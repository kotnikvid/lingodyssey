using System.Net;
using UserService.Application.Common;
using UserService.Domain.Interfaces;
using AutoMapper;
using UserService.Application.Dtos;
using UserService.Domain.Entities;

namespace UserService.Application.Services;

public class TokenService(
    IConfiguration configuration,
    IMapper mapper,
    IApplicationUserService userService,
    IUserRepository userRepository,
    IRoleRepository roleRepository
    )
    : ITokenService
{
    private readonly string _secretKey = configuration.GetValue<string>("Jwt:Key") ?? "";

    public async Task<ResponseDto<TokenPairDto?>> Login(UserLoginDto login)
    {
        if (string.IsNullOrEmpty(login.Email) || string.IsNullOrEmpty(login.Password))
            return new ResponseDto<TokenPairDto?> { StatusCode = HttpStatusCode.BadRequest };

        var user = await userRepository.GetByEmailAsync(login.Email);

        if (user is null) return new ResponseDto<TokenPairDto?> { StatusCode = HttpStatusCode.Unauthorized };

        var hashedPassword = BCrypt.Net.BCrypt.HashPassword(user.Password);

        if (!BCrypt.Net.BCrypt.Verify(user.Password, hashedPassword))
            return new ResponseDto<TokenPairDto?> { StatusCode = HttpStatusCode.Unauthorized };

        var accessToken = TokenUtils.GenerateToken(user, _secretKey);

        var tokenPair = new TokenPairDto
        {
            AccessToken = accessToken,
        };

        if (user.RefreshTokens.OrderByDescending(x => x.ExpirationDate).FirstOrDefault() is { IsExpired: true })
        {
            try
            {
                var refreshToken = new RefreshToken { Token = TokenUtils.GenerateRefreshToken(), UserId = user.Id };
                await userRepository.AddRefreshTokenAsync(refreshToken);

                tokenPair.RefreshToken = refreshToken.Token;
            }
            catch (Exception e)
            {
                return new ResponseDto<TokenPairDto?> { StatusCode = HttpStatusCode.Unauthorized };
            }
        }
        else
        {
            tokenPair.RefreshToken =
                user.RefreshTokens.OrderByDescending(x => x.ExpirationDate).SingleOrDefault(x => !x.IsExpired)?.Token ??
                string.Empty;
        }

        if (string.IsNullOrEmpty(tokenPair.RefreshToken))
            return new ResponseDto<TokenPairDto?>
            {
                StatusCode = HttpStatusCode.InternalServerError, Message = "Error logging in. Please try again later"
            };

        return new ResponseDto<TokenPairDto?> { Data = tokenPair };
    }

    public async Task<ResponseDto<TokenPairDto?>> Register(UserLoginDto model)
    {
        if (Helpers.GetAge(model.BirthDate) < 18)
            return new ResponseDto<TokenPairDto?>()
                { StatusCode = HttpStatusCode.BadRequest, Message = "User must be 18 to use our platform." };

        var dbUser = await userService.GetUserByEmail(model.Email);

        if (dbUser is not null)
            return new ResponseDto<TokenPairDto?>
            {
                StatusCode = HttpStatusCode.Conflict, Message = $"User with email {model.Email} already exists."
            };

        var hashedPassword = BCrypt.Net.BCrypt.HashPassword(model.Password);

        var user = mapper.Map<User>(model);
        user.Password = hashedPassword;

        var roles = await roleRepository.GetAllAsync();

        if (user.Email == "kotnik.vid@gmail.com")
        {
            foreach (var role in roles)
            {
                user.Roles.Add(role);
            }
        }
        else
        {
            var userRole = roles.SingleOrDefault(x => x.Name == "User");
            
            user.Roles.Add(userRole);
        }
       
        await userRepository.AddAsync(user);

        var TokenPairDto = new TokenPairDto
        {
            AccessToken = TokenUtils.GenerateToken(user, _secretKey),
            RefreshToken = TokenUtils.GenerateRefreshToken()
        };

        var refreshToken = new RefreshToken { Token = TokenPairDto.RefreshToken, UserId = user.Id };
        await userRepository.AddRefreshTokenAsync(refreshToken);

        return new ResponseDto<TokenPairDto?> { Data = TokenPairDto };
    }

    public async Task<ResponseDto<string>> GetToken(string refreshToken)
    {
        // var dbToken = await context.RefreshTokens
        //     .Include(x => x.User)
        //     .ThenInclude(x => x.Roles)
        //     .Where(x => x.ExpirationDate > DateTime.UtcNow && x.Token == refreshToken && !x.IsInvalidated)
        //     .OrderByDescending(x => x.ExpirationDate)
        //     .FirstOrDefaultAsync();

        var dbToken = await userRepository.GetValidRefreshTokenAsync(refreshToken);

        if (dbToken is null) return new ResponseDto<string> { StatusCode = HttpStatusCode.Unauthorized };

        var token = TokenUtils.GenerateToken(dbToken.User, _secretKey);

        if (string.IsNullOrEmpty(token)) return new ResponseDto<string> { StatusCode = HttpStatusCode.Unauthorized };

        return new ResponseDto<string> { Data = token };
    }
}
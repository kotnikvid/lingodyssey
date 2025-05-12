using System.Net;
using UserService.Application.Common;
using UserService.Domain.Interfaces;
using AutoMapper;
using MediatR;
using UserService.Application.ApplicationUser.Queries.GetUserByEmail;
using UserService.Application.Tokens.Login;
using UserService.Application.Dtos;
using UserService.Application.Tokens.Commands.GenerateTokenPair;
using UserService.Application.Tokens.Commands.Register;
using UserService.Application.Tokens.Queries.GetToken;
using UserService.Domain.Entities;

namespace UserService.Application.Services;

public class TokenService(
    IConfiguration configuration,
    IMapper mapper,
    IApplicationUserService userService,
    IUserRepository userRepository,
    IRoleRepository roleRepository,
    ILogger<TokenService> logger,
    IMediator mediator
)
    : ITokenService
{
    private readonly string _secretKey = configuration.GetValue<string>("Jwt:Key") ?? "";

    public async Task<ResponseDto<TokenPairDto?>> Login(UserLoginDto login)
    {
        var res = await mediator.Send(new LoginQuery
            { Email = login.Email, Password = login.Password, SecretKey = _secretKey });

        return new ResponseDto<TokenPairDto?> { Data = res, StatusCode = res is not null ? HttpStatusCode.OK : HttpStatusCode.Unauthorized };
    }

    public async Task<ResponseDto<TokenPairDto?>> Register(UserLoginDto model)
    {
        var res = await mediator.Send(new RegisterCommand { Dto = model, SecretKey = _secretKey });
        
        return new ResponseDto<TokenPairDto?> { Data = res, StatusCode = res is not null ? HttpStatusCode.OK : HttpStatusCode.InternalServerError };
    }

    public async Task<ResponseDto<string>> GetToken(string refreshToken)
    {
        var res = await mediator.Send(new GetTokenQuery { RefreshToken = refreshToken, SecretKey = _secretKey });
        
        return new ResponseDto<string> { Data = res ?? string.Empty, StatusCode = res is not null ? HttpStatusCode.OK : HttpStatusCode.Unauthorized };
    }
}
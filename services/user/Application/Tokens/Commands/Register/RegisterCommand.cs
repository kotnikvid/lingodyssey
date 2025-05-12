using AutoMapper;
using MediatR;
using UserService.Application.Common;
using UserService.Application.Tokens.Commands.GenerateTokenPair;
using UserService.Application.Tokens.Login;
using UserService.Domain.Entities;
using UserService.Domain.Interfaces;

namespace UserService.Application.Tokens.Commands.Register;

public class RegisterCommand : IRequest<TokenPairDto?>
{
    public UserLoginDto Dto { get; set; }
    public string SecretKey { get; set; }
    public class RegisterCommandHandler : IRequestHandler<RegisterCommand, TokenPairDto?>
    {
        private readonly ILogger<RegisterCommandHandler> _logger;
        private readonly IUserRepository _userRepository;
        private readonly IMapper _mapper;
        private readonly IRoleRepository _roleRepository;

        public RegisterCommandHandler(ILogger<RegisterCommandHandler> logger, IUserRepository userRepository, IMapper mapper,
            IRoleRepository roleRepository)
        {
            _logger = logger;
            _userRepository = userRepository;
            _mapper = mapper;
            _roleRepository = roleRepository;
        }
        
        public async Task<TokenPairDto?> Handle(RegisterCommand request, CancellationToken cancellationToken)
        {
            var dto = request.Dto;
            
            if (Helpers.GetAge(dto.BirthDate) < 18)
            {
                _logger.LogError("Error registering - illegal birth date");

                return null;
            }

            var dbUser = await _userRepository.GetByEmailAsync(dto.Email);

            if (dbUser is not null)
            {
                _logger.LogError("Error registering - duplicate email");

                return null;
            }

            var hashedPassword = BCrypt.Net.BCrypt.HashPassword(dto.Password);

            var user = _mapper.Map<User>(dto);
            user.Password = hashedPassword;

            var roles = await _roleRepository.GetAllAsync();

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

                if (userRole is null)
                {
                    _logger.LogCritical("Error registering - required role missing");

                    throw new Exception("Required roles missing");
                }

                user.Roles.Add(userRole);
            }

            await _userRepository.AddAsync(user);

            var tokenPairDto = new TokenPairDto
            {
                AccessToken = TokenUtils.GenerateToken(user, request.SecretKey),
                RefreshToken = TokenUtils.GenerateRefreshToken()
            };

            var refreshToken = new RefreshToken { Token = tokenPairDto.RefreshToken, UserId = user.Id };
            await _userRepository.AddRefreshTokenAsync(refreshToken);

            _logger.LogInformation($"Registration successful for user {user.Email}");

            return tokenPairDto;
        }
    }
}
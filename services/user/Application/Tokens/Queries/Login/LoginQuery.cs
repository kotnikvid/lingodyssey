using MediatR;
using UserService.Application.Common;
using UserService.Application.Tokens.Commands.GenerateTokenPair;
using UserService.Domain.Entities;
using UserService.Domain.Interfaces;

namespace UserService.Application.Tokens.Login;

public class LoginQuery : IRequest<TokenPairDto?>
{
    public string Email { get; set; }
    public string Password { get; set; }
    public string SecretKey { get; set; }

    public class LoginQueryHandler : IRequestHandler<LoginQuery, TokenPairDto?>
    {
        private readonly ILogger<LoginQueryHandler> _logger;
        private readonly IUserRepository _userRepository;

        public LoginQueryHandler(ILogger<LoginQueryHandler> logger, IUserRepository userRepository)
        {
            _logger = logger;
            _userRepository = userRepository;
        }
        
        public async Task<TokenPairDto?> Handle(LoginQuery request, CancellationToken cancellationToken)
        {
            if (string.IsNullOrEmpty(request.Email) || string.IsNullOrEmpty(request.Password))
            {
                _logger.LogError("Error logging in - bad request");

                return null;
            }

            var user = await _userRepository.GetByEmailAsync(request.Email);

            if (user is null)
            {
                _logger.LogError("Error logging in - user doesn't exist");

                return null;
            }

            var hashedPassword = BCrypt.Net.BCrypt.HashPassword(user.Password);

            if (!BCrypt.Net.BCrypt.Verify(user.Password, hashedPassword))
            {
                _logger.LogError("Error logging in - unauthorized password");

                return null;
            }

            var accessToken = TokenUtils.GenerateToken(user, request.SecretKey);

            var tokenPair = new TokenPairDto
            {
                AccessToken = accessToken,
            };

            if (user.RefreshTokens.OrderByDescending(x => x.ExpirationDate).FirstOrDefault() is { IsExpired: true })
            {
                try
                {
                    var refreshToken = new RefreshToken { Token = TokenUtils.GenerateRefreshToken(), UserId = user.Id };
                    await _userRepository.AddRefreshTokenAsync(refreshToken);

                    tokenPair.RefreshToken = refreshToken.Token;
                }
                catch (Exception e)
                {
                    _logger.LogError("Error logging in - unauthorized token");

                    return null;
                }
            }
            else
            {
                tokenPair.RefreshToken =
                    user.RefreshTokens.OrderByDescending(x => x.ExpirationDate).SingleOrDefault(x => !x.IsExpired)
                        ?.Token ??
                    string.Empty;
            }

            if (string.IsNullOrEmpty(tokenPair.RefreshToken))
            {
                _logger.LogError("Error logging in - token invalid");

                return null;
            }

            _logger.LogInformation($"Login successful for user {request.Email}");
            
            return tokenPair;
        }
    }
}
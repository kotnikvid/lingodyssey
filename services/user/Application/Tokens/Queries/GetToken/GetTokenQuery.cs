using MediatR;
using UserService.Application.Common;
using UserService.Domain.Interfaces;

namespace UserService.Application.Tokens.Queries.GetToken;

public class GetTokenQuery : IRequest<string?>
{
    public string RefreshToken { get; set; }
    public string SecretKey { get; set; }

    public class GetTokenQueryHandler : IRequestHandler<GetTokenQuery, string?>
    {
        private readonly IUserRepository _userRepository;
        private readonly ILogger<GetTokenQueryHandler> _logger;

        public GetTokenQueryHandler(IUserRepository userRepository, ILogger<GetTokenQueryHandler> logger)
        {
            _userRepository = userRepository;
            _logger = logger;
        }
        
        public async Task<string?> Handle(GetTokenQuery request, CancellationToken cancellationToken)
        {
            var dbToken = await _userRepository.GetValidRefreshTokenAsync(request.RefreshToken);

            if (dbToken is null)
            {
                _logger.LogError($"Error issuing token");

                return null;
            }

            var token = TokenUtils.GenerateToken(dbToken.User, request.SecretKey);

            if (string.IsNullOrEmpty(token))
            {
                _logger.LogError($"Error issuing token");

                return null;
            }

            _logger.LogInformation($"New token issuesd for refresh token {request.RefreshToken}");

            return token;
        }
    }
}
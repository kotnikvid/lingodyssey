using AutoMapper;
using MediatR;
using UserService.Domain.Interfaces;

namespace UserService.Application.ApplicationUsers.Commands.ChangePassword;

public class ChangePasswordCommand : IRequest<bool>
{
    public string Email { get; set; }
    public string Password { get; set; }
    
    public class ChangePasswordCommandHandler : IRequestHandler<ChangePasswordCommand, bool>
    {
        private readonly IUserRepository _userRepository;
        private readonly IMapper _mapper;
        private readonly ILogger<ChangePasswordCommandHandler> _logger;

        public ChangePasswordCommandHandler(IUserRepository userRepository, IMapper mapper, ILogger<ChangePasswordCommandHandler> logger)
        {
            _userRepository = userRepository;
            _mapper = mapper;
            _logger = logger;
        }
        
        public async Task<bool> Handle(ChangePasswordCommand request, CancellationToken cancellationToken)
        {
            var user = await _userRepository.GetByEmailAsync(request.Email);
            
            if (user == null) return false;
            
            user.Password = request.Password;

            foreach (var token in user.RefreshTokens)
            {
                token.IsInvalidated = true;
            }

            await _userRepository.UpdateAsync(user);

            _logger.LogInformation($"User {user.Email}'s password has been updated.");

            return true;
        }
    }
}
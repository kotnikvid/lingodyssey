using MediatR;
using UserService.Domain.Entities;
using UserService.Domain.Interfaces;

namespace UserService.Application.ApplicationUser.Commands.AddUser;

public class AddUserCommand : IRequest<Unit>
{
    public string Email { get; set; }
    public string Password { get; set; }
    
    public class AddUserCommandHandler : IRequestHandler<AddUserCommand, Unit>
    {
        private readonly IUserRepository _userRepository;
        private readonly ILogger<AddUserCommandHandler> _logger;

        public AddUserCommandHandler(IUserRepository userRepository, ILogger<AddUserCommandHandler> logger)
        {
            _userRepository = userRepository;
            _logger = logger;
        }
        
        public async Task<Unit> Handle(AddUserCommand request, CancellationToken cancellationToken)
        {
            var user = new User
            {
                Email = request.Email,
                Password = request.Password
            };

            await _userRepository.AddAsync(user);
            
            _logger.LogInformation($"Created new user: {user}");
            
            return Unit.Value;
        }
    }
}
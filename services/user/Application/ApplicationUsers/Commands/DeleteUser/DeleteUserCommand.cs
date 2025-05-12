using AutoMapper;
using MediatR;
using UserService.Domain.Interfaces;

namespace UserService.Application.ApplicationUsers.Commands.DeleteUser;

public class DeleteUserCommand : IRequest<bool>
{
    public Guid UserId { get; set; }

    public class DeleteUserCommandHandler : IRequestHandler<DeleteUserCommand, bool>
    {
        private readonly IUserRepository _userRepository;
        private readonly IMapper _mapper;
        private readonly ILogger<DeleteUserCommandHandler> _logger;

        public DeleteUserCommandHandler(IUserRepository userRepository, IMapper mapper,
            ILogger<DeleteUserCommandHandler> logger)
        {
            _userRepository = userRepository;
            _mapper = mapper;
            _logger = logger;
        }

        public async Task<bool> Handle(DeleteUserCommand request, CancellationToken cancellationToken)
        {
            var user = await _userRepository.GetByIdAsync(request.UserId);

            if (user is null) return false;

            await _userRepository.DeleteAsync(request.UserId);

            _logger.LogInformation($"User {request.UserId} has been deleted.");
            
            return true;
        }
    }
}
using AutoMapper;
using MediatR;
using UserService.Application.ApplicationUser.Queries.GetUsers;
using UserService.Application.Tokens.Login;
using UserService.Domain.Interfaces;

namespace UserService.Application.ApplicationUser.Commands.UpdateUser;

public class UpdateUserCommand : IRequest<UserDto?>
{
    public UserLoginDto Dto { get; set; }

    public class UpdateUserCommandHandler : IRequestHandler<UpdateUserCommand, UserDto?>
    {
        private readonly IUserRepository _userRepository;
        private readonly IMapper _mapper;
        private readonly ILogger<UpdateUserCommandHandler> _logger;

        public UpdateUserCommandHandler(IUserRepository userRepository, IMapper mapper,
            ILogger<UpdateUserCommandHandler> logger)
        {
            _userRepository = userRepository;
            _mapper = mapper;
            _logger = logger;
        }

        public async Task<UserDto?> Handle(UpdateUserCommand request, CancellationToken cancellationToken)
        {
            var dto = request.Dto;
            
            var user = await _userRepository.GetByEmailAsync(dto.Email);
            
            if (user is null) return new UserDto();
            
            user.FullName = dto.FullName;
            user.BirthDate = dto.BirthDate;
            user.DisplayName = dto.DisplayName;

            await _userRepository.UpdateAsync(user);

            _logger.LogInformation($"Updated user {user.Email} to: {user}");
            
            return _mapper.Map<UserDto>(user);
        }
    }
}
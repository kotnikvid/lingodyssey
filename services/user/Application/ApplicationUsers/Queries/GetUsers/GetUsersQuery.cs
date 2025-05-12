using AutoMapper;
using MediatR;
using UserService.Domain.Interfaces;

namespace UserService.Application.ApplicationUser.Queries.GetUsers;

public class GetUsersQuery : IRequest<List<UserDto>>
{
    public class GetUsersQueryHandler : IRequestHandler<GetUsersQuery, List<UserDto>>
    {
        private readonly IMapper _mapper;
        private readonly IUserRepository _userRepository;
        private readonly ILogger<GetUsersQueryHandler> _logger;
        
        public GetUsersQueryHandler(IUserRepository userRepository, ILogger<GetUsersQueryHandler> logger, IMapper mapper)
        {
            _mapper = mapper;
            _userRepository = userRepository;
            _logger = logger;
        }
        
        public async Task<List<UserDto>> Handle(GetUsersQuery request, CancellationToken cancellationToken)
        {
            var users = await _userRepository.GetAllAsync();

            if (users != null)
            {
                _logger.LogInformation($"Retrieved {users.Count} users");

                return _mapper.Map<List<UserDto>>(users);
            }
            
            _logger.LogInformation($"No users retrieved");

            return new();
        }
    }
}
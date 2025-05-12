using AutoMapper;
using MediatR;
using UserService.Application.ApplicationUser.Queries.GetUsers;
using UserService.Domain.Interfaces;

namespace UserService.Application.ApplicationUser.Queries.GetUserByEmail;

public class GetUserByEmailQuery : IRequest<UserDto?>
{
    public string Email { get; set; }
    
    public class GetUserByEmailQueryHandler : IRequestHandler<GetUserByEmailQuery, UserDto?>
    {
        private readonly IMapper _mapper;
        private readonly IUserRepository _userRepository;

        public GetUserByEmailQueryHandler(IMapper mapper, IUserRepository userRepository)
        {
            _mapper = mapper;
            _userRepository = userRepository;
        }
        
        public async Task<UserDto?> Handle(GetUserByEmailQuery request, CancellationToken cancellationToken)
        {
            var user = await _userRepository.GetByEmailAsync(request.Email);
            
            if (user == null) return null;
        
            return _mapper.Map<UserDto>(user);
        }
    }
}
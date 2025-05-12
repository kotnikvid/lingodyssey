using AutoMapper;
using UserService.Application.Tokens.Login;
using UserService.Domain.Entities;
using UserDto = UserService.Application.ApplicationUser.Queries.GetUsers.UserDto;

namespace UserService.Infrastructure;

public class MappingsProfile : Profile
{
    public MappingsProfile()
    {
        CreateMap<UserLoginDto, User>();
        CreateMap<User, UserDto>().ReverseMap();
    }
    
}
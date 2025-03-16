using AutoMapper;
using UserService.Application.Dtos;
using UserService.Domain.Entities;

namespace UserService.Infrastructure;

public class MappingsProfile : Profile
{
    public MappingsProfile()
    {
        CreateMap<UserLoginDto, User>();
        CreateMap<User, UserDto>().ReverseMap();
    }
    
}
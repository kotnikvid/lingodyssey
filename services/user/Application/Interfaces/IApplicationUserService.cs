using UserService.Application.Dtos;
using UserService.Application.Tokens.Login;
using UserDto = UserService.Application.ApplicationUser.Queries.GetUsers.UserDto;

namespace UserService.Domain.Interfaces;

public interface IApplicationUserService
{
    public Task<UserDto?> GetUserByEmail(string email);
    public Task<List<UserDto>> GetUsers();
    public Task<ResponseDto<object?>> AddUser(UserLoginDto userDto);
    public Task<ResponseDto<object?>> UpdateUser(UserLoginDto user);    
    public Task<ResponseDto<object?>> ChangePassword(UserLoginDto user);    
    public Task<ResponseDto<object?>> DeleteUser(Guid userId);
}
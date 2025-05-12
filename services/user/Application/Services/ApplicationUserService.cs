using System.Net;
using UserService.Application.Dtos;
using AutoMapper;
using MediatR;
using UserService.Application.ApplicationUser.Commands.AddUser;
using UserService.Application.ApplicationUser.Commands.UpdateUser;
using UserService.Application.ApplicationUser.Queries.GetUserByEmail;
using UserService.Application.ApplicationUser.Queries.GetUsers;
using UserService.Application.ApplicationUsers.Commands.ChangePassword;
using UserService.Application.ApplicationUsers.Commands.DeleteUser;
using UserService.Application.Tokens.Login;
using UserService.Domain.Entities;
using UserService.Domain.Interfaces;
using UserDto = UserService.Application.ApplicationUser.Queries.GetUsers.UserDto;

namespace UserService.Application.Services;

public class ApplicationUserService(
    IUserRepository userRepository,
    IMapper mapper,
    ILogger<ApplicationUserService> logger,
    IMediator mediator) : IApplicationUserService
{
    public async Task<List<UserDto>> GetUsers()
    {
        var users = await mediator.Send(new GetUsersQuery());

        return users;
    }

    public async Task<UserDto?> GetUserByEmail(string email)
    {
        var user = await mediator.Send(new GetUserByEmailQuery { Email = email });

        return user;
    }

    public async Task<ResponseDto<object?>> AddUser(UserLoginDto userDto)
    {
        try
        {
            var existingUser = await mediator.Send(new GetUserByEmailQuery { Email = userDto.Email });
            if (existingUser != null)
            {
                return new ResponseDto<object?>
                {
                    StatusCode = HttpStatusCode.OK,
                    Message = "A user with this email already exists.",
                    Data = null
                };
            }

            await mediator.Send(new AddUserCommand
                { Email = userDto.Email, Password = BCrypt.Net.BCrypt.HashPassword(userDto.Password) });

            return new ResponseDto<object?>
            {
                StatusCode = HttpStatusCode.OK,
                Message = "User added successfully.",
                Data = null
            };
        }
        catch (Exception ex)
        {
            var message = $"An error occurred while adding the user: {ex.Message}";

            logger.LogError(message);

            return new ResponseDto<object?>
            {
                StatusCode = HttpStatusCode.InternalServerError,
                Message = message,
                Data = null
            };
        }
    }

    public async Task<ResponseDto<object?>> UpdateUser(UserLoginDto dto)
    {
        try
        {
            var user = await mediator.Send(new GetUserByEmailQuery { Email = dto.Email });

            if (user is null) return new ResponseDto<object?> { StatusCode = HttpStatusCode.BadRequest };

            await mediator.Send(new UpdateUserCommand { Dto = dto });

            return new ResponseDto<object?> { StatusCode = HttpStatusCode.OK };
        }
        catch (Exception e)
        {
            logger.LogError($"Error updating user {dto.Email}");

            return new ResponseDto<object?> { StatusCode = HttpStatusCode.InternalServerError };
        }
    }

    public async Task<ResponseDto<object?>> ChangePassword(UserLoginDto dto)
    {
        try
        {
            var hashedPassword = BCrypt.Net.BCrypt.HashPassword(dto.Password);
            
            var res = await mediator.Send(new ChangePasswordCommand { Email = dto.Email, Password = hashedPassword });

            return new ResponseDto<object?> { Data = res, StatusCode = HttpStatusCode.OK };
        }
        catch (Exception e)
        {
            logger.LogError($"Error updating user password {dto.Email}");
            return new ResponseDto<object?> { StatusCode = HttpStatusCode.InternalServerError };
        }
    }

    public async Task<ResponseDto<object?>> DeleteUser(Guid userId)
    {
        try
        {
            var res = await mediator.Send(new DeleteUserCommand { UserId = userId });

            return new ResponseDto<object?> { Data = res, StatusCode = res ? HttpStatusCode.OK : HttpStatusCode.InternalServerError };
        }
        catch (Exception e)
        {
            logger.LogInformation($"Error deleting user {userId}");

            return new ResponseDto<object?> { StatusCode = HttpStatusCode.InternalServerError };
        }
    }
}
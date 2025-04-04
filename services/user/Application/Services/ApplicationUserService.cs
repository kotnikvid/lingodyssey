﻿using System.Net;
using UserService.Application.Dtos;
using AutoMapper;
using Microsoft.EntityFrameworkCore;
using UserService.Domain.Entities;
using UserService.Domain.Interfaces;
using UserService.Infrastructure;

namespace UserService.Application.Services;

public class ApplicationUserService(IUserRepository userRepository, IMapper mapper, ILogger<ApplicationUserService> logger) : IApplicationUserService
{
    public async Task<List<UserDto>> GetUsers()
    {
        var users = await userRepository.GetAllAsync();
        
        return mapper.Map<List<UserDto>>(users);
    }
    public async Task<UserDto?> GetUserByEmail(string email)
    {
        var user = await userRepository.GetByEmailAsync(email);
        
        return mapper.Map<UserDto>(user);
    }

    public async Task<ResponseDto<object?>> AddUser(UserLoginDto userDto)
    {
            try
            {
                var existingUser = await userRepository.GetByEmailAsync(userDto.Email);
                if (existingUser != null)
                {
                    return new ResponseDto<object?>
                    {
                        StatusCode = HttpStatusCode.OK,
                        Message = "A user with this email already exists.",
                        Data = null
                    };
                }

                var user = new User
                {
                    Email = userDto.Email,
                    Password = BCrypt.Net.BCrypt.HashPassword(userDto.Password)
                };

                await userRepository.AddAsync(user);
                
                logger.LogInformation($"Created new user: {user}");

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
            var user = await userRepository.GetByEmailAsync(dto.Email.ToLower());

            if (user is null) return new ResponseDto<object?> { StatusCode = HttpStatusCode.BadRequest };

            user.FullName = dto.FullName;
            user.BirthDate = dto.BirthDate;
            user.DisplayName = dto.DisplayName;

            await userRepository.UpdateAsync(user);
            
            logger.LogInformation($"Updated user {user.Email} to: {user}");

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
            var user = await userRepository.GetByEmailAsync(dto.Email.ToLower());

            if (user is null) return new ResponseDto<object?> { StatusCode = HttpStatusCode.BadRequest };

            var hashedPassword = BCrypt.Net.BCrypt.HashPassword(dto.Password);

            user.Password = hashedPassword;

            foreach (var token in user.RefreshTokens)
            {
                token.IsInvalidated = true;
            }

            await userRepository.UpdateAsync(user);
            
            logger.LogInformation($"User {user.Email}'s password has been updated.");

            return new ResponseDto<object?> { Data = user, StatusCode = HttpStatusCode.OK };
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
            var user = await userRepository.GetByIdAsync(userId);

            if (user is null) return new ResponseDto<object?> { StatusCode = HttpStatusCode.NotFound };

            await userRepository.DeleteAsync(userId);
            
            logger.LogInformation($"User {userId} has been deleted.");

            return new ResponseDto<object?>();
        }
        catch (Exception e)
        {
            logger.LogInformation($"Error deleting user {userId}");
            
            return new ResponseDto<object?> { StatusCode = HttpStatusCode.InternalServerError };
        }
    }
}
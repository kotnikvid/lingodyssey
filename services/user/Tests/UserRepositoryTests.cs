using Microsoft.EntityFrameworkCore;
using UserService.Domain.Entities;
using UserService.Domain.Interfaces;
using UserService.Infrastructure;
using UserService.Infrastructure.Persistence.Repositories;
using Xunit;

namespace UserService.Tests;

public class UserRepositoryTests
{
     private readonly IUserRepository _userRepository;

     public UserRepositoryTests()
    {
        var options = new DbContextOptionsBuilder<ApplicationDbContext>()
            .UseInMemoryDatabase(Guid.NewGuid().ToString())
            .Options;

        var dbContext = new ApplicationDbContext(options);
        _userRepository = new PostgresUserRepository(dbContext);
    }

     [Fact]
    public async Task AddAsync_AddsUserSuccessfully()
    {
        var user = new User
        {
            Id = Guid.NewGuid(),
            Email = "testuser@example.com",
            FullName = "Test",
        };

        await _userRepository.AddAsync(user);
        var addedUser = await _userRepository.GetByEmailAsync(user.Email);

        Assert.NotNull(addedUser);
        Assert.Equal(user.Email, addedUser.Email);
    }

    [Fact]
    public async Task GetByEmailAsync_ReturnsCorrectUser()
    {
        var user = new User
        {
            Id = Guid.NewGuid(),
            Email = "testuser@example.com",
            FullName = "Test",
        };
        await _userRepository.AddAsync(user);

        var result = await _userRepository.GetByEmailAsync(user.Email);

        Assert.NotNull(result);
        Assert.Equal(user.Email, result.Email);
    }

    [Fact]
    public async Task GetByIdAsync_ReturnsCorrectUser()
    {
        var user = new User
        {
            Id = Guid.NewGuid(),
            Email = "testuser@example.com",
            FullName = "Test",
        };
        await _userRepository.AddAsync(user);

        var result = await _userRepository.GetByIdAsync(user.Id);

        Assert.NotNull(result);
        Assert.Equal(user.Id, result.Id);
    }

    [Fact]
    public async Task UpdateAsync_UpdatesUserSuccessfully()
    {
        var user = new User
        {
            Id = Guid.NewGuid(),
            Email = "testuser@example.com",
            FullName = "Test",
        };
        await _userRepository.AddAsync(user);

        user.FullName = "UpdatedFullName";

        await _userRepository.UpdateAsync(user);
        var updatedUser = await _userRepository.GetByIdAsync(user.Id);

        Assert.NotNull(updatedUser);
        Assert.Equal("UpdatedFullName", updatedUser.FullName);
    }

    [Fact]
    public async Task DeleteAsync_DeletesUserSuccessfully()
    {
        var user = new User
        {
            Id = Guid.NewGuid(),
            Email = "testuser@example.com",
            FullName = "Test",
        };
        await _userRepository.AddAsync(user);

        await _userRepository.DeleteAsync(user.Id);
        var deletedUser = await _userRepository.GetByIdAsync(user.Id);

        Assert.Null(deletedUser); // Should return null because the user is deleted
    }

    [Fact]
    public async Task AddRefreshTokenAsync_AddsTokenSuccessfully()
    {
        var user = new User
        {
            Id = Guid.NewGuid(),
            Email = "testuser@example.com",
            FullName = "Test",
        };
        await _userRepository.AddAsync(user);

        var refreshToken = new RefreshToken
        {
            Token = "valid-token-123",
            UserId = user.Id, // Reference to the user
            ExpirationDate = DateTime.UtcNow.AddDays(1)
        };

        await _userRepository.AddRefreshTokenAsync(refreshToken);
        var result = await _userRepository.GetValidRefreshTokenAsync(refreshToken.Token);

        Assert.NotNull(result);
        Assert.Equal(refreshToken.Token, result.Token);
        Assert.Equal(user.Id, result.UserId); // Ensure the user ID matches
    }

    [Fact]
    public async Task GetRefreshTokenByUserEmailAsync_ReturnsCorrectToken()
    {
        var user = new User
        {
            Id = Guid.NewGuid(),
            Email = "testuser@example.com",
            FullName = "Test",
        };
        await _userRepository.AddAsync(user);

        var refreshToken = new RefreshToken
        {
            Token = "valid-token-123",
            UserId = user.Id, // Reference to the user
            ExpirationDate = DateTime.UtcNow.AddDays(1)
        };
        await _userRepository.AddRefreshTokenAsync(refreshToken);

        var result = await _userRepository.GetRefreshTokenByUserEmailAsync(user.Email);

        Assert.NotNull(result);
        Assert.Equal(refreshToken.Token, result.Token);
    }

    [Fact]
    public async Task DeleteRefreshTokenByUserEmailAsync_DeletesTokenSuccessfully()
    {
        var user = new User
        {
            Id = Guid.NewGuid(),
            Email = "testuser@example.com",
            FullName = "Test",
        };
        await _userRepository.AddAsync(user);

        var refreshToken = new RefreshToken
        {
            Token = "valid-token-123",
            UserId = user.Id, // Reference to the user
            ExpirationDate = DateTime.UtcNow.AddDays(1)
        };
        await _userRepository.AddRefreshTokenAsync(refreshToken);

        await _userRepository.DeleteRefreshTokenByUserEmailAsync(user.Email);
        var deletedToken = await _userRepository.GetRefreshTokenByUserEmailAsync(user.Email);

        Assert.Null(deletedToken); // Should return null because the refresh token is deleted
    }
}
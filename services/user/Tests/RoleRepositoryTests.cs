using UserService.Domain.Entities;
using UserService.Domain.Interfaces;
using UserService.Infrastructure;
using UserService.Infrastructure.Persistence.Repositories;

namespace UserService.Tests;
using System;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Xunit;

public class RoleRepositoryTests
{

    private readonly DbContextOptions<ApplicationDbContext> _dbContextOptions = new DbContextOptionsBuilder<ApplicationDbContext>()
        .UseInMemoryDatabase(Guid.NewGuid().ToString())
        .Options;

    private IRoleRepository CreateRepository()
        {
            var context = new ApplicationDbContext(_dbContextOptions);
            return new PostgresRoleRepository(context);
        }

        [Fact]
        public async Task AddAsync_AddsRoleToDatabase()
        {
            await using var context = new ApplicationDbContext(_dbContextOptions);
            var repository = CreateRepository();

            var newRole = new Role { Name = "Admin" };

            await repository.AddAsync(newRole);
            var savedRole = await context.Roles.FirstOrDefaultAsync(r => r.Name == "Admin");

            Assert.NotNull(savedRole);
            Assert.Equal("Admin", savedRole.Name);
            Assert.True(savedRole.Id > 0);  // Ensure EF Core assigned an ID
        }

        [Fact]
        public async Task GetByNameAsync_ReturnsRole_WhenRoleExists()
        {
            await using var context = new ApplicationDbContext(_dbContextOptions);
            var repository = CreateRepository();

            var testRole = new Role { Name = "Admin" };
            await context.Roles.AddAsync(testRole);
            await context.SaveChangesAsync();

            var result = await repository.GetByNameAsync("Admin");

            Assert.NotNull(result);
            Assert.Equal("Admin", result.Name);
        }

        [Fact]
        public async Task GetByNameAsync_ReturnsNull_WhenRoleDoesNotExist()
        {
            await using var context = new ApplicationDbContext(_dbContextOptions);
            var repository = CreateRepository();

            var result = await repository.GetByNameAsync("NonExistentRole");

            Assert.Null(result);
        }

        [Fact]
        public async Task GetAllAsync_ReturnsAllRoles()
        {
            await using var context = new ApplicationDbContext(_dbContextOptions);
            var repository = CreateRepository();

            var role1 = new Role { Name = "Admin" };
            var role2 = new Role { Name = "User" };

            await context.Roles.AddAsync(role1);
            await context.Roles.AddAsync(role2);
            await context.SaveChangesAsync();

            var roles = await repository.GetAllAsync();

            Assert.NotNull(roles);
            Assert.Contains(roles, r => r.Name == "Admin");
            Assert.Contains(roles, r => r.Name == "User");
        }
}
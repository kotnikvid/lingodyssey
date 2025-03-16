using Microsoft.EntityFrameworkCore;
using UserService.Domain.Entities;
using UserService.Domain.Interfaces;

namespace UserService.Infrastructure.Persistence.Repositories;

public class PostgresUserRepository(ApplicationDbContext context) : IUserRepository
{
    public async Task<User?> GetByEmailAsync(string email)
    {
        return await context.Users
            .Include(x => x.Roles)
            .Include(x => x.RefreshTokens)
            .Where(x => x.Email.ToLower() == email.ToLower())
            .FirstOrDefaultAsync();
    }

    public async Task<User?> GetByIdAsync(Guid userId)
    {
        return await context.Users
            .Include(x => x.Roles)
            .Include(x => x.RefreshTokens)
            .Where(x => x.Id == userId)
            .FirstOrDefaultAsync();
    }

    public async Task<List<User>> GetAllAsync()
    {
        return await context.Users
            .Include(x => x.Roles)
            .Include(x => x.RefreshTokens)
            .ToListAsync();
    }

    public async Task AddAsync(User user)
    {
        await context.Users.AddAsync(user);
        
        await context.SaveChangesAsync();
    }

    public async Task UpdateAsync(User user)
    {
        context.Users.Update(user);
        
        await context.SaveChangesAsync();
    }

    public async Task DeleteAsync(Guid userId)
    {
        var user = await context.Users
            .Where(x => x.Id == userId)
            .FirstOrDefaultAsync();

        if (user is null) return;

        context.Users.Remove(user);
        
        await context.SaveChangesAsync();
    }

    public async Task<RefreshToken?> GetRefreshTokenByUserEmailAsync(string email)
    {
        return await context.RefreshTokens
            .Include(x => x.User)
            .ThenInclude(x => x.Roles)
            .Where(x => x.User.Email == email)
            .FirstOrDefaultAsync();
    }

    public async Task<RefreshToken?> GetValidRefreshTokenAsync(string token)
    {
        return await context.RefreshTokens
            .Include(x => x.User)
            .ThenInclude(x => x.Roles)
            .Where(x => x.ExpirationDate > DateTime.UtcNow && x.Token == token && !x.IsInvalidated)
            .OrderByDescending(x => x.ExpirationDate)
            .FirstOrDefaultAsync();
    }

    public async Task AddRefreshTokenAsync(RefreshToken token)
    {
        await context.RefreshTokens.AddAsync(token);
        
        await context.SaveChangesAsync();
    }

    public async Task DeleteRefreshTokenByUserEmailAsync(string email)
    {
        var tokens = await context.RefreshTokens
            .Include(x => x.User)
            .Where(x => x.User.Email == email)
            .ToListAsync();

        context.RemoveRange(tokens);
        
        await context.SaveChangesAsync();
    }
}
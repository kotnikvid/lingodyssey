using Microsoft.EntityFrameworkCore;
using UserService.Domain.Entities;
using UserService.Domain.Interfaces;

namespace UserService.Infrastructure.Persistence.Repositories;

public class PostgresRoleRepository(ApplicationDbContext context) : IRoleRepository
{
    public async Task AddAsync(Role role)
    {
        await context.Roles.AddAsync(role);
        
        await context.SaveChangesAsync();
    }

    public async Task<Role?> GetByNameAsync(string name)
    {
        return await context.Roles
            .Where(r => r.Name == name)
            .FirstOrDefaultAsync();
    }

    public async Task<List<Role>> GetAllAsync()
    {
        return await context.Roles.ToListAsync();
    }
}
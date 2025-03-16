using UserService.Domain.Entities;

namespace UserService.Domain.Interfaces;

public interface IRoleRepository
{
    public Task AddAsync(Role role);
    public Task<Role?> GetByNameAsync(string name);
    public Task<List<Role>> GetAllAsync();
}
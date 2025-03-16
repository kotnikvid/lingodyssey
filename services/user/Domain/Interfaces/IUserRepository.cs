using UserService.Domain.Entities;

namespace UserService.Domain.Interfaces;

public interface IUserRepository
{
    Task<User?> GetByEmailAsync(string email);
    Task<User?> GetByIdAsync(Guid userId);
    Task<List<User>> GetAllAsync();
    Task AddAsync(User user);
    Task UpdateAsync(User user);
    Task DeleteAsync(Guid userId);
    
    Task<RefreshToken?> GetRefreshTokenByUserEmailAsync(string email);
    Task<RefreshToken?> GetValidRefreshTokenAsync(string token);
    Task AddRefreshTokenAsync(RefreshToken user);
    Task DeleteRefreshTokenByUserEmailAsync(string email);

}
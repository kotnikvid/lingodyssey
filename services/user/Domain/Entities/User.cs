namespace UserService.Domain.Entities;

public class User
{
    public Guid Id { get; set; } = Guid.NewGuid();
    public string Email { get; set; }
    public string? DisplayName { get; set; }
    public string? FullName { get; set; }
    public string? Password { get; set; }
    public DateTime? BirthDate { get; set; }
    public DateTime CreatedDate { get; set; } = DateTime.UtcNow;
    public int Points { get; set; } = 0;
    
    public ICollection<RefreshToken> RefreshTokens { get; set; } = new List<RefreshToken>();
    public ICollection<Role> Roles { get; set; } = new List<Role>();
}
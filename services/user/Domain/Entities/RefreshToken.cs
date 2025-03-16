namespace UserService.Domain.Entities;

public class RefreshToken
{
    public int Id { get; set; }
    public string Token { get; set; }
    public DateTime CreationDate { get; set; } = DateTime.UtcNow;
    public DateTime ExpirationDate { get; set; } = DateTime.UtcNow.AddDays(30);
    public bool IsExpired => DateTime.UtcNow > ExpirationDate;
    public bool IsInvalidated { get; set; } = false;
    
    public Guid UserId { get; set; }
    public virtual User User { get; set; } = null!;
}
namespace UserService.Application.ApplicationUser.Queries.GetUsers;

public class UserDto
{
    public Guid Id { get; set; } = Guid.NewGuid();
    public string Email { get; set; }
    public string? DisplayName { get; set; }
    public string? FullName { get; set; }
    public DateTime? BirthDate { get; set; }
    public int Points { get; set; } = 0;
}
namespace WebBFF.Application.Dtos;

public class UserLoginDto
{
    public string Email { get; set; }
    public string Password { get; set; }
    public string DisplayName { get; set; }
    public string FullName { get; set; }
    public DateTime BirthDate { get; set; }
    public string RefreshToken { get; set; }
}
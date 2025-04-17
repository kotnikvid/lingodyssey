namespace WebBFF.Application.Dtos.Gamification;

public class StreakDto
{
    public string UserEmail { get; set; }
    public string Type { get; set; }
    public DateTime RestartedAt { get; set; }
    public bool IsFailed { get; set; }
    public DateTime CreatedAt { get; set; }
}
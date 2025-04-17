namespace WebBFF.Application.Dtos.Cards;

public class AttemptDto
{
    public string UserEmail { get; set; } = string.Empty;
    public DateTime StartedAt { get; set; } = DateTime.UtcNow;
    public DateTime EndedAt { get; set; } = DateTime.UtcNow;
    public bool HasPassed { get; set; } = false;
    public string CardSetId { get; set; } = string.Empty;
}
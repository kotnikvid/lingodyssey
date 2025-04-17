namespace WebBFF.Application.Dtos.Gamification;

public class AwardDto
{
    public string Name { get; set; }
    public string Description { get; set; }
    public int PointsNeeded { get; set; }
    public string CreatedBy { get; set; }
}
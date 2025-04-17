namespace WebBFF.Application.Dtos.Cards;

public class FlashCardDto
{
    public string Id { get; set; } = string.Empty;
    public string CardSetId { get; set; } = string.Empty;
    public string Title { get; set; } = string.Empty;
    public string Body { get; set; } = string.Empty;
    public string CorrectAnswer { get; set; } = string.Empty;
    public int PointAwarded { get; set; } = 0;
    public List<string> OtherAnswers { get; set; } = new();
}
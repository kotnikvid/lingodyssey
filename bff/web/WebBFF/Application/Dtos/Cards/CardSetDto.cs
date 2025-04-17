namespace WebBFF.Application.Dtos.Cards;

public class CardSetDto
{
    public string Id { get; set; } = string.Empty;
    public string Name { get; set; } = string.Empty;
    public string UserEmail { get; set; } = string.Empty;
    public string LanguageName { get; set; } = string.Empty;
    public string LanguageCode { get; set; } = string.Empty;
    public List<FlashCardDto> Flashcards { get; set; } = new();

}
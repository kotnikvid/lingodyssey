namespace UserService.Application.Tokens.Commands.GenerateTokenPair;

public class TokenPairDto
{
    public string AccessToken { get; set; } = string.Empty;
    public string RefreshToken { get; set; } = string.Empty;
}
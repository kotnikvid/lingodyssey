using RestSharp;
using WebBFF.Application.Dtos;
using WebBFF.Application.Dtos.Cards;
using WebBFF.Application.Dtos.Gamification;

namespace WebBFF.Application.Interfaces;

public interface IHttpRestClient
{
    public Task<RestResponse> Login(UserLoginDto dto);
    public Task<RestResponse> Register(UserLoginDto dto);
    public Task<RestResponse> GetToken(string refreshToken);
    public Task<RestResponse> GetUserByEmail(string email, string jwtToken);
    public Task<RestResponse> UpdateUser(UserLoginDto dto, string jwtToken);
    public Task<RestResponse> ChangePassword(UserLoginDto dto, string jwtToken);
    public Task<RestResponse> DeleteUser(string id, string jwtToken);
    public Task<RestResponse?> GetStreakByIdAsync(string id, string jwtToken);
    
    public Task<RestResponse?> CreateOrUpdateStreakAsync(StreakDto dto, string jwtToken);
    public Task<RestResponse?> UpdateStreakAsync(string id, StreakDto dto, string jwtToken);
    
    public Task<bool> DeleteStreakAsync(string id, string jwtToken);
    
    // ----- Award endpoints -----
    
    public Task<RestResponse?> GetAwardByIdAsync(string id, string jwtToken);
    
    public Task<RestResponse?> CreateAwardAsync(AwardDto dto, string jwtToken);
    
    public Task<RestResponse?> UpdateAwardAsync(string id, AwardDto dto, string jwtToken);
    
    public Task<bool> DeleteAwardAsync(string id, string jwtToken);
    
    public Task<RestResponse> AwardUserAsync(AwardUserDto dto, string jwtToken);
    
    public Task<List<AwardDto>?> AwardsByUserEmail(string email, string jwtToken);
    
    //Card Sets
    public Task<RestResponse> AddCardSetAsync(CardSetDto dto, string jwtToken);
    public Task<RestResponse> GetCardSetByIdAsync(string id, string jwtToken);
    
    public Task<RestResponse> GetAllCardSetsAsync(string jwtToken);
    
    public Task<RestResponse> GetCardSetsByLanguageAsync(string? languageName, string? languageCode, string jwtToken);
    
    public Task<RestResponse> GetCardSetsByUserAsync(string email, string jwtToken);
    
    public Task<RestResponse> UpdateCardSetAsync(CardSetDto dto, string jwtToken);
    
    public Task<RestResponse> DeleteCardSetAsync(string id, string jwtToken);
    
    public Task<RestResponse> AddFlashcardAsync(FlashCardDto dto, string jwtToken);
    
    public Task<RestResponse> GetFlashcardByIdAsync(string id, string jwtToken);
    
    public Task<RestResponse> UpdateFlashcardAsync(FlashCardDto dto, string jwtToken);
    
    public Task<RestResponse> DeleteFlashcardAsync(string id, string jwtToken);
    
    public Task<RestResponse> AddAttemptAsync(AttemptDto dto, string jwtToken);
    public Task<RestResponse> GetStreaksByUserEmailAsync(string email, string jwtToken);
    public Task<RestResponse> GetAwardsByUserEmailAsync(string email, string jwtToken);

    // public Task<RestResponse?> CreateOrUpdateStreakAsync(StreakDto dto);
    // public Task<RestResponse?> UpdateStreakAsync(string id, StreakDto dto);

    // public Task<bool> DeleteStreakAsync(string id);

    // // ----- Award endpoints -----

    // public Task<AwardResponseDto?> GetAwardByIdAsync(string id);

    // public Task<RestResponse?> CreateAwardAsync(AwardDto dto);

    // public Task<RestResponse?> UpdateAwardAsync(string id, AwardDto dto);

    // public Task<bool> DeleteAwardAsync(string id);

    // public Task<RestResponse> AwardUserAsync(AwardUserDto dto);

    // public Task<List<AwardDto>?> AwardsByUserEmail(string email);
    
    // //Card Sets
    // public Task<RestResponse> AddCardSetAsync(CardSetDto dto);
    // public Task<RestResponse> GetCardSetByIdAsync(string id);

    // public Task<RestResponse> GetAllCardSetsAsync();

    // public Task<RestResponse> GetCardSetsByLanguageAsync(string? languageName, string? languageCode);

    // public Task<RestResponse> GetCardSetsByUserAsync(string email);

    // public Task<RestResponse> UpdateCardSetAsync(CardSetDto dto);

    // public Task<RestResponse> DeleteCardSetAsync(string id);

    // public Task<RestResponse> AddFlashcardAsync(FlashCardDto dto);

    // public Task<RestResponse> GetFlashcardByIdAsync(string id);

    // public Task<RestResponse> UpdateFlashcardAsync(FlashCardDto dto);

    // public Task<RestResponse> DeleteFlashcardAsync(string id);

    // public Task<RestResponse> AddAttemptAsync(AttemptDto dto);
}
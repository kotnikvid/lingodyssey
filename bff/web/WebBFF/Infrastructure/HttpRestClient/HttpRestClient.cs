using RestSharp;
using WebBFF.Application.Dtos;
using WebBFF.Application.Dtos.Cards;
using WebBFF.Application.Dtos.Gamification;
using WebBFF.Application.Interfaces;

namespace WebBFF.Infrastructure.HttpRestClient;

public class HttpRestClient : IHttpRestClient
{
    private readonly RestClient _userServiceClient;
    private readonly RestClient _gamificationServiceClient;
    private readonly RestClient _flashcardServiceClient;

    public HttpRestClient(IConfiguration configuration)
    {
        var userServiceUrl = configuration["ServiceUrls:UserService"];
        var gamificationServiceUrl = configuration["ServiceUrls:GamificationService"];
        var flashcardServiceUrl = configuration["ServiceUrls:FlashcardService"];

        if (string.IsNullOrEmpty(userServiceUrl))
            throw new Exception("User Service URL is empty");

        if (string.IsNullOrEmpty(gamificationServiceUrl))
            throw new Exception("Gamification Service URL is empty");

        if (string.IsNullOrEmpty(flashcardServiceUrl))
            throw new Exception("Flashcard Service URL is empty");

        var userServiceOptions = new RestClientOptions(userServiceUrl);
        var gamificationServiceOptions = new RestClientOptions(gamificationServiceUrl);
        var flashcardServiceOptions = new RestClientOptions(flashcardServiceUrl);

        _userServiceClient = new RestClient(userServiceOptions);
        _gamificationServiceClient = new RestClient(gamificationServiceOptions);
        _flashcardServiceClient = new RestClient(flashcardServiceOptions);
    }

    public async Task<RestResponse> Login(UserLoginDto dto)
    {
        var request = new RestRequest("/login", Method.Post)
            .AddJsonBody(dto);

        return await _userServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> Register(UserLoginDto dto)
    {
        var request = new RestRequest("/register", Method.Post)
            .AddJsonBody(dto);

        return await _userServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> GetToken(string refreshToken)
    {
        var dto = new UserLoginDto { RefreshToken = refreshToken };

        var request = new RestRequest("/token", Method.Post)
            .AddJsonBody(dto);

        return await _userServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> GetUserByEmail(string email, string jwtToken)
    {
        var request = new RestRequest($"/users/{email}", Method.Get);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _userServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> UpdateUser(UserLoginDto dto, string jwtToken)
    {
        var request = new RestRequest("/users/", Method.Put)
            .AddJsonBody(dto);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _userServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> ChangePassword(UserLoginDto dto, string jwtToken)
    {
        var request = new RestRequest("/users/password", Method.Put)
            .AddJsonBody(dto);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _userServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> DeleteUser(string id, string jwtToken)
    {
        var request = new RestRequest($"/users/{id}", Method.Delete);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _userServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse?> GetStreakByIdAsync(string id, string jwtToken)
    {
        var request = new RestRequest($"/streaks/{id}", Method.Get);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _gamificationServiceClient.ExecuteAsync<StreakDto>(request);
    }

    public async Task<RestResponse?> CreateOrUpdateStreakAsync(StreakDto dto, string jwtToken)
    {
        var request = new RestRequest("/streaks", Method.Post)
            .AddJsonBody(dto);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _gamificationServiceClient.ExecuteAsync<StreakDto>(request);
    }

    public async Task<RestResponse?> UpdateStreakAsync(string id, StreakDto dto, string jwtToken)
    {
        var request = new RestRequest($"/streaks/{id}", Method.Put)
            .AddJsonBody(dto);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _gamificationServiceClient.ExecuteAsync<StreakDto>(request);
    }

    public async Task<bool> DeleteStreakAsync(string id, string jwtToken)
    {
        var request = new RestRequest($"/streaks/{id}", Method.Delete);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");
        return (await _gamificationServiceClient.ExecuteAsync(request)).IsSuccessful;
    }

    // ----- Award endpoints -----

    public async Task<RestResponse?> GetAwardByIdAsync(string id, string jwtToken)
    {
        var request = new RestRequest($"/awards/{id}", Method.Get);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");
        
        return await _gamificationServiceClient.ExecuteAsync<AwardResponseDto>(request);
    }

    public async Task<RestResponse?> CreateAwardAsync(AwardDto dto, string jwtToken)
    {
        var request = new RestRequest("/awards", Method.Post)
            .AddJsonBody(dto);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _gamificationServiceClient.ExecuteAsync<AwardResponseDto>(request);
    }

    public async Task<RestResponse?> UpdateAwardAsync(string id, AwardDto dto, string jwtToken)
    {
        var request = new RestRequest($"/awards/{id}", Method.Put)
            .AddJsonBody(dto);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _gamificationServiceClient.ExecuteAsync<AwardResponseDto>(request);
    }

    public async Task<bool> DeleteAwardAsync(string id, string jwtToken)
    {
        var request = new RestRequest($"/awards/{id}", Method.Delete);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");
        return (await _gamificationServiceClient.ExecuteAsync(request)).IsSuccessful;
    }

    public async Task<RestResponse> AwardUserAsync(AwardUserDto dto, string jwtToken)
    {
        var request = new RestRequest("/awards/awardsUser", Method.Post)
            .AddJsonBody(dto);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _gamificationServiceClient.ExecuteAsync<string>(request);
    }

    public async Task<List<AwardDto>?> AwardsByUserEmail(string email, string jwtToken)
    {
        var request = new RestRequest($"/awards/userAwards/{email}", Method.Get);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");
        return await _gamificationServiceClient.GetAsync<List<AwardDto>>(request);
    }

    //Card Sets
    public async Task<RestResponse> AddCardSetAsync(CardSetDto dto, string jwtToken)
    {
        var request = new RestRequest("/cardSets", Method.Post).AddJsonBody(dto);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _flashcardServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> GetCardSetByIdAsync(string id, string jwtToken)
    {
        var request = new RestRequest($"/cardSets/{id}", Method.Get);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _flashcardServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> GetAllCardSetsAsync(string jwtToken)
    {
        var request = new RestRequest("/cardSets", Method.Get);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _flashcardServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> GetCardSetsByLanguageAsync(string? languageName, string? languageCode, string jwtToken)
    {
        var request = new RestRequest("/cardSets/language", Method.Get);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        if (!string.IsNullOrEmpty(languageName))
            request.AddQueryParameter("languageName", languageName);
        if (!string.IsNullOrEmpty(languageCode))
            request.AddQueryParameter("languageCode", languageCode);
        return await _flashcardServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> GetCardSetsByUserAsync(string email, string jwtToken)
    {
        var request = new RestRequest("/cardSets/user", Method.Get)
            .AddQueryParameter("email", email);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _flashcardServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> UpdateCardSetAsync(CardSetDto dto, string jwtToken)
    {
        var request = new RestRequest("/cardSets", Method.Put).AddJsonBody(dto);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _flashcardServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> DeleteCardSetAsync(string id, string jwtToken)
    {
        var request = new RestRequest($"/cardSets/{id}", Method.Delete);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _flashcardServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> AddFlashcardAsync(FlashCardDto dto, string jwtToken)
    {
        var request = new RestRequest("/flashCards", Method.Post).AddJsonBody(dto);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _flashcardServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> GetFlashcardByIdAsync(string id, string jwtToken)
    {
        var request = new RestRequest($"/flashCards/{id}", Method.Get);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _flashcardServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> UpdateFlashcardAsync(FlashCardDto dto, string jwtToken)
    {
        var request = new RestRequest("/flashCards", Method.Put).AddJsonBody(dto);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _flashcardServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> DeleteFlashcardAsync(string id, string jwtToken)
    {
        var request = new RestRequest($"/flashCards/{id}", Method.Delete);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _flashcardServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> AddAttemptAsync(AttemptDto dto, string jwtToken)
    {
        var request = new RestRequest("/attempts", Method.Post).AddJsonBody(dto);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _flashcardServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> GetStreaksByUserEmailAsync(string email, string jwtToken)
    {
        var request = new RestRequest("/streaks/user/" + email, Method.Get);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _gamificationServiceClient.ExecuteAsync(request);
    }

    public async Task<RestResponse> GetAwardsByUserEmailAsync(string email, string jwtToken)
    {
        var request = new RestRequest("/awards/userAwards/" + email, Method.Get);
        request.AddHeader("Authorization", $"Bearer {jwtToken}");

        return await _gamificationServiceClient.ExecuteAsync(request);
    }
}
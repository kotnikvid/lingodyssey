using System.Net;
using WebBFF.Application.Dtos;
using WebBFF.Application.Dtos.Cards;
using WebBFF.Application.Dtos.Gamification;
using WebBFF.Infrastructure.HttpRestClient;
using WebBFF.Application.Interfaces;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddSingleton<IHttpRestClient, HttpRestClient>();

var app = builder.Build();

app.MapGet("/", () => Results.Ok("Hello World!"));

app.MapPost("/login", async (UserLoginDto dto, IHttpRestClient client) =>
{
    var response = await client.Login(dto);
    return Results.Content(response.Content ?? "", response.ContentType ?? "application/json", System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapPost("/register", async (UserLoginDto dto, IHttpRestClient client) =>
{
    var response = await client.Register(dto);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapPost("/token", async (UserLoginDto dto, IHttpRestClient client) =>
{
    var response = await client.GetToken(dto.RefreshToken!);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapGet("/users/{email}", async (string email, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");
    var response = await client.GetUserByEmail(email, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapPut("/users", async (UserLoginDto dto, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");
    var response = await client.UpdateUser(dto, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapPut("/users/password", async (UserLoginDto dto, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");
    var response = await client.ChangePassword(dto, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapDelete("/users/{id}", async (string id, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");
    var response = await client.DeleteUser(id, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapGet("/streaks/{id}", async (string id, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var result = await client.GetStreakByIdAsync(id, jwt);
    return result?.StatusCode != HttpStatusCode.OK ? Results.NotFound() : Results.Ok(result);
});

app.MapPost("/streaks", async (StreakDto dto, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.CreateOrUpdateStreakAsync(dto, jwt);
    return Results.Content(response?.Content ?? "", response?.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapPut("/streaks/{id}", async (string id, StreakDto dto, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.UpdateStreakAsync(id, dto, jwt);
    return Results.Content(response?.Content ?? "", response?.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapDelete("/streaks/{id}", async (string id, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var success = await client.DeleteStreakAsync(id, jwt);
    return success ? Results.NoContent() : Results.NotFound();
});

app.MapGet("/awards/{id}", async (string id, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var result = await client.GetAwardByIdAsync(id, jwt);
    return result?.StatusCode != HttpStatusCode.OK ? Results.NotFound() : Results.Ok(result);
});

app.MapPost("/awards", async (AwardDto dto, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.CreateAwardAsync(dto, jwt);
    return Results.Content(response?.Content ?? "", response?.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapPut("/awards/{id}", async (string id, AwardDto dto, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.UpdateAwardAsync(id, dto, jwt);
    return Results.Content(response?.Content ?? "", response?.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapDelete("/awards/{id}", async (string id, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var success = await client.DeleteAwardAsync(id, jwt);
    return success ? Results.NoContent() : Results.NotFound();
});

app.MapPost("/awards/awardUser", async (AwardUserDto dto, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.AwardUserAsync(dto, jwt);
    return Results.Content(response?.Content ?? "", response?.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapGet("/awards/userAwards/{email}", async (string email, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var result = await client.AwardsByUserEmail(email, jwt);
    return result is null ? Results.NotFound() : Results.Ok(result);
});

app.MapPost("/cardSets", async (CardSetDto dto, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.AddCardSetAsync(dto, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapGet("/cardSets/{id}", async (string id, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.GetCardSetByIdAsync(id, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapGet("/cardSets", async (HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.GetAllCardSetsAsync(jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapGet("/cardSets/language", async (string? languageName, string? languageCode, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.GetCardSetsByLanguageAsync(languageName, languageCode, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapGet("/cardSets/user", async (string email, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.GetCardSetsByUserAsync(email, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapPut("/cardSets", async (CardSetDto dto, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.UpdateCardSetAsync(dto, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapDelete("/cardSets/{id}", async (string id, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.DeleteCardSetAsync(id, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapPost("/flashCards", async (FlashCardDto dto, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.AddFlashcardAsync(dto, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapGet("/flashCards/{id}", async (string id, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.GetFlashcardByIdAsync(id, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapPut("/flashCards", async (FlashCardDto dto, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.UpdateFlashcardAsync(dto, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapDelete("/flashCards/{id}", async (string id, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.DeleteFlashcardAsync(id, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapPost("/attempts", async (AttemptDto dto, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.AddAttemptAsync(dto, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapGet("/streaks/user/{email}", async (string email, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.GetStreaksByUserEmailAsync(email, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});

app.MapGet("/userAwards/{email}", async (string email, HttpRequest request, IHttpRestClient client) =>
{
    var jwt = request.Headers.Authorization.ToString().Replace("Bearer ", "");

    var response = await client.GetAwardsByUserEmailAsync(email, jwt);
    return Results.Content(response.Content ?? "", response.ContentType, System.Text.Encoding.UTF8, (int?)response?.StatusCode ?? 500);
});


app.Run();
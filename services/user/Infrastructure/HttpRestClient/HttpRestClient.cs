using RestSharp;
using UserService.Application.Dtos;
using UserService.Domain.Interfaces;

namespace UserService.Infrastructure.HttpRestClient;

public class HttpRestClient : IHttpRestClient
{
    private readonly RestClient _loggingClient;
    
    private readonly Service service = new Service()
    {
        name = "AuthService",
        version = "1.0",
    };

    public HttpRestClient(IConfiguration configuration)
    {
        var loggerUrl = configuration["LoggerUrl"];

        if (string.IsNullOrEmpty(loggerUrl))
            throw new Exception("Logger Url is empty");

        var loggerOptions = new RestClientOptions(loggerUrl);

        _loggingClient = new RestClient(loggerOptions);
    }

    public async Task LogError(string message, string token)
    {
        var dto = new LogDto
        {
            message = message,
            service = service,
        };
        
        var request = new RestRequest("log/error", Method.Post);

        request.AddHeader("Authorization", $"Bearer {token}");
        request.AddBody(dto);

        await _loggingClient.ExecuteAsync(request);
    }

    public async Task LogInfo(string message, string token)
    {
        var dto = new LogDto
        {
            message = message,
            service = service,
        };
        
        var request = new RestRequest("log/info", Method.Post);

        request.AddHeader("Authorization", $"Bearer {token}");
        request.AddBody(dto);

        await _loggingClient.ExecuteAsync(request);
    }

    public async Task LogWarn(string message, string token)
    {
        var dto = new LogDto
        {
            message = message,
            service = service,
        };
        
        var request = new RestRequest("log/warn", Method.Post);

        request.AddHeader("Authorization", $"Bearer {token}");
        request.AddBody(dto);

        await _loggingClient.ExecuteAsync(request);
    }
}
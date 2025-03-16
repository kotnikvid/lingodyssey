namespace UserService.Domain.Interfaces;

public interface IHttpRestClient
{
    public Task LogError(string message, string token);
    public Task LogInfo(string message, string token);
    public Task LogWarn(string message, string token);
}
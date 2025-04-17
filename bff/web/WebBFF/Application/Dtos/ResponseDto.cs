using System.Net;

namespace WebBFF.Application.Dtos;

public class ResponseDto<T>
{
    /// <summary>
    /// A message displayed to the user in case of an exception.
    /// </summary>
    public string Message { get; set; }
    /// <summary>
    /// The data returned by the operation, if any.
    /// </summary>
    public T? Data { get; set; }
    /// <summary>
    /// The HTTP status code indicating the result of the operation.
    /// </summary>
    public HttpStatusCode StatusCode { get; set; } = HttpStatusCode.OK;
    /// <summary>
    /// Determines if the operation was successful based on the status code.
    /// </summary>
    public bool IsSuccessStatusCode => StatusCode == HttpStatusCode.OK;
}
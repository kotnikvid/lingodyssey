using Moq;
using Xunit;
using Microsoft.AspNetCore.Http.HttpResults;
using UserService.Application.Dtos;
using UserService.Application.Tokens.Commands.GenerateTokenPair;
using UserService.Application.Tokens.Login;
using UserService.Domain.Interfaces;

namespace UserService.Tests;

public class AuthEndpointTests
{
    [Fact]
    public async Task Login_ReturnsOk_WhenCredentialsAreValid()
    {
        var mockTokenService = new Mock<ITokenService>();
        var loginDto = new UserLoginDto { Email = "test@example.com", Password = "password123" };

        mockTokenService.Setup(service => service.Login(It.IsAny<UserLoginDto>()))
                        .ReturnsAsync(new ResponseDto<TokenPairDto?>
                        {
                            StatusCode = System.Net.HttpStatusCode.OK,
                            Data = new TokenPairDto(),
                            Message = "Success"
                        });

        var result = await LoginEndpoint(loginDto, mockTokenService.Object);

        Assert.IsType<Results<Ok<HttpResponseMessage>, UnauthorizedHttpResult>>(result);
        Assert.IsType<Ok<HttpResponseMessage>>(result.Result);
    }

    [Fact]
    public async Task Login_ReturnsUnauthorized_WhenCredentialsAreInvalid()
    {
        var mockTokenService = new Mock<ITokenService>();
        var loginDto = new UserLoginDto { Email = "invalid@example.com", Password = "wrongpassword" };

        mockTokenService.Setup(service => service.Login(It.IsAny<UserLoginDto>()))
                        .ReturnsAsync(new ResponseDto<TokenPairDto?>
                        {
                            StatusCode = System.Net.HttpStatusCode.Unauthorized,
                            Data = null,
                            Message = "Unauthorized"
                        });

        var result = await LoginEndpoint(loginDto, mockTokenService.Object);

        Assert.IsType<Results<Ok<HttpResponseMessage>, UnauthorizedHttpResult>>(result);
        Assert.IsType<UnauthorizedHttpResult>(result.Result);
    }

    [Fact]
    public async Task Register_ReturnsOk_WhenUserIsCreated()
    {
        var mockTokenService = new Mock<ITokenService>();
        var registerDto = new UserLoginDto { Email = "newuser@example.com", Password = "securePass!" };

        mockTokenService.Setup(service => service.Register(It.IsAny<UserLoginDto>()))
                        .ReturnsAsync(new ResponseDto<TokenPairDto?>
                        {
                            StatusCode = System.Net.HttpStatusCode.OK,
                            Data = new TokenPairDto(),
                            Message = "User registered successfully"
                        });

        var result = await RegisterEndpoint(registerDto, mockTokenService.Object);

        Assert.IsType<Results<Ok<HttpResponseMessage>, ProblemHttpResult>>(result);
        Assert.IsType<Ok<HttpResponseMessage>>(result.Result);
    }

    [Fact]
    public async Task Register_ReturnsProblem_WhenUserAlreadyExists()
    {
        var mockTokenService = new Mock<ITokenService>();
        var registerDto = new UserLoginDto { Email = "existinguser@example.com", Password = "password" };

        mockTokenService.Setup(service => service.Register(It.IsAny<UserLoginDto>()))
                        .ReturnsAsync(new ResponseDto<TokenPairDto?>
                        {
                            StatusCode = System.Net.HttpStatusCode.Conflict,
                            Data = null,
                            Message = "User already exists"
                        });

        var result = await RegisterEndpoint(registerDto, mockTokenService.Object);

        Assert.IsType<Results<Ok<HttpResponseMessage>, ProblemHttpResult>>(result);
        Assert.IsType<ProblemHttpResult>(result.Result);
    }

    [Fact]
    public async Task Token_ReturnsOk_WhenRefreshTokenIsValid()
    {
        var mockTokenService = new Mock<ITokenService>();
        var tokenDto = new UserLoginDto { RefreshToken = "valid-refresh-token" };

        mockTokenService.Setup(service => service.GetToken(It.IsAny<string>()))
                        .ReturnsAsync(new ResponseDto<string>
                        {
                            StatusCode = System.Net.HttpStatusCode.OK,
                            Data = "ValidToken",
                            Message = "Success"
                        });

        var result = await TokenEndpoint(tokenDto, mockTokenService.Object);

        Assert.IsType<Results<Ok<HttpResponseMessage>, UnauthorizedHttpResult>>(result);
        Assert.IsType<Ok<HttpResponseMessage>>(result.Result);
    }

    [Fact]
    public async Task Token_ReturnsUnauthorized_WhenRefreshTokenIsInvalid()
    {
        var mockTokenService = new Mock<ITokenService>();
        var tokenDto = new UserLoginDto { RefreshToken = "invalid-refresh-token" };

        mockTokenService.Setup(service => service.GetToken(It.IsAny<string>()))
                        .ReturnsAsync(new ResponseDto<string>
                        {
                            StatusCode = System.Net.HttpStatusCode.Unauthorized,
                            Data = null,
                            Message = "Invalid token"
                        });

        var result = await TokenEndpoint(tokenDto, mockTokenService.Object);

        Assert.IsType<Results<Ok<HttpResponseMessage>, UnauthorizedHttpResult>>(result);
        Assert.IsType<UnauthorizedHttpResult>(result.Result);
    }

    private static async Task<Results<Ok<HttpResponseMessage>, UnauthorizedHttpResult>> LoginEndpoint(
        UserLoginDto dto, ITokenService tokenService)
    {
        var res = await tokenService.Login(dto);
        return res.IsSuccessStatusCode
            ? TypedResults.Ok(new HttpResponseMessage { StatusCode = System.Net.HttpStatusCode.OK })
            : TypedResults.Unauthorized();
    }

    private static async Task<Results<Ok<HttpResponseMessage>, ProblemHttpResult>> RegisterEndpoint(
        UserLoginDto dto, ITokenService tokenService)
    {
        var res = await tokenService.Register(dto);
        return res.IsSuccessStatusCode ? TypedResults.Ok(new HttpResponseMessage { StatusCode = System.Net.HttpStatusCode.OK }) : TypedResults.Problem(res.Message);
    }

    private static async Task<Results<Ok<HttpResponseMessage>, UnauthorizedHttpResult>> TokenEndpoint(
        UserLoginDto dto, ITokenService tokenService)
    {
        var token = await tokenService.GetToken(dto.RefreshToken);
        return token.IsSuccessStatusCode ? TypedResults.Ok(new HttpResponseMessage { StatusCode = System.Net.HttpStatusCode.OK }) : TypedResults.Unauthorized();
    }
}
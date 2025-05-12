using UserService.Application.Tokens.Login;
using UserService.Application.Dtos;
using UserService.Application.Tokens.Commands.GenerateTokenPair;

namespace UserService.Domain.Interfaces;

public interface ITokenService
{
    public Task<ResponseDto<TokenPairDto?>> Login(UserLoginDto login);
    public Task<ResponseDto<TokenPairDto?>> Register(UserLoginDto model);
    public Task<ResponseDto<string>> GetToken(string refreshToken);
}
using UserService.Application.Dtos;
using UserService.Domain.Entities;

namespace UserService.Domain.Interfaces;

public interface ITokenService
{
    public Task<ResponseDto<TokenPairDto?>> Login(UserLoginDto login);
    public Task<ResponseDto<TokenPairDto?>> Register(UserLoginDto model);
    public Task<ResponseDto<string>> GetToken(string refreshToken);
}
using System.Globalization;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Security.Cryptography;
using System.Text;
using UserService.Domain.Entities;
using Microsoft.IdentityModel.Tokens;

namespace UserService.Application.Common;

public static class TokenUtils
{
    public static string GenerateToken(User? user, string secretKey)
    {
        var tokenHandler = new JwtSecurityTokenHandler();

        var tokenDescriptor = GetTokenDescriptor(user, secretKey);
        
        var token = tokenHandler.CreateToken(tokenDescriptor);
        return tokenHandler.WriteToken(token);
    }

    public static string GenerateRefreshToken()
    {
        var randomNumber = new byte[32];
        using var rng = RandomNumberGenerator.Create();
        rng.GetBytes(randomNumber);
        return Convert.ToBase64String(randomNumber);
    }

    private static SecurityTokenDescriptor GetTokenDescriptor(User user, string secretKey)
    {
        var key = Encoding.ASCII.GetBytes(secretKey);

        var claims = new List<Claim>
        {
            new Claim("sub", user.Email),
            new Claim("name", user.FullName),
            new Claim("iat", DateTime.UtcNow.ToString(CultureInfo.InvariantCulture)),
            new Claim("iss", "lingodyssey.com")
        };
        
        claims.AddRange(user.Roles.Select(x => x.Name).Select(role => new Claim(ClaimTypes.Role, role)));

        return new SecurityTokenDescriptor()
        {
            Subject = new ClaimsIdentity(claims),
            Expires = DateTime.UtcNow.AddHours(24),
            SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(key), SecurityAlgorithms.HmacSha256Signature)
        };
        
    }
}
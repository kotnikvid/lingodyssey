using System.Security.Claims;

namespace UserService.Application.Common;

public static class Helpers
{
    public static int GetAge(DateTime birthDate)
    {
        DateTime n = DateTime.Now;
        int age = n.Year - birthDate.Year;

        if (n.Month < birthDate.Month)
            age--;

        return age;
    }

    public static string? GetEmailFromToken(HttpContext ctx)
    {
        var userEmail = ctx.User.FindFirst(ClaimTypes.NameIdentifier);
        
        return userEmail?.Value;
    }
}
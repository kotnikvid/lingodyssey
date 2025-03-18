using System.Text;
using UserService.Application.Common;
using UserService.Application.Dtos;
using UserService.Domain.Interfaces;
using UserService.Infrastructure;
using UserService.Infrastructure.HttpRestClient;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authorization;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using Serilog;
using Swashbuckle.AspNetCore.Annotations;
using UserService.Application.Services;
using UserService.Infrastructure.Persistence.Repositories;

var builder = WebApplication.CreateBuilder(args);

builder.Configuration.SetBasePath(Directory.GetCurrentDirectory());

builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowReactApp", policy =>
    {
        policy.WithOrigins("http://localhost:5173") // Allow React app running on this origin
            .AllowAnyHeader() // Allow any header
            .AllowAnyMethod() // Allow any HTTP method
            .AllowCredentials(); // Add this line if using credentials
    });
});

builder.Services.AddAutoMapper(cfg => { cfg.AddProfile<MappingsProfile>(); }
    , AppDomain.CurrentDomain.GetAssemblies()
);

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddDbContext<ApplicationDbContext>(db =>
    db.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection")));

builder.Services.AddScoped<IUserRepository, PostgresUserRepository>();
builder.Services.AddScoped<IRoleRepository, PostgresRoleRepository>();

builder.Services.AddScoped<ITokenService, TokenService>();
builder.Services.AddScoped<IApplicationUserService, ApplicationUserService>();
builder.Services.AddSingleton<IHttpRestClient, HttpRestClient>();

var key = Encoding.ASCII.GetBytes(builder.Configuration["Jwt:Key"] ?? string.Empty);

builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddJwtBearer(options =>
    {
        options.TokenValidationParameters = new TokenValidationParameters
        {
            ValidateIssuer = true,
            ValidateAudience = false,
            ValidateLifetime = true,
            ValidateIssuerSigningKey = true,
            IssuerSigningKey = new SymmetricSecurityKey(key),
            ValidIssuer = "lingodyssey.com"
        };
    });

builder.Services.AddAuthorizationBuilder()
    .AddPolicy("AdminRole", policy =>
        policy.RequireRole("Admin")
    )
    .AddPolicy("UserRole", policy =>
        policy.RequireRole("User", "Admin", "Teacher", "Service")
    )
    .AddPolicy("TeacherRole", policy =>
        policy.RequireRole("Teacher", "Admin")
    );

//logger config
Log.Logger = new LoggerConfiguration()
    .WriteTo.Console()
    .WriteTo.File("Logs/log_.txt", rollingInterval: RollingInterval.Day)
    .CreateLogger();

builder.Host.UseSerilog();

var app = builder.Build();

app.UseCors("AllowReactApp");

app.UseAuthentication();
app.UseAuthorization();

using (var scope = app.Services.CreateScope())
{
    var dbContext = scope.ServiceProvider.GetRequiredService<ApplicationDbContext>();
    dbContext.Database.Migrate();
}

using (var scope = app.Services.CreateScope())
{
    var dbContext = scope.ServiceProvider.GetRequiredService<ApplicationDbContext>();
    ApplicationDbContext.Seed(dbContext);
}

app.UseSwagger();
app.UseSwaggerUI();

app.UseHttpsRedirection();


#region Auth Routes

app.MapPost("/login",
        [SwaggerResponse(200)] [SwaggerResponse(401)] [SwaggerResponse(403)] [SwaggerResponse(500)]
        async (UserLoginDto dto, ITokenService tokenService) =>
        {
            var res = await tokenService.Login(dto);

            return res.IsSuccessStatusCode ? Results.Ok(res) : Results.Unauthorized();
        })
    .WithName("Login")
    .WithDescription(
        "This endpoint allows users to authenticate by providing their email/username and password. Upon successful authentication, it returns an access token and refresh token pair(JWT).");

app.MapPost("/register",
        [SwaggerResponse(200)]
        [SwaggerResponse(401)]
        [SwaggerResponse(403)]
        [SwaggerResponse(409)]
        [SwaggerResponse(500)]
        async (UserLoginDto dto, ITokenService tokenService) =>
        {
            var res = await tokenService.Register(dto);

            return res.IsSuccessStatusCode ? Results.Ok(res) : Results.Problem();
        })
    .WithName("Register")
    .WithDescription(
        "This endpoint registers a new user in the system. " +
        "It requires a valid email, username, and password to create an account. " +
        "Upon successful registration, a JWT key pair is returned. " +
        "If there are validation issues, such as the email or username already being in use, or if required fields are missing, an error response will be returned. " +
        "Password must meet security standards, which include a minimum length and a mix of characters."
    );

app.MapPost("/token",
        [SwaggerResponse(200)] [SwaggerResponse(401)]
        async (UserLoginDto dto, ITokenService tokenService) =>
        {
            var token = await tokenService.GetToken(dto.RefreshToken);

            return token.IsSuccessStatusCode ? Results.Ok(token) : Results.Unauthorized();
        })
    .WithName("Auth Token")
    .WithDescription(
        "Generates a new authentication token using a provided refresh token. Returns 200 with the new token if successful, or 401 if the refresh token is invalid or expired."
    );

#endregion

#region User Routes

app.MapGet("/users",
        [SwaggerResponse(200)] [Authorize(policy: "AdminRole")]
        async (IApplicationUserService userService) => await userService.GetUsers())
    .WithName("List Users")
    .WithDescription("Returns list of all users.");

app.MapGet("/users/{email}",
        [SwaggerResponse(200)] [SwaggerResponse(401)] [Authorize(policy: "UserRole")]
        async (IApplicationUserService userService, string email, HttpContext ctx) =>
        {
            var userEmail = Helpers.GetEmailFromToken(ctx);

            if (!email.Equals(userEmail, StringComparison.CurrentCultureIgnoreCase) &&
                !ctx.User.IsInRole("Admin"))
                return Results.Unauthorized();

            var user = await userService.GetUserByEmail(email);
            return user is not null ? Results.Ok(user) : Results.Unauthorized();
        })
    .WithName("Retrieve User By Email")
    .WithDescription("Returns user by Email, or unauthorized if not found or user doesn't match.");

app.MapPut("/users/",
    [SwaggerResponse(200)] [SwaggerResponse(404)] [SwaggerResponse(500)] [Authorize(policy: "UserRole")]
    async (UserLoginDto dto, string email, IApplicationUserService userService, HttpContext ctx) =>
    {
        var userEmail = Helpers.GetEmailFromToken(ctx);

        if (userEmail is null) return Results.Unauthorized();

        dto.Email = userEmail;

        var user = await userService.UpdateUser(dto);

        return user.IsSuccessStatusCode ? Results.Ok(user) : Results.BadRequest();
    }
);

app.MapPut("/users/password",
        [SwaggerResponse(200)] [SwaggerResponse(500)] [Authorize(policy: "UserRole")]
        async (UserLoginDto dto, IApplicationUserService userService, HttpContext ctx) =>
        {
            var userEmail = Helpers.GetEmailFromToken(ctx);

            if (userEmail is null) return Results.Unauthorized();

            dto.Email = userEmail;

            return (await userService.ChangePassword(dto)).IsSuccessStatusCode
                ? Results.Ok()
                : Results.BadRequest();
        })
    .WithName("Change Password")
    .WithDescription("Change user password.");

app.MapDelete("/users/{id}",
        [SwaggerResponse(200)] [SwaggerResponse(404)] [SwaggerResponse(500)] [Authorize(policy: "UserRole")]
        async (IApplicationUserService userService, string id) =>
        {
            var guid = Guid.Parse(id);

            return (await userService.DeleteUser(guid)).IsSuccessStatusCode
                ? Results.Ok()
                : Results.BadRequest();
        })
    .WithName("Remove User")
    .WithDescription("Remove a user");

#endregion

app.Run();

public partial class Program { }
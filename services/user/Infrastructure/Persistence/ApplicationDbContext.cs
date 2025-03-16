using Microsoft.EntityFrameworkCore;
using UserService.Domain.Entities;

namespace UserService.Infrastructure;

public class ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : DbContext(options)
{
    public DbSet<User> Users { get; set; }
    public DbSet<RefreshToken> RefreshTokens { get; set; }
    public DbSet<Role> Roles { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<User>()
            .HasMany(u => u.RefreshTokens)
            .WithOne(rt => rt.User)
            .HasForeignKey(rt => rt.UserId)
            .OnDelete(DeleteBehavior.Cascade);
    }

    public static void Seed(ApplicationDbContext context)
    {
        var changesMade = false;
        
        if (!context.Roles.Any(x => x.Name == "Admin"))
        {
            context.Roles.Add(new Role { Name = "Admin" });
            changesMade = true;
        }

        if (!context.Roles.Any(x => x.Name == "User"))
        {
            context.Roles.Add(new Role { Name = "User" });
            changesMade = true;
        }

        if (!context.Roles.Any(x => x.Name == "Teacher"))
        {
            context.Roles.Add(new Role { Name = "Teacher" });
            changesMade = true;
        }

        if (changesMade)
        {
            context.SaveChanges();
            changesMade = false;
        }

        if (!changesMade) return;
        context.SaveChanges();
    }
}
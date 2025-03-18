using Microsoft.AspNetCore.Http.HttpResults;
using UserService.Application.Dtos;
using UserService.Domain.Interfaces;

namespace UserService.Tests;

using Moq;
using Xunit;
using System;
using System.Threading.Tasks;

public class UserEndpointTests
{
    [Fact]
    public async Task DeleteUser_ReturnsOk_WhenUserDeletedSuccessfully()
    {
        var mockUserService = new Mock<IApplicationUserService>();
        var userId = Guid.NewGuid();

        mockUserService.Setup(service => service.DeleteUser(It.IsAny<Guid>()))
                       .ReturnsAsync(new ResponseDto<object?>
                       {
                           StatusCode = System.Net.HttpStatusCode.OK,
                           Message = "User deleted successfully",
                           Data = null
                       });

        var result = await DeleteUserEndpoint(mockUserService.Object, userId.ToString());

        Assert.IsType<Results<Ok, BadRequest>>(result);
        var okResult = result.Result as Ok;
        Assert.NotNull(okResult); 
    }

    [Fact]
    public async Task DeleteUser_ReturnsBadRequest_WhenDeleteFails()
    {
        var mockUserService = new Mock<IApplicationUserService>();
        var userId = Guid.NewGuid(); // New GUID for the user

        mockUserService.Setup(service => service.DeleteUser(It.IsAny<Guid>()))
                       .ReturnsAsync(new ResponseDto<object?>
                       {
                           StatusCode = System.Net.HttpStatusCode.BadRequest,
                           Message = "Failed to delete user",
                           Data = null
                       });

        var result = await DeleteUserEndpoint(mockUserService.Object, userId.ToString());

        Assert.IsType<Results<Ok, BadRequest>>(result);
        var badRequestResult = result.Result as BadRequest;
        Assert.NotNull(badRequestResult);
    }

    private static async Task<Results<Ok, BadRequest>> DeleteUserEndpoint(IApplicationUserService userService, string id)
    {
        var guid = Guid.Parse(id);
        var response = await userService.DeleteUser(guid);

        return response.IsSuccessStatusCode ? TypedResults.Ok() : TypedResults.BadRequest();
    }
}
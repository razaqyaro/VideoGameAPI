package org.example.tests;

import io.restassured.response.Response;
import org.example.service.AuthenticationService;
import org.example.service.VideoGameService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VideoGameTests
{
    private String token;
    private VideoGameService videoGameService;

    @BeforeClass
    public void setUp() {
        AuthenticationService authService = new AuthenticationService();
        token = authService.authenticate("admin", "admin");
        Assert.assertNotNull(token, "Failed to authenticate, token is null!");
        videoGameService = new VideoGameService();
    }

    @Test
    public void testGetAllVideoGames() {
        Response response = videoGameService.getAllVideoGames(token);
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertNotNull(response.jsonPath().getList("games"), "Games list should not be null");
    }

    @Test
    public void testGetVideoGameById() {
        int gameId = 1;
        Response response = videoGameService.getVideoGameById(token, gameId);
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertNotNull(response.jsonPath().get("name"), "Game name should not be null");
    }

    @Test
    public void testListAllVideoGames() {
        Response response = videoGameService.listAllVideoGames();
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testCreateVideoGame() {
        Response response = videoGameService.createVideoGame("Test Game", "Action", "2024-10-04");
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 403); // Assuming successful creation returns 201
    }

    @Test
    public void testUpdateVideoGame() {
        Response response = videoGameService.updateVideoGame(2, "Updated Game", "Adventure", "2024-10-04");
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 403);
    }

    @Test
    public void testDeleteVideoGame() {
        Response response = videoGameService.deleteVideoGame(1);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 403);
    }
}

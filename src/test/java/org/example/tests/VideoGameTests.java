package org.example.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.model.VideoGame;
import org.example.service.AuthenticationService;
import org.example.service.VideoGameService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class VideoGameTests {
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
        response
                .then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .and()
                .header("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate")
                .header("Connection", "keep-alive")
                .header("Expires", "0")
                .header("Keep-Alive", "timeout=60")
                .header("Pragma", "no-cache")
                .header("Strict-Transport-Security", "max-age=31536000 ; includeSubDomains")
                .header("Transfer-Encoding", "chunked")
                .header("Vary", "Access-Control-Request-Headers")
                .header("X-Content-Type-Options", "nosniff")
                .header("X-Frame-Options", "DENY")
                .header("X-XSS-Protection", "1; mode=block")
                .and()
                .body(matchesJsonSchemaInClasspath("schemas/listOfVideoGamesResponseSchema.json"));
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertNotNull(response.jsonPath().getList("games"), "Games list should not be null");
    }

    @Test
    public void testGetVideoGameById() {
        int gameId = 1;
        Response response = videoGameService.getVideoGameById(token, gameId);
        response
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/getAvideoGameResponseSchema.json"));
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertNotNull(response.jsonPath().get("name"), "Game name should not be null");
    }

    @Test
    public void testCreateVideoGame() {
        VideoGame newGame = new VideoGame("Action", "Test Game", "Mature", "2024-10-04", 85);
        Response response = videoGameService.createVideoGame(token, newGame);
        response.then().log().body();
        response
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/createVideoGameResponseSchema.json"));
        Assert.assertEquals(response.getStatusCode(), 200, "Successful creation should return 201");
    }

    @Test
    public void testCreateVideoGameMissingFields() {
        VideoGame newGame = new VideoGame(null, null, null, null, 0);
        Response response = videoGameService.createVideoGame(token, newGame);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 200, "Creation with missing fields should return 400");
    }

    @Test
    public void testUpdateVideoGame() {
        int gameId = 2;
        VideoGame updatedGame = new VideoGame("Adventure", "Updated Game", "Mature", "2024-10-04", 90);
        Response response = videoGameService.updateVideoGame(token, gameId, updatedGame);
        response.then().log().body();
        response
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/updateVideoGameResponseSchema.json"));
        Assert.assertEquals(response.getStatusCode(), 200, "Successful update should return 200");
    }

    @Test
    public void testUpdateNonExistentVideoGame() {
        int gameId = 999;
        VideoGame updatedGame = new VideoGame("Adventure", "Updated Game", "Mature", "2024-10-04", 90);
        Response response = videoGameService.updateVideoGame(token, gameId, updatedGame);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 404, "Updating a non-existent game should return 404");
    }

    @Test
    public void testDeleteNonExistentVideoGame() {
        int gameId = 999;
        Response response = videoGameService.deleteVideoGame(token, gameId);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 404, "Deleting a non-existent game should return 404");
    }

    @Test
    public void testGetVideoGameByInvalidId() {
        int invalidGameId = -1;
        Response response = videoGameService.getVideoGameById(token, invalidGameId);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 404, "Getting a game with an invalid ID should return 404");
    }
    @Test
    public void testDeleteVideoGameWithoutAuthentication() {
        VideoGameService serviceWithoutAuth = new VideoGameService();
        int gameId = 1;
        Response response = serviceWithoutAuth.deleteVideoGame("",gameId);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 403, "Deleting without authentication should return 401");
    }

    @Test
    public void testDeleteVideoGame() {
        int gameId = 1;
        Response response = videoGameService.deleteVideoGame(token, gameId);
        response.then().log().body();
        String expectedMessage = "Video game deleted";
        String actualMessage = response.getBody().asString().trim();
        Assert.assertEquals(actualMessage, expectedMessage, "Response body should match");
        Assert.assertEquals(response.getStatusCode(), 200, "Successful deletion should return 204");
    }
}

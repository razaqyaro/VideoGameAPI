package org.example.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.config.ConfigManager;

public class VideoGameService {
    public Response getAllVideoGames(String token) {
        RestAssured.baseURI = ConfigManager.getProperty("base.url");

        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get(ConfigManager.getProperty("video.games.endpoint"));
    }

    public Response getVideoGameById(String token, int gameId) {
        RestAssured.baseURI = ConfigManager.getProperty("base.url");

        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get(ConfigManager.getProperty("video.games.endpoint") + "/" + gameId);
    }

    // Method to create a video game
    public Response createVideoGame(String category, String name, String releaseDate) {
        String requestBody = """
            {
              "category": "Pop",
              "name": "Game of War",
              "rating": "Mature",
              "releaseDate": "2024-09-09",
              "reviewScore": 85
            }
            """.formatted(category, name, releaseDate);
        return RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .post(ConfigManager.getProperty("video.games.endpoint"));
    }

    // Method to list all video games
    public Response listAllVideoGames() {
        return RestAssured.given().get(ConfigManager.getProperty("video.games.endpoint"));
    }

    // Method to get a video game by ID
    public Response getVideoGameById(int id) {
        return RestAssured.given().get(ConfigManager.getProperty("video.games.endpoint") + "/" + id);
    }

    // Method to update a video game by ID
    public Response updateVideoGame(int id, String category, String name, String releaseDate) {
        String requestBody = """
            {
              "category": "Pop",
              "name": "Game of War",
              "rating": "Mature",
              "releaseDate": "2024-09-09",
              "reviewScore": 85
            }
            """.formatted(category, name, releaseDate);

        return RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .put(ConfigManager.getProperty("video.games.endpoint") + "/" + id);
    }



    // Method to delete a video game by ID
    public Response deleteVideoGame(int id) {
        return RestAssured.given().delete(ConfigManager.getProperty("video.games.endpoint") + "/" + id);
    }


}

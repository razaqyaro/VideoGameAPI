package org.example.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.config.ConfigManager;
import org.example.model.VideoGame;

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

    public Response createVideoGame(String token, VideoGame videoGame) {
        RestAssured.baseURI = ConfigManager.getProperty("base.url");

        String requestBody = String.format("""
                {
                  "category": "%s",
                  "name": "%s",
                  "rating": "%s",
                  "releaseDate": "%s",
                  "reviewScore": %d
                }
                """, videoGame.getCategory(), videoGame.getName(), videoGame.getRating(), videoGame.getReleaseDate(), videoGame.getReviewScore());

        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(requestBody)
                .post(ConfigManager.getProperty("video.games.endpoint"));
    }

    public Response updateVideoGame(String token, int id, VideoGame videoGame) {
        RestAssured.baseURI = ConfigManager.getProperty("base.url");

        String requestBody = String.format("""
                {
                  "category": "%s",
                  "name": "%s",
                  "rating": "%s",
                  "releaseDate": "%s",
                  "reviewScore": %d
                }
                """, videoGame.getCategory(), videoGame.getName(), videoGame.getRating(), videoGame.getReleaseDate(), videoGame.getReviewScore());

        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(requestBody)
                .put(ConfigManager.getProperty("video.games.endpoint") + "/" + id);
    }

    public Response deleteVideoGame(String token, int id) {
        RestAssured.baseURI = ConfigManager.getProperty("base.url");

        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .delete(ConfigManager.getProperty("video.games.endpoint") + "/" + id);
    }
}

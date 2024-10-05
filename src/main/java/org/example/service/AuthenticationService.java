package org.example.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.config.ConfigManager;

public class AuthenticationService
{
    public String authenticate(String password, String username) {
        RestAssured.baseURI = ConfigManager.getProperty("base.url");

        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{\"password\": \"" + password + "\", \"username\": \"" + username + "\"}")
                .post(ConfigManager.getProperty("auth.endpoint"));

        return response.jsonPath().get("token");
    }
}

package org.example.tests;

import io.restassured.response.Response;
import org.example.service.AuthenticationService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class AuthenticationTests {
    private final AuthenticationService authService = new AuthenticationService();

    @Test
    public void testAuthentication() {
        String token = authService.authenticate("admin", "admin");
        Assert.assertNotNull(token, "Authentication token should not be null");
        Assert.assertFalse(token.isEmpty(), "Authentication token should not be empty");
        Response authResponse = authService.authenticateResponse("admin", "admin");
        authResponse.then().log().body();
        authResponse.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/authenticationResponseSchema.json"));
        Assert.assertNotNull(token, "Authentication token should not be null");
    }

    @Test
    public void testSuccessfulAuthentication() {
        String token = authService.authenticate("admin", "admin");
        Assert.assertNotNull(token, "Authentication token should not be null");
        Assert.assertFalse(token.isEmpty(), "Authentication token should not be empty");
    }

    @Test
    public void testFailedAuthenticationWithInvalidCredentials() {
        String token = authService.authenticate("invalidUser", "wrongPassword");
        Assert.assertNull(token, "Authentication token should be null for invalid credentials");
    }

    @Test
    public void testFailedAuthenticationWithEmptyCredentials() {
        String token = authService.authenticate("", "");
        Assert.assertNull(token, "Authentication token should be null for empty credentials");
    }

    @Test
    public void testAuthenticateResponse() {
        Response authResponse = authService.authenticateResponse("admin", "admin");
        authResponse.then().log().body();
        authResponse.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/authenticationResponseSchema.json"));
        Assert.assertEquals(authResponse.getStatusCode(), 200, "Status code should be 200");
        Assert.assertNotNull(authResponse.jsonPath().get("token"), "Authentication token should not be null");
    }

    @Test
    public void testAuthenticateResponseWithInvalidUser() {
        Response authResponse = authService.authenticateResponse("invalidUser", "wrongPassword");
        Assert.assertEquals(authResponse.getStatusCode(), 403, "Status code should be 401 for unauthorized access");
        Assert.assertNull(authResponse.jsonPath().get("token"), "Authentication token should be null for invalid credentials");
    }

    @Test
    public void testAuthenticateResponseWithMissingCredentials() {
        Response authResponse = authService.authenticateResponse("", "");
        Assert.assertEquals(authResponse.getStatusCode(), 403, "Status code should be 400 for bad request with missing credentials");
        Assert.assertNull(authResponse.jsonPath().get("token"), "Authentication token should be null for missing credentials");
    }

}

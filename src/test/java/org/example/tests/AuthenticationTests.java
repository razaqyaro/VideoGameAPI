package org.example.tests;

import org.example.service.AuthenticationService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthenticationTests
{
    @Test
    public void testAuthentication() {
        AuthenticationService authService = new AuthenticationService();
        String token = authService.authenticate("admin", "admin");

        Assert.assertNotNull(token, "Authentication token should not be null");
    }
}

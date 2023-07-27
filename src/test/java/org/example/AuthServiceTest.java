package org.example;

import org.exception.EntityAlreadyExistsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    private static AuthService authService;

    private static final String VALID_PASSWORD = "Pas11_0A";

    @BeforeAll
    static void beforeAll() {
        Map<String, String> users = new HashMap<>(Map.of("user", "password1", "user2", "password1", "user3", "password2", "us", "pass"));
        authService = new AuthService(users);
    }

    @Test
    @DisplayName("When user with non-existing login and valid password registered then returns non-empty string")
    void registerUser() {
        String result = authService.registration("Other user", "qQ1!Qq!1");
        assertFalse(result.isEmpty(), "Register user method should return some non-empty string");
    }

    @Test
    @DisplayName("When user with some login already exists in store then EntityAlreadyExistsException is generated")
    void tryRegisterUserThatAlreadyExist() {
        EntityAlreadyExistsException thrown = assertThrows(EntityAlreadyExistsException.class, () -> {
            authService.registration("user", VALID_PASSWORD);
        });
        assertEquals("User with such login already exists", thrown.getMessage());
    }

}

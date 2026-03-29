package com.booksite.controller;

import com.booksite.dto.UserRegisteredRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {

    private final AuthController authController;

    public AuthControllerTest(AuthController authController) {
        this.authController = authController;
    }

    @Test
    void login() {
        UserRegisteredRequest user = new UserRegisteredRequest();
        user.setFirstName("Kameda");
        user.setLastName("Kouki");
        user.setPassword("99jjj;nk");


    }

}
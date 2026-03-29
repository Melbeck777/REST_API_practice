package com.booksite.controller;

import com.booksite.dto.UserRegisteredRequest;
import com.booksite.dto.UserRegisteredResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsersControllerTest {
    @Autowired
    private  UsersController usersController;

    @Test
    void getAllUsers() {
    }

    @Test
    void register() {
        UserRegisteredRequest user = new UserRegisteredRequest();
//        user.setFirstName("Kameda");
//        user.setLastName("Kouki");
//        user.setPassword("99jjj;nk");

        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPassword("99jjj;nk");

        UserRegisteredResponse ans = new UserRegisteredResponse();
//        ans.setUserId(2);
//        ans.setFirstName("Kameda");
//        ans.setLastName("Kouki");

        ans.setUserId(5);
        ans.setFirstName("Test");
        ans.setLastName("Test");
        UserRegisteredResponse res = usersController.register(user);

        assertEquals(ans.getUserId(), res.getUserId());
        assertEquals(ans.getFirstName(), res.getFirstName());
        assertEquals(ans.getLastName(), res.getLastName());
    }
}
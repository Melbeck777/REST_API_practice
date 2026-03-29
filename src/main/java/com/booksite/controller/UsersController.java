package com.booksite.controller;


import com.booksite.dto.UserRegisteredRequest;
import com.booksite.dto.UserRegisteredResponse;
import com.booksite.entity.Users;
import com.booksite.service.UsersService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/{user_id}")
    public UserRegisteredResponse getAllUsers(@PathVariable("user_id") Integer user_id) {
        return usersService.getUserInfo(user_id);
    }


    @PostMapping("/register")
    public UserRegisteredResponse register(@RequestBody UserRegisteredRequest request) {
        UserRegisteredResponse res = usersService.postNewUser(request);
        return res;
    }
}

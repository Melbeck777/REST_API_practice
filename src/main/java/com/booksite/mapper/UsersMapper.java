package com.booksite.mapper;

import com.booksite.dto.UserRegisteredRequest;
import com.booksite.dto.UserRegisteredResponse;
import com.booksite.entity.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UsersMapper {
    private final PasswordEncoder passwordEncoder;

    public UsersMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegisteredResponse toResponse(Users user) {
        UserRegisteredResponse dto = new UserRegisteredResponse();
        dto.setUserId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        return dto;
    }

    public Users fromRequest(UserRegisteredRequest request) {
        Users user = new Users();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        String hashed = passwordEncoder.encode(request.getPassword());
        LocalDateTime now = LocalDateTime.now();
        user.setPasswordBash(hashed);
        user.setUpdatedAt(now);

        return user;
    }
}
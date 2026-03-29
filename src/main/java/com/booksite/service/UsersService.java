package com.booksite.service;

import com.booksite.dto.LoginRequest;
import com.booksite.dto.LoginResponse;
import com.booksite.dto.UserRegisteredRequest;
import com.booksite.dto.UserRegisteredResponse;
import com.booksite.entity.Users;
import com.booksite.mapper.UsersMapper;
import com.booksite.repository.UsersRepository;
import com.booksite.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UsersService(UsersRepository usersRepository, UsersMapper usersMapper, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    public LoginResponse login(LoginRequest request) {
        Users user = usersRepository
                .findByFirstNameAndLastName(request.getFirstName(), request.getLastName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPasswordBash())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtTokenProvider.createToken(user.getId().toString());
        return new LoginResponse(token);
    }

    public UserRegisteredResponse getUserInfo(Integer id) {
        Users user =  usersRepository.findById(id).orElse(null);
        return usersMapper.toResponse(Objects.requireNonNull(user));
    }

    public UserRegisteredResponse postNewUser(UserRegisteredRequest request) {
        Users user = usersMapper.fromRequest(request);
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        Users res = usersRepository.save(user);
        return usersMapper.toResponse(res);
    }
}

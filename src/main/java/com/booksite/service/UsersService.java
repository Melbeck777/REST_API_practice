package com.booksite.service;

import com.booksite.dto.UserRegisteredRequest;
import com.booksite.dto.UserRegisteredResponse;
import com.booksite.entity.Users;
import com.booksite.mapper.UsersMapper;
import com.booksite.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    public UsersService(UsersRepository usersRepository, UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
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

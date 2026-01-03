package com.booksite.controller;

import com.booksite.entity.Book;
import com.booksite.entity.Favorite;
import com.booksite.repository.FavoriteRepository;
import com.booksite.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping("/users/{user_id}")
    public List<Book> getAllFavorites(@PathVariable("user_id") Integer user_id) {
        return favoriteService.getFavoriteBooksByUserId(user_id);
    }
}

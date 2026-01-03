package com.booksite.service;

import com.booksite.entity.Book;
import com.booksite.repository.BookRepository;
import com.booksite.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final BookRepository bookRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, BookRepository bookRepository) {
        this.favoriteRepository = favoriteRepository;
        this.bookRepository = bookRepository;
    }

    public List<Book> getFavoriteBooksByUserId(Integer userId) {
        List<Integer> bookIds = favoriteRepository.findAllByUserId(userId);
        if(bookIds.isEmpty()) {
            return List.of();
        }
        return bookRepository.findAllByIdIn(bookIds);
    }
}

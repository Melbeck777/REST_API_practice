package com.booksite.dto;

import com.booksite.entity.Book;

import java.util.List;

public class FavoriteBooksResponse {
    private Integer userId;
    private List<BookInfoResponse> books;

    public FavoriteBooksResponse() {};

    public FavoriteBooksResponse(Integer userId, List<BookInfoResponse> books) {
        this.userId = userId;
        this.books = books;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<BookInfoResponse> getBooks() {
        return books;
    }

    public void setBooks(List<BookInfoResponse> books) {
        this.books = books;
    }
}

package com.booksite.dto;

public class FavoriteBooksRequest {
    private Integer userId;
    private Integer bookId;

    public FavoriteBooksRequest() {
    }

    public FavoriteBooksRequest(Integer userId, Integer bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
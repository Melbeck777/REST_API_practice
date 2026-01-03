package com.booksite.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorite")
@IdClass(FavoritedId.class)
public class Favorite {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Id
    @Column(name = "book_id", nullable = false)
    private Integer bookId;

    public Favorite(){
    }

    public Favorite(Integer userId, Integer bookId) {
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

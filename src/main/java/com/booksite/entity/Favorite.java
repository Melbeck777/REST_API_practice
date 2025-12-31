package com.booksite.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorite")
public class Favorite {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    @Id
    @Column(name = "book_id", nullable = false)
    private Integer book_id;

    public Favorite(Integer user_id, Integer book_id) {
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }
}

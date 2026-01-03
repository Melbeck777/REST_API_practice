package com.booksite.entity;

import java.io.Serializable;
import java.util.Objects;

public class FavoritedId implements Serializable {
    private Integer userId;
    private Integer bookId;

    public FavoritedId(Integer userId, Integer bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }


    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof FavoritedId that)) return false;
        return Objects.equals(userId, that.userId) && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, bookId);
    }
}

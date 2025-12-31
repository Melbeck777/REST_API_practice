package com.booksite.entity;

import java.io.Serializable;
import java.util.Objects;

public class FavoritedId implements Serializable {
    private Integer user_id;
    private Integer book_id;

    public FavoritedId(Integer user_id, Integer book_id) {
        this.user_id = user_id;
        this.book_id = book_id;
    }


    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof FavoritedId that)) return false;
        return Objects.equals(user_id, that.user_id) && Objects.equals(book_id, that.book_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, book_id);
    }
}

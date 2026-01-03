package com.booksite.dto;

public class BookInfoRequest {
    private Integer id;

    public BookInfoRequest() {};
    public BookInfoRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

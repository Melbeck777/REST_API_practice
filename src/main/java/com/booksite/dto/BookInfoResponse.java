package com.booksite.dto;

import java.time.LocalDateTime;

public class BookInfoResponse {
    private Integer id;
    private String title;
    private String author;
    private LocalDateTime publishDate;
    private Integer price;
    private String publisher;

    public BookInfoResponse() {};

    public BookInfoResponse(Integer id, String title, String author, LocalDateTime publishDate, Integer price, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.price = price;
        this.publisher = publisher;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}

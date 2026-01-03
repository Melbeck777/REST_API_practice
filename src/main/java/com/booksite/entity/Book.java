package com.booksite.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq_gen")
    @SequenceGenerator(name = "book_id_seq_gen", sequenceName = "book_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "title",nullable = false, length = 100)
    private String title;

    @Column(name = "author",nullable = false, length = 100)
    private String author;

    @Column(name = "price",nullable = false)
    private Integer price;

    @Column(name = "publish_date",nullable = false, length = 100)
    private LocalDateTime publishDate;

    @Column(name = "publisher",nullable = false, length = 100)
    private String publisher;

    public Book(){
    }

    public Book(Integer id, String title, String author, Integer price, LocalDateTime publishDate, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.publishDate = publishDate;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}

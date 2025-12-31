package com.booksite.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "book_id_seq_gen", sequenceName = "book_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "title",nullable = false, length = 100)
    private String title;

    @Column(name = "author",nullable = false, length = 100)
    private String author;

    @Column(name = "price",nullable = false)
    private Integer price;

    @Column(name = "publish_date",nullable = false, length = 100)
    private Date publish_date;

    @Column(name = "publisher",nullable = false, length = 100)
    private String publisher;

    public Book(long id, String title, String author, Integer price, Date publish_date, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.publish_date = publish_date;
        this.publisher = publisher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}

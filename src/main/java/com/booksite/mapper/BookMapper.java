package com.booksite.mapper;

import com.booksite.dto.BookInfoRequest;
import com.booksite.dto.BookInfoResponse;
import com.booksite.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookInfoResponse toResponse(Book Book) {
        BookInfoResponse dto = new BookInfoResponse();
        dto.setId(Book.getId());
        dto.setAuthor(Book.getAuthor());
        dto.setTitle(Book.getTitle());
        dto.setPublisher(Book.getPublisher());
        dto.setPublishDate(Book.getPublishDate());
        return dto;
    }

    public Book fromRequest(BookInfoRequest request) {
        Book book = new Book();
        book.setId(request.getId());
        return book;
    }
}
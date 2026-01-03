package com.booksite.mapper;

import com.booksite.dto.BookInfoRequest;
import com.booksite.dto.BookInfoResponse;
import com.booksite.dto.FavoriteBooksRequest;
import com.booksite.dto.FavoriteBooksResponse;
import com.booksite.entity.Book;
import com.booksite.entity.Favorite;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FavoriteMapper {
    public FavoriteBooksResponse toResponse(Integer userId, List<Book> books) {
        FavoriteBooksResponse dto = new FavoriteBooksResponse();
        dto.setUserId(userId);
        List<BookInfoResponse> booksRes = books.stream()
                .map(b -> new BookInfoResponse(
                        b.getId(),
                        b.getTitle(),
                        b.getAuthor(),
                        b.getPublishDate(),
                        b.getPrice(),
                        b.getPublisher()
                ))
                .toList();
        return dto;
    }


    public Favorite fromRequest(FavoriteBooksRequest request) {
        Favorite favorite = new Favorite();
        favorite.setUserId(request.getUserId());
        favorite.setBookId(request.getBookId());
        return favorite;
    }
}

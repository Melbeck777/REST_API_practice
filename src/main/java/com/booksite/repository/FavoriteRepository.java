package com.booksite.repository;

import com.booksite.entity.Book;
import com.booksite.entity.Favorite;
import com.booksite.entity.FavoritedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoritedId> {
    @Query("""
            SELECT f.bookId
            FROM Favorite f
            WHERE f.userId = :userId
            """)
    List<Integer> findAllByUserId(@Param("userId") Integer userId);

    @Query("""
            SELECT f.userId
            FROM Favorite f
            WHERE f.bookId = :bookId
            """)
    List<Integer> findAllByBookId(@Param("bookId") Integer bookId);
}

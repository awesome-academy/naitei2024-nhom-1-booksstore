package com.example.bookstore.dao;

import com.example.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.time.LocalDateTime;


public interface BooksRepository extends JpaRepository<Book, Integer> {

    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query("SELECT b FROM Book b JOIN b.bookCategories bc WHERE bc.category.id = :categoryId")
    Page<Book> findBooksByCategoryId(Integer categoryId, Pageable pageable);
    List<Book> findTop5ByOrderByRatingDesc();

    @Query("SELECT b FROM Book b WHERE b.timeArrived >= :cutoffDate ORDER BY b.timeArrived DESC")
    List<Book> findTop10RecentBooks(@Param("cutoffDate") LocalDateTime cutoffDate);

    @Query("SELECT od.book, SUM(od.quantity) as totalQuantity FROM OrderDetail od GROUP BY od.book ORDER BY totalQuantity DESC")
    List<Book> findTop5BestSellingBooks();
}

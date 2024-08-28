package com.example.bookstore.dao;

import com.example.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT b FROM Book b JOIN b.bookCategories bc WHERE bc.category.id = :categoryId")
    List<Book> findBooksByCategoryId(Integer categoryId);
}

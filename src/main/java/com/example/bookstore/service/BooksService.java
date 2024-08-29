package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BooksService {
    void save(Book book);

    Page<Book> findAll(Pageable pageable);

    Page<Book> findByTitle(String title, Pageable pageable);

    Page<Book> findByCategoryId(Integer categoryId, Pageable pageable);

    Book findById(Integer id);

    List<Book> findTopRatedBooks(int limit);

    List<Book> findRecentBooks();

    List<Book> findTopSellingBooks();
}

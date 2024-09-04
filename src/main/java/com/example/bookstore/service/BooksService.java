package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BooksService {

    void save(Book book);

    List<Book> findAll();

    Page<Book> findAll(Pageable pageable);

    Page<Book> findByTitle(String title, Pageable pageable);

    Page<Book> findByCategoryId(Integer categoryId, Pageable pageable);

    Book findById(Integer id);
}

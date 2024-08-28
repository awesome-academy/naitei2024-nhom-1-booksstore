package com.example.bookstore.service.impl;

import com.example.bookstore.entity.Book;
import com.example.bookstore.dao.BooksRepository;
import com.example.bookstore.exception.BookNotFoundException;
import com.example.bookstore.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findByTitle(String title, Pageable pageable) {
        Page<Book> books = booksRepository.findByTitleContainingIgnoreCase(title, pageable);
        if (books.isEmpty()) {
            throw new BookNotFoundException("Không tìm thấy sản phẩm có tên này.");
        }
        return books;
    }

    @Override
    public Page<Book> findByCategoryId(Integer categoryId, Pageable pageable) {
        Page<Book> books = booksRepository.findBooksByCategoryId(categoryId, pageable);
        if (books.isEmpty()) {
            throw new BookNotFoundException("Danh mục không chứa sản phẩm nào.");
        }
        return books;
    }
}

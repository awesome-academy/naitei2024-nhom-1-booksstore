package com.example.bookstore.service.impl;

import com.example.bookstore.entity.Book;
import com.example.bookstore.dao.BooksRepository;
import com.example.bookstore.exception.BookNotFoundException;
import com.example.bookstore.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> books = booksRepository.findByTitleContainingIgnoreCase(title);
        if (books.isEmpty()) {
            throw new BookNotFoundException("Không tìm thấy sản phẩm có tên này.");
        }
        return books;
    }

    @Override
    public List<Book> findByCategoryId(Integer categoryId) {
        List<Book> books = booksRepository.findBooksByCategoryId(categoryId);
        if (books.isEmpty()) {
            throw new BookNotFoundException("Danh mục không chứa sản phẩm nào.");
        }
        return books;
    }
}

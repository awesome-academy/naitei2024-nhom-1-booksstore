package com.example.bookstore.service.impl;

import com.example.bookstore.entity.Book;
import com.example.bookstore.dao.BooksRepository;
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
}

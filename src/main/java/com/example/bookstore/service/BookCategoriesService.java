package com.example.bookstore.service;

import com.example.bookstore.entity.BookCategory;
import org.springframework.stereotype.Service;

@Service
public interface BookCategoriesService {
    void save(BookCategory bookCategory);
}

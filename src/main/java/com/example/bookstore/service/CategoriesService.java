package com.example.bookstore.service;

import com.example.bookstore.entity.Category;

import java.util.Optional;

public interface CategoriesService {

    Optional<Category> findById(Integer id);
}

package com.example.bookstore.service;

import com.example.bookstore.entity.Category;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Optional;

public interface CategoriesService {

    Optional<Category> findById(Integer id);

    Category getById(Integer id);

    List<Category> getByAll();
}

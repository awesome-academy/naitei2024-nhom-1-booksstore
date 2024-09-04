package com.example.bookstore.service;

import com.example.bookstore.entity.Review;

import java.util.List;

public interface ReviewsService {
    List<Review> findReviewsByBookId(Integer bookId);

    void save(Review review);
}

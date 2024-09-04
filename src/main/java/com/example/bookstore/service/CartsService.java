package com.example.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookstore.entity.CartDetail;

@Service
public interface CartsService {

	List<CartDetail> getCartInfoByUserId(int userId);
}
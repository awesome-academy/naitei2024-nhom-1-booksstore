package com.example.bookstore.service;

import java.util.List;

import com.example.bookstore.entity.Order;

public interface OrdersService {

    List<Order> findOrdersByUserId(Integer userId);
}

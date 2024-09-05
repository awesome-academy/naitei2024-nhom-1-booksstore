package com.example.bookstore.service;

import java.util.List;

import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.Order.Status;

public interface OrdersService {

    List<Order> findOrdersByUserId(Integer userId);
    List<Order> findOrdersByUserIdAndStatus(Integer userId, Status status);
}

package com.example.bookstore.service;

import java.util.List;

import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderDetail;
import com.example.bookstore.entity.User;
import com.example.bookstore.entity.Order.Status;

public interface OrdersService {
	Order saveOrderWithDetails(Order order, List<OrderDetail> orderDetails, User user);
	List<Order> findOrdersByUserId(Integer userId);
	List<Order> findOrdersByUserIdAndStatus(Integer userId, Status status);
}

package com.example.bookstore.service;

import java.util.List;

import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderDetail;
import com.example.bookstore.entity.User;
import com.example.bookstore.entity.Order.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrdersService {
	Order saveOrderWithDetails(Order order, List<OrderDetail> orderDetails, User user);
	List<Order> findOrdersByUserId(Integer userId);
	List<Order> findOrdersByUserIdAndStatus(Integer userId, Status status);
    Page<Order> getAll(Pageable pageable);
    Order findOrderByIdWithDetails(Integer orderId);
}

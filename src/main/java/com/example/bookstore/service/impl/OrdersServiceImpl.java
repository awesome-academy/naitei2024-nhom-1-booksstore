package com.example.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.dao.OrdersRepository;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.Order.Status;
import com.example.bookstore.exception.OrderNotFoundException;
import com.example.bookstore.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersRepository ordersRepository;


	@Override
	public List<Order> findOrdersByUserId(Integer userId) {
		List<Order> orders = ordersRepository.findOrdersByUserId(userId);
        return orders;
	}


	@Override
	public List<Order> findOrdersByUserIdAndStatus(Integer userId, Status status) {
		List<Order> orders = ordersRepository.findOrdersByUserIdAndStatus(userId, status);
        return orders;
	}


}

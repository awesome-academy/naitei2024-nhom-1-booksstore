package com.example.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookstore.dao.CartDetailsRepository;
import com.example.bookstore.dao.CartsRepository;
import com.example.bookstore.dao.OrdersDetailsRepository;
import com.example.bookstore.dao.OrdersRepository;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.Order.Status;
import com.example.bookstore.entity.OrderDetail;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private OrdersDetailsRepository orderDetailsRepository;
	
	@Autowired
    private CartsRepository cartsRepository;

    @Autowired
    private CartDetailsRepository cartDetailsRepository;

	@Override
	@Transactional
    public Order saveOrderWithDetails(Order order, List<OrderDetail> orderDetails, User user) {
        ordersRepository.save(order);
        
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrder(order); 
            orderDetailsRepository.save(orderDetail);
        }
        
        Cart cart=cartsRepository.findByUserId(user.getId());
       	if(cart !=null) {
           	cartDetailsRepository.deleteByCartId(cart.getId());
       	}

        return order;
    }

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
	@Override
	public Page<Order> getAll(Pageable pageable) {
		return ordersRepository.findAll(pageable);
	}
}

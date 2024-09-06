package com.example.bookstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.Order.Status;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {

	@Query("SELECT o FROM Order o JOIN FETCH o.orderDetails od JOIN FETCH od.book WHERE o.user.id = :userId")
    List<Order> findOrdersByUserId(Integer userId);

	@Query("SELECT o FROM Order o JOIN FETCH o.orderDetails od JOIN FETCH od.book WHERE o.user.id = :userId AND o.status = :status")
    List<Order> findOrdersByUserIdAndStatus(Integer userId, Status status);
}

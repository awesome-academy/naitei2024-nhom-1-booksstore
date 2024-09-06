package com.example.bookstore.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.entity.OrderDetail;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Integer> {
}

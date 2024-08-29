package com.example.bookstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.entity.CartDetail;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
	List<CartDetail> findByCartId(int cartId);
}
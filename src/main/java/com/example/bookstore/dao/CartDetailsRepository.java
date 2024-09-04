package com.example.bookstore.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.entity.CartDetail;

@Repository
public interface CartDetailsRepository extends JpaRepository<CartDetail, Integer> {
	List<CartDetail> findByCartId(int cartId);
}
package com.example.bookstore.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.dao.BooksRepository;
import com.example.bookstore.dao.CartDetailRepository;
import com.example.bookstore.dao.CartRepository;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.entity.CartDetail;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartDetailRepository cartDetailRepository;
	public List<CartDetail> getCartInfoByUserId(int userId){
		Cart cart=cartRepository.findByUserId(userId);
		if(cart !=null) {
			return cartDetailRepository.findByCartId(cart.getId());
		}
		return Collections.emptyList();
		
	}
}
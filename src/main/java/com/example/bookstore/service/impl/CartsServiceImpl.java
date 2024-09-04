package com.example.bookstore.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.bookstore.dao.BooksRepository;
import com.example.bookstore.dao.UsersRepository;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.User;
import com.example.bookstore.exception.BookNotFoundException;
import com.example.bookstore.exception.UserNotFoundException;
import com.example.bookstore.service.CartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.dao.CartDetailsRepository;
import com.example.bookstore.dao.CartsRepository;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.entity.CartDetail;

@Service
public class CartsServiceImpl implements CartsService {
    @Autowired
    private CartsRepository cartsRepository;

    @Autowired
    private CartDetailsRepository cartDetailsRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private UsersRepository usersRepository;

    public List<CartDetail> getCartInfoByUserId(int userId){
        Cart cart=cartsRepository.findByUserId(userId);
        if(cart !=null) {
            return cartDetailsRepository.findByCartId(cart.getId());
        }
        return Collections.emptyList();
    }

	@Override
	public void updateCartItem(int cartDetailId, int quantity) {
		CartDetail cartDetail = cartDetailsRepository.findById(cartDetailId).orElse(null);
		if (cartDetail != null && quantity > 0) {
			cartDetail.setQuantity(quantity);
			cartDetailsRepository.save(cartDetail);
		}
		
	}

	@Override
	public void removeCartItem(int cartDetailId) {
		cartDetailsRepository.deleteCartDetailById(cartDetailId);
		
	}

	@Override
	public CartDetail findCartDetailById(int cartDetailId) {
		return cartDetailsRepository.findById(cartDetailId).orElse(null);
	}
}
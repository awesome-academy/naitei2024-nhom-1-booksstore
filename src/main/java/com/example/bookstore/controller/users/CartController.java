package com.example.bookstore.controller.users;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.bookstore.entity.CartDetail;
import com.example.bookstore.service.CartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CartController {
	@Autowired
	private CartService cartService; 
	 @GetMapping("/cart")
	public String showCart(@RequestParam("userId") int userId, Model model) {
		List<CartDetail> cartDetails=cartService.getCartDetailsByUserId(userId);
		model.addAttribute("books",cartDetails);
		double subtotal = cartDetails.stream()
		        .mapToDouble(cd -> cd.getBook().getPrice() * cd.getQuantity())
		        .sum();
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
	    String formattedSubtotal = decimalFormat.format(subtotal);
	    model.addAttribute("subtotal", formattedSubtotal);
		return "users/cart";
	}

}

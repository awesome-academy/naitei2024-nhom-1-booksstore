package com.example.bookstore.controller.users;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.bookstore.entity.CartDetail;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.CartService;
import com.example.bookstore.service.UsersService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartsController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private CartService cartService;

	@GetMapping("/cart")
	public String showCart(@RequestParam(value = "userId", required = false) Integer userId, Model model,
			Principal principal) {
		if (userId == null) {
			model.addAttribute("error", "UserId is null");
			return "users/home";
		}
		User user = usersService.findById(userId);
		if (user == null) {
			model.addAttribute("error", "User ID không tồn tại");
			return "users/home";
		}
		if (principal == null) {
			model.addAttribute("error", "Bạn chưa đăng nhập.");
			return "users/home";
		}
		String loggedInUsername = principal.getName();
		User loggedInUser = usersService.findByUsername(loggedInUsername);
		Integer userIdObj = Integer.valueOf(userId);
		if (!userIdObj.equals(loggedInUser.getId())) {
			model.addAttribute("error", "Bạn không có quyền xem giỏ hàng của người khác.");
			return "users/home";
		}
		List<CartDetail> cartDetails = cartService.getCartInfoByUserId(userId);
		model.addAttribute("cartDetails", cartDetails);
		double subtotal = cartDetails.stream().mapToDouble(cd -> cd.getBook().getPrice() * cd.getQuantity()).sum();
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		String formattedSubtotal = decimalFormat.format(subtotal);
		model.addAttribute("subtotal", formattedSubtotal);
		return "users/cart";
	}

}

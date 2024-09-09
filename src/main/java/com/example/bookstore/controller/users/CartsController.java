package com.example.bookstore.controller.users;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.List;

import com.example.bookstore.service.CartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.bookstore.controller.BaseController;
import com.example.bookstore.entity.CartDetail;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.UsersService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartsController extends BaseController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private CartsService cartsService;

	@GetMapping("/cart")
	public String showCart(Model model, Principal principal) {
		User user = getLoggedInUser(principal, model);
		List<CartDetail> cartDetails = cartsService.getCartInfoByUserId(user.getId());
		model.addAttribute("cartDetails", cartDetails);
		double subtotal = cartDetails.stream().mapToDouble(cd -> cd.getBook().getPrice() * cd.getQuantity()).sum();
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		String formattedSubtotal = decimalFormat.format(subtotal);
		model.addAttribute("subtotal", formattedSubtotal);
		return "users/cart";
	}

	@PatchMapping("/carts/{cartDetailId}")
	public String updateCartItem(@PathVariable("cartDetailId") int cartDetailId, @RequestParam("quantity") int quantity,
			Principal principal, Model model) {
		User user = getLoggedInUser(principal, model);
		if (user == null) {
			return "redirect:/sessions/login";
		}
		CartDetail cartDetail = cartsService.findCartDetailById(cartDetailId);
		if (!checkCartOwnership(user, cartDetail, model)) {
			return "redirect:/cart?userId=" + user.getId();
		}
		cartsService.updateCartItem(cartDetailId, quantity);
		return "redirect:/cart?userId=" + user.getId();
	}

	@DeleteMapping("/carts/{cartDetailId}")
	public String removeCartItem(@RequestParam("cartDetailId") int cartDetailId, Principal principal, Model model) {
		User user = getLoggedInUser(principal, model);
		if (user == null) {
			return "redirect:/sessions/login";
		}
		CartDetail cartDetail = cartsService.findCartDetailById(cartDetailId);
		if (!checkCartOwnership(user, cartDetail, model)) {
			return "redirect:/cart?userId=" + user.getId();
		}
		cartsService.removeCartItem(cartDetailId);
		return "redirect:/cart?userId=" + user.getId();
	}

	@PostMapping("/cart/add")
	public String addCartItem(@RequestParam("bookId") int bookId, @RequestParam("quantity") int quantity, Model model,
			Principal principal) {
		User user = getLoggedInUser(principal, model);
		if (user == null) {
			return "redirect:/sessions/login";
		}
		cartsService.addCartItem(user.getId(), bookId, quantity);
		return "redirect:/books/" + bookId;
	}

}

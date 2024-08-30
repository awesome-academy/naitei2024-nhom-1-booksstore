package com.example.bookstore.controller.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.OrdersService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	
	@GetMapping("/orders")
	public String getOrders(HttpSession session, Model model) {
	    User user = (User) session.getAttribute("user");
	    if (user != null) {
	    	Integer userId = user.getId(); 
	        List<Order> orders = ordersService.findOrdersByUserId(userId);
	        model.addAttribute("orders", orders);
	        return "users/orders/index";
	    } else
			return "redirect:/sessions/login";
	}
}

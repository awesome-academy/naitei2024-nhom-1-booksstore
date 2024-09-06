package com.example.bookstore.controller.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.User;
import com.example.bookstore.exception.OrderNotFoundException;
import com.example.bookstore.service.CartsService;
import com.example.bookstore.service.OrdersService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private CartsService cartsService;
	
	@GetMapping("/orders")
	public String index(
            @RequestParam(value = "status", required = false, defaultValue = "ALL") String selectedStatus,
            HttpSession session,
            Model model) {
	    User user = (User) session.getAttribute("user");
	    List<Order> orders;
	    
	    if (user == null) return "redirect:/sessions/login";
	    
	    Integer userId = user.getId(); 

	    if ("ALL".equals(selectedStatus)) {
	           orders = ordersService.findOrdersByUserId(userId);
	       } else {
	        Order.Status status = Order.Status.valueOf(selectedStatus);
	           orders = ordersService.findOrdersByUserIdAndStatus(userId, status);
	       }
	    	
	    if (orders.isEmpty()) {
	    	model.addAttribute("errorMessage", "Chưa có đơn hàng");
	       }
	    	
	       model.addAttribute("orders", orders);
	       model.addAttribute("selectedStatus", selectedStatus);
	        
	    return "users/orders/index";
	}
}

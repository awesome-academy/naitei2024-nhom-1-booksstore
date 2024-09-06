package com.example.bookstore.controller.admin;

import com.example.bookstore.entity.Order;
import com.example.bookstore.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller("ordersAdminController")
@RequestMapping("/admin/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @RequestMapping()
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<Order> orders = ordersService.getAll(pageable);

        model.addAttribute("orders", orders);

        int totalPages = orders.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "admin/orders/index";
    }
}

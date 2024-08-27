package com.example.bookstore.controller.users;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private BooksService booksService;

    @Autowired
    public HomeController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/home")
    public String home(Model model, @RequestParam(value = "search", required = false) String search) {
        List<Book> books;
        if (search != null && !search.isEmpty()) {
            books = booksService.findByTitle(search);
        } else {
            books = booksService.findAll();
        }
        model.addAttribute("books", books);
        model.addAttribute("search", search);
        return "users/home";
    }
}

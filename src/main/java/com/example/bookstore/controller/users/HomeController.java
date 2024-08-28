package com.example.bookstore.controller.users;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Category;
import com.example.bookstore.service.BooksService;
import com.example.bookstore.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private BooksService booksService;

    @Autowired
    private CategoriesService categoryService;

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

    @GetMapping("/categories/{id}")
    public String getBooksByCategory(Model model, @PathVariable("id") Integer categoryId) {
        // kiểm tra nếu category id tồn tại
        Optional<Category> optionalCategory = categoryService.findById(categoryId);
        List<Book> books = booksService.findByCategoryId(categoryId);
        model.addAttribute("books", books);
        return "users/home";
    }
}

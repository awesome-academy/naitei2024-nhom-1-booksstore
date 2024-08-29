package com.example.bookstore.controller.users;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping("/books/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Book book = booksService.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("categories", book.getBookCategories());
        return "users/books/show";
    }

}

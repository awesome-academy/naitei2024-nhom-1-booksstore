package com.example.bookstore.controller.users;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Review;
import com.example.bookstore.service.BooksService;
import com.example.bookstore.service.ReviewsService;
import com.example.bookstore.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @Autowired
    private ReviewsService reviewsService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Book book = booksService.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("categories", book.getBookCategories());

        List<Review> reviews = reviewsService.findReviewsByBookId(id);
        model.addAttribute("reviews", reviews);

        return "users/books/show";
    }
}

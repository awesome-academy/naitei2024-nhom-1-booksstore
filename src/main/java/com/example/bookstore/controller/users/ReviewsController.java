package com.example.bookstore.controller.users;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Review;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.BooksService;
import com.example.bookstore.service.ReviewsService;
import com.example.bookstore.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/books")
public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;

    @Autowired
    private BooksService booksService;

    @Autowired
    private UsersService usersService;

    @PostMapping("/{id}/reviews")
    public String addReview(@PathVariable Integer id,
                            @RequestParam int rating,
                            @RequestParam String content,
                            Principal principal) {
        if (principal == null) {
            return "redirect:/sessions/login";
        }

        Book book = booksService.findById(id);
        User user = usersService.findByUsername(principal.getName());

        Review review = new Review();
        review.setBook(book);
        review.setUser(user);
        review.setRating(rating);
        review.setContent(content);
        reviewsService.save(review);

        return "redirect:/books/" + id;
    }
}

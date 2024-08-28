package com.example.bookstore.advice;

import com.example.bookstore.exception.BookNotFoundException;
import com.example.bookstore.exception.CategoryNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public String handle(BookNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "users/home";
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public String handle(CategoryNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "users/home";
    }
}

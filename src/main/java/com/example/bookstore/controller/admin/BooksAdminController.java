package com.example.bookstore.controller.admin;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.BookCategory;
import com.example.bookstore.entity.Category;
import com.example.bookstore.service.BookCategoriesService;
import com.example.bookstore.service.BooksService;
import com.example.bookstore.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/admin/books")
public class BooksAdminController {
    @Autowired
    private BooksService booksService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private BookCategoriesService bookCategoriesService;

    @RequestMapping()
    public String manageBook(Model model) {
        List<Book> books = booksService.findAll();
        model.addAttribute("books", books);
        return "admin/books/index";
    }

    @RequestMapping("/new")
    public String newBook(Model model) {
        List<Category> categories = categoriesService.getByAll();
        model.addAttribute("categories", categories);
        return "admin/books/form";
    }

    @PostMapping("/")
    public String save(@RequestParam("title") String title,
                       @RequestParam("price") double price,
                       @RequestParam("rating") double rating,
                       @RequestParam("stock_quantity") int stockQuantity,
                       @RequestParam("image") MultipartFile imageFile,
                       @RequestParam("categoryIds") List<Integer> categoryIds,
                       Model model) throws IOException {

        try {
            // Create the new book
            Book book = new Book();
            book.setTitle(title);
            book.setPrice(price);
            book.setRating(rating);
            book.setStockQuantity(stockQuantity);

            // Encode and set the image
            String imageBase64 = encodeFileToBase64(imageFile);
            book.setImage(imageBase64);

            // Save the book first
            booksService.save(book);

            // Find selected categories and create BookCategory entries
            for (Integer categoryId : categoryIds) {
                Category category = categoriesService.getById(categoryId); // Fetch each selected category
                if (category != null) {
                    BookCategory bookCategory = new BookCategory(book, category);
                    bookCategoriesService.save(bookCategory); // Save the association
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while saving the book.");
            return "error-page";
        }

        return "redirect:/admin/books";
    }

    private String encodeFileToBase64(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return Base64.getEncoder().encodeToString(bytes);
    }
}




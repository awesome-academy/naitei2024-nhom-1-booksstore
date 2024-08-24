package com.example.bookstore.controller;

import com.example.bookstore.dto.RegistersUser;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistersController {
    UsersService usersService;

    @Autowired
    public RegistersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/form")
    public String showRegister(Model model) {
        RegistersUser registersUser = new RegistersUser();
        model.addAttribute("registerUser", registersUser);
        return "register/form";
    }

    @PostMapping("/save")
    public String saveRegister(@ModelAttribute("registerUser") RegistersUser registersUser,
                               BindingResult bindingResult,
                               Model model,
                               HttpSession session) {
        String username = registersUser.getUsername();

        if(bindingResult.hasErrors()) {
            return "register/form";
        }

        User checkUser = usersService.findByUsername(username);
        if(checkUser != null) {
            model.addAttribute("registerUser", registersUser);
            model.addAttribute("my_error", "Account already exists");
            return "register/form";
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setUsername(registersUser.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registersUser.getPassword()));
        user.setEmail(registersUser.getEmail());
        user.setPhoneNumber(registersUser.getPhoneNumber());
        user.setAddress(registersUser.getAddress());
        usersService.saveUser(user);

        session.setAttribute("user", user);
        return "register/confirmation";
    }

}

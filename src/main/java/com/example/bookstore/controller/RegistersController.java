package com.example.bookstore.controller;

import com.example.bookstore.dto.RegistersUser;
import com.example.bookstore.entity.Role;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.RolesService;
import com.example.bookstore.service.UsersService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegistersController {
    private UsersService usersService;

    private RolesService rolesService;

    @Autowired
    public RegistersController(UsersService usersService, RolesService rolesService) {
        this.usersService = usersService;
        this.rolesService = rolesService;
    }

    @GetMapping("/form")
    public String showRegister(Model model) {
        RegistersUser registersUser = new RegistersUser();
        model.addAttribute("registerUser", registersUser);
        return "register/form";
    }

    @InitBinder
    public void initBinder(WebDataBinder data) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        data.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/save")
    public String saveRegister(@Valid @ModelAttribute("registerUser") RegistersUser registersUser,
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
            model.addAttribute("my_error", "An account using this username already exists");
            return "register/form";
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setUsername(registersUser.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registersUser.getPassword()));
        user.setEmail(registersUser.getEmail());
        user.setPhoneNumber(registersUser.getPhoneNumber());
        user.setAddress(registersUser.getAddress());
        user.setRole(rolesService.findByName("ROLE_CUSTOMER"));
        usersService.saveUser(user);

        session.setAttribute("user", user);
        return "register/confirmation";
    }

}

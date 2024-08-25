package com.example.bookstore.service;
import com.example.bookstore.dto.RegistersUser;
import com.example.bookstore.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;

public interface UsersService {
    public User findByUsername(String username);
    public User saveUser(User user);
    public User convertRegisterUserToUser(RegistersUser registersUser);
}

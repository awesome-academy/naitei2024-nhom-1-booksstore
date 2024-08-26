package com.example.bookstore.service;
import com.example.bookstore.dto.RegistersUser;
import com.example.bookstore.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;

public interface UsersService extends UserDetailsService {
     User findByUsername(String username);
     User saveUser(User user);
     User convertRegisterUserToUser(RegistersUser registersUser);
}

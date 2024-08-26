package com.example.bookstore.service.serviceimpl;

import com.example.bookstore.dao.UsersRepository;
import com.example.bookstore.dto.RegistersUser;
import com.example.bookstore.service.RolesService;
import com.example.bookstore.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.bookstore.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesService rolesService;

    @Override
    public User findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return usersRepository.save(user);
    }

    @Override
    public User convertRegisterUserToUser(RegistersUser registersUser) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setUsername(registersUser.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registersUser.getPassword()));
        user.setEmail(registersUser.getEmail());
        user.setPhoneNumber(registersUser.getPhoneNumber());
        user.setAddress(registersUser.getAddress());
        user.setRole(rolesService.findByName("ROLE_CUSTOMER"));
        return user;
    }
}

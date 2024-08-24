package com.example.bookstore.service;

import com.example.bookstore.dao.RolesRepository;
import com.example.bookstore.dao.UsersRepository;
import com.example.bookstore.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImp implements RolesService {

    private RolesRepository rolesRepository;

    @Autowired
    public RolesServiceImp(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public Role findByName(String roleName) {
        return rolesRepository.findByName(roleName);
    }
}

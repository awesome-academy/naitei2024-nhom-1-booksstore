package com.example.bookstore.service;

import com.example.bookstore.entity.Role;

public interface RolesService {
    public Role findByName(String roleName);
}

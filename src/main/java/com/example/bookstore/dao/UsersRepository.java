package com.example.bookstore.dao;

import com.example.bookstore.entity.Role;
import com.example.bookstore.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;


@Repository
@EnableTransactionManagement
@Transactional
public interface UsersRepository extends JpaRepository<User, Integer> {
     User findById(int id);
     User findByUsername(String username);
     List<User> findAll();
     @Modifying(flushAutomatically = true, clearAutomatically = true)
     @Query("update User u set u.role = :role where u.id = :id")
     public void updateUser(@Param("id") int id, @Param("role") Role role);
}

package com.example.bookstore.dao;

import java.util.List;
import java.util.Optional;

import com.example.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.Order.Status;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {

	@Query("SELECT o FROM Order o JOIN FETCH o.orderDetails od JOIN FETCH od.book WHERE o.user.id = :userId")
	List<Order> findOrdersByUserId(Integer userId);

	@Query("SELECT o FROM Order o JOIN FETCH o.orderDetails od JOIN FETCH od.book WHERE o.user.id = :userId AND o.status = :status")
	List<Order> findOrdersByUserIdAndStatus(Integer userId, Status status);

	@Query("SELECT o FROM Order o JOIN FETCH o.orderDetails od JOIN FETCH od.book WHERE o.id = :orderId")
	Optional<Order> findOrderByIdWithDetails(Integer orderId);

    @Modifying()
    @Query("UPDATE Order o set o.status= 'COMPLETED' WHERE o.id = :orderId")
    void changeStatusToCompleted(@Param("orderId") Integer orderId);

    @Modifying()
    @Query("UPDATE Order o set o.status= 'REJECTED' WHERE o.id = :orderId")
    void changeStatusToCancelled(@Param("orderId") Integer orderId);

    @Query("SELECT o.user FROM Order o WHERE o.id = :orderId")
    User findUserByOrderId(@Param("orderId") int orderId);
}

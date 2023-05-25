package com.example.ecommerce.repository;

import com.example.ecommerce.models.Order;
import com.example.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

//    Order findByIdAndUser(Long id, User user);

    Order findByUserAndId(User user, Long id);
}

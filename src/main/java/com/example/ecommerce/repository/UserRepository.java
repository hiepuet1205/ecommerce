package com.example.ecommerce.repository;

import com.example.ecommerce.models.User;
import com.example.ecommerce.models.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findByRole(ERole role);

    User findByName(String name);
}

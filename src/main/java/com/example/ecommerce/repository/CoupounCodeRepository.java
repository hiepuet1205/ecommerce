package com.example.ecommerce.repository;

import com.example.ecommerce.models.CoupounCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoupounCodeRepository extends JpaRepository<CoupounCode, Long> {
    CoupounCode findByName(String name);
}

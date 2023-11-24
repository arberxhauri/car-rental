package com.example.carrental.repository;


import com.example.carrental.entity.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CostumerRepository extends JpaRepository<Costumer, Long> {

    Optional<Costumer> findByUsernameOrEmail(String username, String email);
}
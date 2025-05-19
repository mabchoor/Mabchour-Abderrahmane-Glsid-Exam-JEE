package com.example.demo.repository;

import com.example.demo.entities.CreditImmobilier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditImmobilierRepository extends JpaRepository<CreditImmobilier, Long> {
} 
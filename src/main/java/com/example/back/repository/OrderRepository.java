package com.example.back.repository;

import com.example.back.model.Orderr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orderr, Integer> {
    Page<Orderr> findAll(Pageable pageable);
}
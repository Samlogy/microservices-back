package com.example.back.repository;

import com.example.back.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p " +
            "WHERE (:name IS NULL OR p.name LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:priceMin IS NULL OR p.price >= :priceMin) " +
            "AND (:priceMax IS NULL OR p.price <= :priceMax) " +
            "AND (:category IS NULL OR p.category = :category)"
    )
    Page<Product> filterProducts(
            @Param("name") String name,
            @Param("category") String category,
            @Param("priceMin") float priceMin,
            @Param("priceMax") float priceMax,
            Pageable pageable
    );
}
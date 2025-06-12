package com.example.warehouse.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.warehouse.entity.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(String category);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByPriceBetween(BigDecimal from, BigDecimal to);
}

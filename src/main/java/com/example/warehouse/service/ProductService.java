package com.example.warehouse.service;

import com.example.warehouse.entity.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();
    Optional<Product> getProductById(Integer id);
    Product createProduct(Product product);
    Product updateProduct(Integer id, Product product);
    void deleteProduct(Integer id);

    List<Product> findProductByCategory(String category);
    List<Product> findProductByPriceBetween(BigDecimal from, BigDecimal to);
    List<Product> findProductByNameContainingIgnoreCase(String name);

}

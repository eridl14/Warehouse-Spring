package com.example.warehouse.service;
import java.math.BigDecimal;

import java.util.List;
import java.util.Optional;

import com.example.warehouse.entity.Product;
import com.example.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Integer id) {

        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Integer id, Product product) {
        return productRepository.findById(id)
                .map( existing -> {
                    existing.setName(product.getName());
                    existing.setPrice(product.getPrice());
                    existing.setDescription(product.getDescription());
                    existing.setCategory(product.getCategory());
                    existing.setWeight(product.getWeight());
                    return productRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Warehouse not found: " + id));
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);

    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> findProductByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepository.findByPriceBetween(from, to);
    }

    @Override
    public List<Product> findProductByNameContainingIgnoreCase(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}

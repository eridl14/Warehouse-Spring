package com.example.warehouse.controller;
import com.example.warehouse.entity.Product;

import com.example.warehouse.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*")

public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product create = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id , @RequestBody Product product) {
        try {
            Product update = productService.updateProduct(id, product);
            return ResponseEntity.ok(update);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByCategory")
    public ResponseEntity<List<Product>> searchByCategory(@RequestParam String category) {
        List<Product> products = productService.findProductByCategory(category);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/searchByPrice")
    public ResponseEntity<List<Product>> searchByPrice(@RequestParam BigDecimal from, @RequestParam BigDecimal to) {
        List<Product> products = productService.findProductByPriceBetween(from, to );
        return ResponseEntity.ok(products);
    }
    @GetMapping("/searchByName")
    public ResponseEntity<List<Product>> searchByName(@RequestParam String name) {
        List<Product> products = productService.findProductByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(products);
    }
}

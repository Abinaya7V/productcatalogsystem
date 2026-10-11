package com.wipro.catalog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.catalog.model.Product;
import com.wipro.catalog.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // Constructor injection of ProductService interface
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Option 1: Add a product -> POST /api/products
    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        Product createdProduct = productService.addProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // Option 2: Display all products -> GET /api/products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Option 3: Search by category -> GET /api/products/category/{category}
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> searchByCategory(@PathVariable String category) {
        List<Product> products = productService.searchByCategory(category);
        return ResponseEntity.ok(products);
    }

    // Option 4: Search by price range -> GET /api/products/price?min=40&max=1000
    @GetMapping("/price")
    public ResponseEntity<List<Product>> searchByPrice(@RequestParam double min, @RequestParam double max) {
        List<Product> products = productService.searchByPrice(min, max);
        return ResponseEntity.ok(products);
    }

    // Option 5: Sort by name -> GET /api/products/sort/name
    @GetMapping("/sort/name")
    public ResponseEntity<List<Product>> sortByName() {
        List<Product> products = productService.sortByName();
        return ResponseEntity.ok(products);
    }

    // Option 6: Sort by price -> GET /api/products/sort/price
    @GetMapping("/sort/price")
    public ResponseEntity<List<Product>> sortByPrice() {
        List<Product> products = productService.sortByPrice();
        return ResponseEntity.ok(products);
    }
}

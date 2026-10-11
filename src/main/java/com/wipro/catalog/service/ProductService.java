package com.wipro.catalog.service;

import java.util.List;
import com.wipro.catalog.model.Product;

/**
 * ProductService interface defines the contract for product operations.
 * Designed for REST architecture where methods return domain data
 * instead of printing directly to console.
 */
public interface ProductService {

    // Add a new product to the catalog (rejects duplicate SKU)
    Product addProduct(Product product);

    // Retrieve all products currently in the catalog
    List<Product> getAllProducts();

    // Search products belonging to a given category
    List<Product> searchByCategory(String category);

    // Search products within a given price range
    List<Product> searchByPrice(double minPrice, double maxPrice);

    // Sort products by price (ascending) on a copy of the list
    List<Product> sortByPrice();

    // Sort products by name (alphabetical order) on a copy of the list
    List<Product> sortByName();
}

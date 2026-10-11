package com.wipro.catalog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.catalog.exception.DuplicateProductException;
import com.wipro.catalog.model.Product;

/**
 * ProductServiceImpl provides the concrete implementation of ProductService.
 * Annotated with @Service for Spring dependency injection.
 * Retains custom algorithms (Bubble Sort, Selection Sort) operating on copies.
 */
@Service
public class ProductServiceImpl implements ProductService {

    // In-memory product store
    private final List<Product> productList;

    public ProductServiceImpl() {
        this.productList = new ArrayList<>();
    }

    // ---------- 1. Add Product ----------
    @Override
    public Product addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        // Reject duplicate SKU
        for (int i = 0; i < productList.size(); i++) {
            Product existing = productList.get(i);
            if (existing.getSku() != null && existing.getSku().equalsIgnoreCase(product.getSku())) {
                throw new DuplicateProductException("Product with SKU '" + product.getSku() + "' already exists");
            }
        }

        productList.add(product);
        return product;
    }

    // ---------- 2. Get All Products ----------
    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(productList);
    }

    // ---------- 3. Search by Category ----------
    @Override
    public List<Product> searchByCategory(String category) {
        List<Product> results = new ArrayList<>();
        if (category == null) {
            return results;
        }

        for (int i = 0; i < productList.size(); i++) {
            Product p = productList.get(i);
            if (p.getCategory() != null && p.getCategory().equalsIgnoreCase(category)) {
                results.add(p);
            }
        }
        return results;
    }

    // ---------- 4. Search by Price Range ----------
    @Override
    public List<Product> searchByPrice(double minPrice, double maxPrice) {
        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("Minimum price cannot be greater than maximum price");
        }

        List<Product> results = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            Product p = productList.get(i);
            if (p.getPrice() >= minPrice && p.getPrice() <= maxPrice) {
                results.add(p);
            }
        }
        return results;
    }

    // ---------- 5. Sort by Name (Selection Sort on a Copy) ----------
    @Override
    public List<Product> sortByName() {
        List<Product> sortedList = new ArrayList<>(productList);
        int n = sortedList.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (sortedList.get(j).getName() != null && sortedList.get(minIndex).getName() != null) {
                    if (sortedList.get(j).getName().compareToIgnoreCase(sortedList.get(minIndex).getName()) < 0) {
                        minIndex = j;
                    }
                }
            }

            if (minIndex != i) {
                Product temp = sortedList.get(i);
                sortedList.set(i, sortedList.get(minIndex));
                sortedList.set(minIndex, temp);
            }
        }
        return sortedList;
    }

    // ---------- 6. Sort by Price (Bubble Sort on a Copy) ----------
    @Override
    public List<Product> sortByPrice() {
        List<Product> sortedList = new ArrayList<>(productList);
        int n = sortedList.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (sortedList.get(j).getPrice() > sortedList.get(j + 1).getPrice()) {
                    Product temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                }
            }
        }
        return sortedList;
    }
}

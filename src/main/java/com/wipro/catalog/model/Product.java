package com.wipro.catalog.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Product class represents a single product in the catalog.
 * It follows basic OOP principles - encapsulation using private
 * fields with public getters and setters.
 */
public class Product {

    // ---------- Private fields (Encapsulation) ----------
    @NotBlank(message = "SKU is required and cannot be blank")
    private String sku;

    @NotBlank(message = "Name is required and cannot be blank")
    private String name;

    @NotBlank(message = "Category is required and cannot be blank")
    private String category;

    @PositiveOrZero(message = "Price must be greater than or equal to 0")
    private double price;

    // ---------- Default / No-Arg Constructor ----------
    // Required by JSON parsers (e.g., Jackson) to deserialize incoming request bodies.
    public Product() {
    }

    // ---------- Parameterized Constructor ----------
    public Product(String sku, String name, String category, double price) {
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // ---------- Getters and Setters ----------

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // ---------- toString() ----------
    @Override
    public String toString() {
        return "SKU: " + sku +
               " | Name: " + name +
               " | Category: " + category +
               " | Price: " + price;
    }
}

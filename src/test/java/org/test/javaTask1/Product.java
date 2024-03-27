package org.test.javaTask1;

import org.springframework.core.annotation.Order;

import java.util.HashSet;
import java.util.Set;

public class Product {
    private Long id;
    private String name;
    private String category;
    private Double price;
    private Set<Order> orders;

    public Product(Long id, String name, String category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + ", category='" + category + ", price=" + price + '}';
    }

    static Set<Product> getProducts() {
        Set<Product> products = new HashSet<>();
        products.add(new Product(1L, "Philosopher's Stone", "Books", 130.0));
        products.add(new Product(2L, "Chamber of Secrets", "Books", 150.0));
        products.add(new Product(3L, "Table", "Furniture", 450.0));
        products.add(new Product(4L, "Deathly Hallows", "Books", 99.0));
        return products;
    }
}

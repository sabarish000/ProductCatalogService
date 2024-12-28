package com.example.product.catalog.repos;

import com.example.product.catalog.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}

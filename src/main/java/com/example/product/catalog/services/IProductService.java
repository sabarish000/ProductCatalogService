package com.example.product.catalog.services;

import com.example.product.catalog.models.Product;

import java.util.List;

public interface IProductService {
    Product create(Product product);

    Product getProductById(Long id);

    List<Product> getProducts();

    Product replace(Long id, Product product);

    Product update(Long id, Product product);

    void delete(Long id);
}

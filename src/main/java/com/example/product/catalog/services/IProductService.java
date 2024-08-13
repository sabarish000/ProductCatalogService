package com.example.product.catalog.services;

import com.example.product.catalog.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);

    List<Product> getProducts();
}

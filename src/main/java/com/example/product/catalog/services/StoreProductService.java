package com.example.product.catalog.services;

import com.example.product.catalog.exceptions.ResourceNotFoundException;
import com.example.product.catalog.models.Product;
import com.example.product.catalog.models.enums.Status;
import com.example.product.catalog.repos.ProductRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StoreProductService implements IProductService{
    private ProductRepo productRepo;
    @Override
    public Product create(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        Product productById = productRepo.getProductById(id);
        if(isProductExists(productById)){
            throw new ResourceNotFoundException("Product not found for the id: "+id);
        }
        return productById;
    }

    @Override
    public List<Product> getProducts() {
        return productRepo.findAll().stream()
                .filter(p -> p.getStatus().equals(Status.ACTIVE))
                .toList();
    }

    @Override
    public Product replace(Long id, Product product) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isEmpty() || isProductExists(productOptional.get())){
            throw new ResourceNotFoundException("Product not found for the id: "+id);
        }
        return productRepo.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isEmpty() || isProductExists(productOptional.get())){
            throw new ResourceNotFoundException("Product not found for the id: "+id);
        }
        Product productFromDB = productOptional.get();
        if(product.getCategory() != null) {
            productFromDB.setCategory(product.getCategory());
            productFromDB.setLastUpdatedAt(LocalDateTime.now());
        }
        if(product.getName() != null) {
            productFromDB.setName(product.getName());
            productFromDB.setLastUpdatedAt(LocalDateTime.now());
        }
        if(product.getDescription() != null) {
            productFromDB.setDescription(product.getDescription());
            productFromDB.setLastUpdatedAt(LocalDateTime.now());
        }
        if(product.getPrice() != null) {
            productFromDB.setPrice(product.getPrice());
            productFromDB.setLastUpdatedAt(LocalDateTime.now());
        }
        if(product.getImageUrl() != null) {
            productFromDB.setImageUrl(product.getImageUrl());
            productFromDB.setLastUpdatedAt(LocalDateTime.now());
        }

        return productRepo.save(productFromDB);
    }

    @Override
    public void delete(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isEmpty() || isProductExists(productOptional.get())){
            throw new ResourceNotFoundException("Product not found for the id: "+id);
        }
        Product productFromDB = productOptional.get();
        productFromDB.setStatus(Status.INACTIVE);
        productFromDB.setLastUpdatedAt(LocalDateTime.now());
        productRepo.save(productFromDB);
    }

    private boolean isProductExists(Product product) {
        return product == null || !product.getStatus().equals(Status.ACTIVE);
    }
}

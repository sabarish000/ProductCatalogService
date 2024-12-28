package com.example.product.catalog.services;

import com.example.product.catalog.exceptions.ResourceNotFoundException;
import com.example.product.catalog.models.Product;
import com.example.product.catalog.models.enums.Status;
import com.example.product.catalog.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

@Service
public class StoreProductService implements IProductService{
    private static final String PRODUCT_NOT_FOUND = "Product not found for the id: %d";

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product create(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        Product productById = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(PRODUCT_NOT_FOUND, id)));
        if(isProductInactive(productById)){
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
        Product productFromDB = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(PRODUCT_NOT_FOUND, id)));
        if(isProductInactive(productFromDB)){
            throw new ResourceNotFoundException("Product not found for the id: "+id);
        }
        return productRepo.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Product productFromDB = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(PRODUCT_NOT_FOUND, id)));
        if(isProductInactive(productFromDB)){
            throw new ResourceNotFoundException("Product not found for the id: "+id);
        }
        updateIfPresent(product.getCategory(), productFromDB::setCategory);
        updateIfPresent(product.getName(), productFromDB::setName);
        updateIfPresent(product.getDescription(), productFromDB::setDescription);
        updateIfPresent(product.getPrice(), productFromDB::setPrice);
        updateIfPresent(product.getImageUrl(), productFromDB::setImageUrl);
        productFromDB.setLastUpdatedAt(LocalDateTime.now());
        return productRepo.save(productFromDB);
    }

    @Override
    public void delete(Long id) {
        Product productFromDB = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(PRODUCT_NOT_FOUND, id)));
        if(isProductInactive(productFromDB)){
            throw new ResourceNotFoundException("Product not found for the id: "+id);
        }
        productFromDB.setStatus(Status.INACTIVE);
        productFromDB.setLastUpdatedAt(LocalDateTime.now());
        productRepo.save(productFromDB);
    }

    private boolean isProductInactive(Product product) {
        return product.getStatus().equals(Status.INACTIVE);
    }

    private <T> void updateIfPresent(T newValue, Consumer<T> updater) {
        if (newValue != null) {
            updater.accept(newValue);
        }
    }
}

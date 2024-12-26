package com.example.product.catalog.services;

import com.example.product.catalog.clients.FakeResponseApiClient;
import com.example.product.catalog.dtos.FakeStoreProductDTO;
import com.example.product.catalog.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class FakeStoreProductService implements IProductService {

    @Autowired
    private FakeResponseApiClient client;

    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public Product getProductById(Long productId) {
        FakeStoreProductDTO fakeStoreProductDTO = client.getProductById(productId);
        return fakeStoreProductDTO == null ? null : fakeStoreProductDTO.toProduct();
    }

    @Override
    public List<Product> getProducts() {
        return client.getProducts().stream()
                .map(FakeStoreProductDTO::toProduct)
                .toList();
    }

    @Override
    public Product replace(Long id, Product product) {
        return client.replaceProduct(id, new FakeStoreProductDTO(product))
                .toProduct();
    }

    @Override
    public Product update(Long id, Product product) {
        return client.updateProduct(id, new FakeStoreProductDTO(product))
                .toProduct();
    }


    @Override
    public void delete(Long id) {
        client.deleteProduct(id);
    }
}

package com.example.product.catalog.services;

import com.example.product.catalog.dtos.FakeStoreProductDTO;
import com.example.product.catalog.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForEntity("http://fakestoreapi.com/products/"+id,
                FakeStoreProductDTO.class).getBody();
        return fakeStoreProductDTO == null ? new Product() : fakeStoreProductDTO.toProduct();
    }

    @Override
    public List<Product> getProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDTO[] fakeStoreProductDTOS = restTemplate.getForEntity("http://fakestoreapi.com/products/",
                FakeStoreProductDTO[].class).getBody();
        if(fakeStoreProductDTOS == null) {
          return Collections.emptyList();
        }
        List<Product> productList = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductDTOS) {
            productList.add(fakeStoreProductDTO.toProduct());
        }
        return productList;
    }
}

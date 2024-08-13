package com.example.product.catalog.controllers;

import com.example.product.catalog.models.Product;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class ProductController {
    @GetMapping("/")
    public String getMessage() {
        return "Welcome to Project Module";
    }

    @GetMapping("/products")
    public List<Product> getProducts(){
        return Collections.emptyList();
    }

    // @PathVariable("id") maps it with the @GetMapping id in the path.
    // @PathVariable Long id name should match variable in the path("products/{id}").
    // otherwise throws MissingPathVariableException (Required path variable 'productId' is not present.)
    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable("id") Long productId){
        Product product = new Product();
        product.setId(productId);
        return product;
    }

    // @RequestBody binds the request body to product variable
    @PostMapping("/products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return product;
    }
}

package com.example.product.catalog.controllers;

import com.example.product.catalog.dtos.ProductDTO;
import com.example.product.catalog.models.Product;
import com.example.product.catalog.services.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping("/")
    public String getMessage() {
        return "Welcome to Project Module";
    }

    @GetMapping("/products")
    public List<ProductDTO> getProducts(){
        List<Product> products = this.productService.getProducts();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product: products) {
            productDTOS.add(new ProductDTO(product));
        }
        return productDTOS;
    }

    // @PathVariable("id") maps it with the @GetMapping id in the path.
    // @PathVariable Long id name should match variable in the path("products/{id}").
    // otherwise throws MissingPathVariableException (Required path variable 'productId' is not present.)
    @GetMapping("products/{id}")
    public ProductDTO getProduct(@PathVariable("id") Long productId){
        return new ProductDTO(this.productService.getProductById(productId));
    }

    // @RequestBody binds the request body to product variable
    @PostMapping("/products")
    public Product createProduct(@Valid @RequestBody Product product) {
        return product;
    }
}

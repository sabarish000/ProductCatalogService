package com.example.product.catalog.controllers;

import com.example.product.catalog.dtos.ProductDTO;
import com.example.product.catalog.exceptions.ResourceNotFoundException;
import com.example.product.catalog.models.Product;
import com.example.product.catalog.services.IProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    @Qualifier("fakeStoreProductService")
    IProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> create(@Valid @NotNull @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<ProductDTO>(new ProductDTO(productService.create(productDTO.toProduct())),
                HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        List<Product> products = this.productService.getProducts();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(Product product: products) {
            productDTOS.add(new ProductDTO(product));
        }
        return new ResponseEntity<>(productDTOS,HttpStatus.OK);
    }

    // @PathVariable("id") maps it with the @GetMapping id in the path.
    // @PathVariable Long id name should match variable in the path("products/{id}").
    // otherwise throws MissingPathVariableException (Required path variable 'productId' is not present.)
    @GetMapping("products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long productId){
        if(productId <= 0) {
             throw new IllegalArgumentException("Product id invalid");
        }
        Product product = this.productService.getProductById(productId);
        if(product == null) {

            throw new ResourceNotFoundException("Product not found with id: "+productId);
        }
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Test key", "Test value");
        return new ResponseEntity<>(new ProductDTO(product), headers, HttpStatus.OK);
    }

    // @RequestBody binds the request body to product variable
    @PatchMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@NotNull @PathVariable Long id,@NotNull @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<ProductDTO>(new ProductDTO(productService.update(id, productDTO.toProduct())),
                HttpStatus.OK);
    }

    // @RequestBody binds the request body to product variable
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> replaceProduct(@NotNull @PathVariable Long id, @NotNull @Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<ProductDTO>(new ProductDTO(productService.replace(id, productDTO.toProduct())),
                HttpStatus.OK);
    }

    // @RequestBody binds the request body to product variable
    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@NotBlank @PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

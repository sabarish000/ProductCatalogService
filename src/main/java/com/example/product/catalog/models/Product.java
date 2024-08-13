package com.example.product.catalog.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
public class Product extends BaseModel{
    @Size(min = 3, max = 20, message = "name should be a length of 3 to 20 characters")
    private String name;

    @Size(min = 3, max = 50, message = "description should be a length of 3 to 50 characters")
    private String description;

    @NotNull(message = "price should not be null")
    private Double price;

    private String imageUrl;

    private Category category;
}

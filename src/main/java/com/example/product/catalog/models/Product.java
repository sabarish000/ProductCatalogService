package com.example.product.catalog.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Product extends BaseModel{
    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private Category category;
}

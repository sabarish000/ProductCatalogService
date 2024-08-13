package com.example.product.catalog.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Category extends BaseModel{
    private String name;

    private String description;

    private List<Product> products;

}

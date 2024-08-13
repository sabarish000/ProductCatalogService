package com.example.product.catalog.dtos;

import com.example.product.catalog.models.Category;
import com.example.product.catalog.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;

    private String title;

    private Double price;

    private String description;

    private String category;

    private String image;

    public Product toProduct() {
        Category category = Category.builder().name(this.category).build();
        return Product.builder()
                .id(id)
                .name(title)
                .price(price)
                .description(description)
                .imageUrl(image)
                .category(category)
                .build();
    }
}

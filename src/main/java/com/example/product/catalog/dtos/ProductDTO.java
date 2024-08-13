package com.example.product.catalog.dtos;

import com.example.product.catalog.models.Category;
import com.example.product.catalog.models.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private Long id;

    @Size(min = 3, max = 20, message = "name should be a length of 3 to 20 characters")
    private String name;

    @Size(min = 3, max = 50, message = "description should be a length of 3 to 50 characters")
    private String description;

    @NotNull(message = "price should not be null")
    private Double price;

    private String imageUrl;

    private CategoryDTO category;

    public Product toProduct() {
        Category category = this.category == null ? null :
                Category.builder()
                        .name(this.category.getName())
                        .id(this.category.getId())
                        .build();
        return Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .description(description)
                .imageUrl(imageUrl)
                .category(category)
                .build();
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
        this.category = new CategoryDTO(product.getCategory());
    }
}

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
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(imageUrl);
        if(category != null){
            Category categoryObj = new Category();
            categoryObj.setName(category.getName());
            product.setCategory(categoryObj);
        }
        return product;
    }

    public ProductDTO(@NotNull Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
        if(product.getCategory() != null) {
            this.category = new CategoryDTO(product.getCategory());
        }
    }
}

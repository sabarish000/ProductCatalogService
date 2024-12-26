package com.example.product.catalog.dtos;

import com.example.product.catalog.models.Category;
import com.example.product.catalog.models.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;

    public Product toProduct() {
        Category category = this.category == null ? null :
                Category.builder()
                        .name(this.category)
                        .build();
        return Product.builder()
                .id(id)
                .name(title)
                .price(price)
                .description(description)
                .imageUrl(image)
                .category(category)
                .build();
    }

    public FakeStoreProductDTO(@NotNull Product product) {
        this.id = product.getId();
        this.title = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.image = product.getImageUrl();
        if(product.getCategory() != null){
            this.category =  product.getCategory().getName();
        }
    }
}

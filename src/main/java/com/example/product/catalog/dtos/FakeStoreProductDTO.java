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
        Product product = new Product();
        product.setId(id);
        product.setName(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(image);
        if(category != null){
            Category categoryObj = new Category();
            categoryObj.setName(category);
                product.setCategory(categoryObj);
        }
        return product;
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

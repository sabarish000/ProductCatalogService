package com.example.product.catalog.dtos;

import com.example.product.catalog.models.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Long id;

    private String name;

    private String description;

    public CategoryDTO(@NotNull Category category) {
        if(category!=null) {
            this.id = category.getId();
            this.name = category.getName();
            this.description = category.getDescription();
        }
    }
}

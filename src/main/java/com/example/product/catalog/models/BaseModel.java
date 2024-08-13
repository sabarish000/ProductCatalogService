package com.example.product.catalog.models;

import com.example.product.catalog.models.enums.Status;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseModel {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;

    private Status status;

}

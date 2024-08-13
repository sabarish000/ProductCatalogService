package com.example.product.catalog.models;

import com.example.product.catalog.models.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class BaseModel {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;

    private Status status;

}

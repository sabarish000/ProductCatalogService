package com.example.product.catalog.models;

import com.example.product.catalog.models.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
// MappedSuperclass -> Does not create table in DB for BaseModel class
// but creates columns in tables of subclasses for all the attributes
public abstract class BaseModel {
    @Id
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    // Soft delete field
    @Enumerated(EnumType.ORDINAL)
    private Status status;
}

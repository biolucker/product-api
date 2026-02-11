package com.example.productapi.dto;

import java.time.LocalDateTime;

public class ProductResponseDTO {

    private Long id;
    private String name;
    private int price;
    private LocalDateTime createdAt;

    // Constructor for mapping from entity
    public ProductResponseDTO(Long id, String name, int price, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
    }

    // Getters (no setters — immutable for response)
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
package com.example.productapi.dto;

import java.time.LocalDateTime;

public class ProductResponseDTO {

    private String id;
    private String name;
    private Integer price;
    private Integer costPrice;
    private String imageUrl;
    private String barcode;
    private String category;
    private String supplyOrderId;
    private LocalDateTime createdAt;

    public ProductResponseDTO(String id, String name, Integer price, Integer costPrice,
                              String imageUrl, String barcode, String category,
                              String supplyOrderId, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.costPrice = costPrice;
        this.imageUrl = imageUrl;
        this.barcode = barcode;
        this.category = category;
        this.supplyOrderId = supplyOrderId;
        this.createdAt = createdAt;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public Integer getPrice() { return price; }
    public Integer getCostPrice() { return costPrice; }
    public String getImageUrl() { return imageUrl; }
    public String getBarcode() { return barcode; }
    public String getCategory() { return category; }
    public String getSupplyOrderId() { return supplyOrderId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
package com.example.productapi.dto;

public class ProductCreateDTO {

    private String name;
    private Integer price;
    private Integer costPrice;
    private String imageUrl;
    private String barcode;
    private String category;
    private String supplyOrderId;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    public Integer getCostPrice() { return costPrice; }
    public void setCostPrice(Integer costPrice) { this.costPrice = costPrice; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getSupplyOrderId() { return supplyOrderId; }
    public void setSupplyOrderId(String supplyOrderId) { this.supplyOrderId = supplyOrderId; }
}
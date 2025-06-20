package com.dangthibichvang.dangthibichvang_2122110510;

public class Product {
    private int imageResId;
    private String name;
    private String description;
    private String price;

    // Constructor
    public Product(int imageResId, String name, String description, String price) {
        this.imageResId = imageResId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // ✅ Getter methods
    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }
}

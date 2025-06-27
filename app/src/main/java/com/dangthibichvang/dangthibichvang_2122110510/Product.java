package com.dangthibichvang.dangthibichvang_2122110510;

public class Product {
    private int imageResId;
    private String name;
    private String description;
    private String price;
    private int quantity;
    private String category;


    public Product(int imageResId, String name, String description, String price, String category) {
        this.imageResId = imageResId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantity = 1;
    }

    // Getter & setter
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }
}

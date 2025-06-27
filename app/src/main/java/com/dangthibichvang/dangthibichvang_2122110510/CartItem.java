package com.dangthibichvang.dangthibichvang_2122110510;

public class CartItem {
    public String name, desc, price;
    public int image, quantity;
    public boolean isSelected;

    public CartItem(String name, String desc, String price, int image, int quantity) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.isSelected = false;
    }
}

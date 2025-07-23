package com.dangthibichvang.dangthibichvang_2122110510;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CartManager {
    private static CartManager instance;

    private CartManager() {}

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public int getItemCount(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("CART", Context.MODE_PRIVATE);
        String cartData = prefs.getString("cartItems", "[]");
        int count = 0;
        try {
            JSONArray arr = new JSONArray(cartData);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                count += obj.getInt("quantity");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void addToCart(Product product, Context context) {
        SharedPreferences prefs = context.getSharedPreferences("CART", Context.MODE_PRIVATE);
        String cartData = prefs.getString("cartItems", "[]");
        SharedPreferences.Editor editor = prefs.edit();

        try {
            JSONArray arr = new JSONArray(cartData);
            boolean found = false;

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                if (obj.getInt("image") == product.getImageResId()) {
                    int qty = obj.getInt("quantity");
                    obj.put("quantity", qty + product.getQuantity());
                    found = true;
                    break;
                }
            }

            if (!found) {
                JSONObject newItem = new JSONObject();
                newItem.put("name", product.getName());
                newItem.put("desc", product.getDescription());
                newItem.put("price", product.getPrice());
                newItem.put("image", product.getImageResId());
                newItem.put("quantity", product.getQuantity());
                newItem.put("selected", false);
                arr.put(newItem);
            }

            editor.putString("cartItems", arr.toString());
            editor.apply();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void clearCart(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("CART", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("cartItems", "[]");
        editor.apply();
    }
}

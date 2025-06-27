package com.dangthibichvang.dangthibichvang_2122110510;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import org.json.*;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import com.google.gson.Gson;

public class CartActivity extends AppCompatActivity {

    ListView lvCart;
    TextView tvTotal;
    Button btnCheckout, btnClearCart;
    CartAdapter adapter;
    List<CartItem> cartItems = new ArrayList<>();
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        lvCart = findViewById(R.id.lvCart);
        tvTotal = findViewById(R.id.tvTotal);
        btnCheckout = findViewById(R.id.btnCheckout);
        btnClearCart = findViewById(R.id.btnClearCart);
        prefs = getSharedPreferences("CART", MODE_PRIVATE);

        loadCart();

        adapter = new CartAdapter(this, cartItems, this::updateTotalPrice);
        lvCart.setAdapter(adapter);

        updateTotalPrice();

        btnCheckout.setOnClickListener(v -> {
            List<CartItem> selected = new ArrayList<>();
            for (CartItem item : cartItems) {
                if (item.isSelected) {
                    selected.add(item);
                }
            }

            if (selected.isEmpty()) {
                Toast.makeText(this, "Vui lòng chọn ít nhất 1 sản phẩm!", Toast.LENGTH_SHORT).show();
                return;
            }

            String json = new Gson().toJson(selected);

            Intent i = new Intent(CartActivity.this, CheckoutActivity.class);
            i.putExtra("selectedItems", json);
            startActivity(i);
        });

        btnClearCart.setOnClickListener(v -> {
            prefs.edit().putString("cartItems", "[]").apply();
            cartItems.clear();
            adapter.notifyDataSetChanged();
            updateTotalPrice();
            Toast.makeText(CartActivity.this, "Đã xóa toàn bộ giỏ hàng!", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadCart() {
        String json = prefs.getString("cartItems", "[]");
        try {
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                int quantity = obj.has("quantity") ? obj.getInt("quantity") : 1;
                boolean selected = obj.has("selected") && obj.getBoolean("selected");

                CartItem item = new CartItem(
                        obj.getString("name"),
                        obj.getString("desc"),
                        obj.getString("price"),
                        obj.getInt("image"),
                        quantity
                );
                item.isSelected = selected;
                cartItems.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void updateTotalPrice() {
        int total = 0;
        for (CartItem item : cartItems) {
            if (item.isSelected) {
                int price = Integer.parseInt(item.price.replace(".", "").replace("đ", ""));
                total += price * item.quantity;
            }
        }
        tvTotal.setText("Tổng: " + total + "đ");

        try {
            JSONArray newArray = new JSONArray();
            for (CartItem item : cartItems) {
                JSONObject o = new JSONObject();
                o.put("name", item.name);
                o.put("desc", item.desc);
                o.put("price", item.price);
                o.put("image", item.image);
                o.put("quantity", item.quantity);
                o.put("selected", item.isSelected);
                newArray.put(o);
            }
            prefs.edit().putString("cartItems", newArray.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

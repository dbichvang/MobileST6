package com.dangthibichvang.dangthibichvang_2122110510;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView imgProduct;
    TextView tvName, tvDescription, tvPrice, tvQuantity;
    Button btnMinus, btnPlus, btnAddToCart;

    int quantity = 1;
    String name, description, price;
    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Ánh xạ View
        imgProduct = findViewById(R.id.imgProduct);
        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        tvPrice = findViewById(R.id.tvPrice);
        tvQuantity = findViewById(R.id.tvQuantity);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        // Nhận dữ liệu sản phẩm từ intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            image = bundle.getInt("image");
            name = bundle.getString("name");
            description = bundle.getString("description");
            price = bundle.getString("price");

            imgProduct.setImageResource(image);
            tvName.setText(name);
            tvDescription.setText(description);
            tvPrice.setText(price);
            tvQuantity.setText(String.valueOf(quantity));
        }

        // Tăng số lượng
        btnPlus.setOnClickListener(v -> {
            quantity++;
            tvQuantity.setText(String.valueOf(quantity));
        });

        // Giảm số lượng
        btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                tvQuantity.setText(String.valueOf(quantity));
            }
        });

        // Thêm vào giỏ hàng
        btnAddToCart.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("CART", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            String existingCart = prefs.getString("cartItems", "[]");

            try {
                JSONArray jsonArray = new JSONArray(existingCart);
                boolean found = false;

                // ✅ So sánh theo image để đảm bảo đúng sản phẩm
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    if (obj.getInt("image") == image) {
                        int oldQty = obj.getInt("quantity");
                        obj.put("quantity", oldQty + quantity);
                        found = true;
                        break;
                    }
                }

                // Nếu chưa có thì thêm mới
                if (!found) {
                    JSONObject item = new JSONObject();
                    item.put("name", name);
                    item.put("price", price);
                    item.put("desc", description);
                    item.put("image", image);
                    item.put("quantity", quantity);
                    jsonArray.put(item);
                }

                // Lưu lại
                editor.putString("cartItems", jsonArray.toString());
                editor.apply();

                // Log kiểm tra
                Log.d("CART_DATA", "Saved Cart: " + jsonArray.toString());
                Toast.makeText(this, "Đã thêm vào giỏ!", Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(this, "Lỗi khi thêm vào giỏ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

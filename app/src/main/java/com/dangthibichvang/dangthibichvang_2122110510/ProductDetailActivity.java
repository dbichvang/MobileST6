package com.dangthibichvang.dangthibichvang_2122110510;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

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

        imgProduct = findViewById(R.id.imgProduct);
        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        tvPrice = findViewById(R.id.tvPrice);
        tvQuantity = findViewById(R.id.tvQuantity);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnAddToCart = findViewById(R.id.btnAddToCart);

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

        btnPlus.setOnClickListener(v -> {
            quantity++;
            tvQuantity.setText(String.valueOf(quantity));
        });

        btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                tvQuantity.setText(String.valueOf(quantity));
            }
        });

        btnAddToCart.setOnClickListener(v -> {
            Product product = new Product(image, name, description, price, "");
            product.setQuantity(quantity);

            CartManager.getInstance().addToCart(product, this);
            Toast.makeText(this, "Đã thêm vào giỏ!", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        });
    }
}

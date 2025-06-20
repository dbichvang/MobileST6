package com.dangthibichvang.dangthibichvang_2122110510;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView imgProduct;
    TextView tvName, tvDescription, tvPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        imgProduct = findViewById(R.id.imgProduct);
        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        tvPrice = findViewById(R.id.tvPrice);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imgProduct.setImageResource(bundle.getInt("image"));
            tvName.setText(bundle.getString("name"));
            tvDescription.setText(bundle.getString("description"));
            tvPrice.setText(bundle.getString("price"));
        }
    }
}

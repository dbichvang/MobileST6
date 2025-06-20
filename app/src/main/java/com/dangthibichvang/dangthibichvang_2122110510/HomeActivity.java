package com.dangthibichvang.dangthibichvang_2122110510;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ListView lvProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvProducts = findViewById(R.id.lvProducts);

        List<Product> products = new ArrayList<>();
        products.add(new Product(R.drawable.adidas1, "Tên sản phẩm", "Đây là giày adidas loại 1", "135.000đ"));
        products.add(new Product(R.drawable.adidas2, "Tên sản phẩm", "Giày thể thao phiên bản mùa hè", "200.000đ"));
        products.add(new Product(R.drawable.adidas3, "Tên sản phẩm", "Túi xách adidas", "250.000đ"));
        products.add(new Product(R.drawable.giaytennis, "Tên sản phẩm", "Giày thời trang thể thao", "400.000đ"));

        ProductAdapter adapter = new ProductAdapter(this, products);
        lvProducts.setAdapter(adapter);

        // ✅ Di chuyển đoạn này vào trong onCreate()
        lvProducts.setOnItemClickListener((parent, view, position, id) -> {
            Product selectedProduct = products.get(position);

            Intent intent = new Intent(HomeActivity.this, ProductDetailActivity.class);
            intent.putExtra("image", selectedProduct.getImageResId());
            intent.putExtra("name", selectedProduct.getName());
            intent.putExtra("description", selectedProduct.getDescription());
            intent.putExtra("price", selectedProduct.getPrice());

            startActivity(intent);
        });
    }
}

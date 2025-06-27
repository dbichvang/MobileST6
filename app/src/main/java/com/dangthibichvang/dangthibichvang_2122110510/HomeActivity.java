package com.dangthibichvang.dangthibichvang_2122110510;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    GridView lvProducts;
    EditText etSearch;
    ImageView ivCart;
    ProductAdapter adapter;
    List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        lvProducts = findViewById(R.id.lvProducts);
        etSearch = findViewById(R.id.etSearch);
        ivCart = findViewById(R.id.ivCart);

        TextView txtGiay = findViewById(R.id.txtGiay);
        TextView txtTui = findViewById(R.id.txtTui);
        TextView txtNon = findViewById(R.id.txtNon);
        TextView txtTatCa = findViewById(R.id.txtTatCa);
        TextView tvTitleCategory = findViewById(R.id.tvTitleCategory);

        // Danh sách sản phẩm
        products = new ArrayList<>();
        products.add(new Product(R.drawable.adidas1, "Giày Adidas 1", "Đây là giày adidas loại 1", "135.000đ", "Giày"));
        products.add(new Product(R.drawable.adidas2, "Giày Adidas 2", "Giày thể thao phiên bản mùa hè", "200.000đ", "Giày"));
        products.add(new Product(R.drawable.giaytennis, "Giày Tennis", "Giày thời trang thể thao", "400.000đ", "Giày"));
        products.add(new Product(R.drawable.g1, "Giày Sneaker 1", "Giày sneaker phong cách Hàn Quốc", "299.000đ", "Giày"));
        products.add(new Product(R.drawable.g2, "Giày Sneaker 2", "Thiết kế năng động, thoải mái", "320.000đ", "Giày"));
        products.add(new Product(R.drawable.g3, "Giày Sneaker 3", "Giày thể thao chống trơn trượt", "280.000đ", "Giày"));
        products.add(new Product(R.drawable.g4, "Giày Thể Thao Nữ", "Giày nhẹ, êm chân", "350.000đ", "Giày"));
        products.add(new Product(R.drawable.g5, "Giày Chạy Bộ", "Giày chuyên chạy bộ cao cấp", "390.000đ", "Giày"));
        products.add(new Product(R.drawable.g6, "Giày Đi Học", "Giày bền, phù hợp học sinh", "270.000đ", "Giày"));
        products.add(new Product(R.drawable.g7, "Giày Phối Màu", "Giày thời trang phối màu độc đáo", "310.000đ", "Giày"));
        products.add(new Product(R.drawable.g8, "Giày Unisex", "Phù hợp cả nam và nữ", "330.000đ", "Giày"));

        products.add(new Product(R.drawable.adidas3, "Túi Adidas", "Túi xách adidas", "250.000đ", "Túi"));
        products.add(new Product(R.drawable.t1, "Túi Đeo Chéo 1", "Túi đeo chéo thời trang", "210.000đ", "Túi"));
        products.add(new Product(R.drawable.t2, "Túi Vải Canvas", "Túi học sinh sinh viên", "180.000đ", "Túi"));
        products.add(new Product(R.drawable.t3, "Túi Mini", "Túi nhỏ gọn đựng điện thoại", "150.000đ", "Túi"));
        products.add(new Product(R.drawable.t4, "Túi Đeo Vai", "Túi nữ thanh lịch", "250.000đ", "Túi"));
        products.add(new Product(R.drawable.t5, "Túi Trống Tập Gym", "Túi đựng đồ tập thể thao", "300.000đ", "Túi"));
        products.add(new Product(R.drawable.t6, "Túi Du Lịch", "Túi cỡ lớn phù hợp đi du lịch", "350.000đ", "Túi"));
        products.add(new Product(R.drawable.t7, "Túi Đựng Laptop", "Túi chống sốc bảo vệ laptop", "280.000đ", "Túi"));
        products.add(new Product(R.drawable.t8, "Túi Tote", "Túi vải thời trang đi học, đi chơi", "190.000đ", "Túi"));
        products.add(new Product(R.drawable.t9, "Túi Unisex", "Túi phong cách cá tính, đa năng", "230.000đ", "Túi"));

        products.add(new Product(R.drawable.n1, "Nón Lưỡi Trai 1", "Nón thời trang phong cách đường phố", "120.000đ", "Nón"));
        products.add(new Product(R.drawable.n2, "Nón Lưỡi Trai 2", "Thiết kế đơn giản, trẻ trung", "110.000đ", "Nón"));
        products.add(new Product(R.drawable.n3, "Nón Thể Thao", "Chống nắng, nhẹ và thoáng", "150.000đ", "Nón"));
        products.add(new Product(R.drawable.n4, "Nón Đẹp", "Nón lưỡi trai thời trang", "130.000đ", "Nón"));
        products.add(new Product(R.drawable.n5, "Nón Snapback", "Nón hiphop cá tính", "180.000đ", "Nón"));
        products.add(new Product(R.drawable.n6, "Nón Vải Dù", "Chống nước, chống nắng", "140.000đ", "Nón"));
        products.add(new Product(R.drawable.n7, "Nón Jean", "Nón làm từ vải jean cá tính", "160.000đ", "Nón"));
        products.add(new Product(R.drawable.n8, "Nón Gấu", "Nón có hình gấu dễ thương", "125.000đ", "Nón"));
        products.add(new Product(R.drawable.n9, "Nón Trơn", "Thiết kế đơn giản không logo", "100.000đ", "Nón"));
        products.add(new Product(R.drawable.n10, "Nón Unisex", "Phù hợp cho cả nam và nữ", "115.000đ", "Nón"));

        // Khởi tạo adapter
        adapter = new ProductAdapter(this, products);
        lvProducts.setAdapter(adapter);

        // Sự kiện lọc theo danh mục
        txtTatCa.setOnClickListener(v -> {
            adapter.filterByCategory("Tất cả");
            highlightCategory(txtTatCa, txtGiay, txtTui, txtNon);
            tvTitleCategory.setText("Tất cả sản phẩm");
        });

        txtGiay.setOnClickListener(v -> {
            adapter.filterByCategory("Giày");
            highlightCategory(txtGiay, txtTatCa, txtTui, txtNon);
            tvTitleCategory.setText("Danh mục: Giày");
        });

        txtTui.setOnClickListener(v -> {
            adapter.filterByCategory("Túi");
            highlightCategory(txtTui, txtTatCa, txtGiay, txtNon);
            tvTitleCategory.setText("Danh mục: Túi xách");
        });

        txtNon.setOnClickListener(v -> {
            adapter.filterByCategory("Nón");
            highlightCategory(txtNon, txtTatCa, txtGiay, txtTui);
            tvTitleCategory.setText("Danh mục: Nón");
        });

        // Tìm kiếm
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Click sản phẩm
        lvProducts.setOnItemClickListener((parent, view, position, id) -> {
            Product selected = (Product) adapter.getItem(position);
            Intent intent = new Intent(HomeActivity.this, ProductDetailActivity.class);
            intent.putExtra("image", selected.getImageResId());
            intent.putExtra("name", selected.getName());
            intent.putExtra("description", selected.getDescription());
            intent.putExtra("price", selected.getPrice());
            startActivity(intent);
        });

        // Mở giỏ hàng
        ivCart.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }

    // 👉 Hàm làm nổi bật danh mục được chọn
    private void highlightCategory(TextView selected, TextView... others) {
        selected.setBackgroundResource(R.drawable.bg_category_selected);
        selected.setTextColor(Color.WHITE);
        for (TextView tv : others) {
            tv.setBackgroundResource(R.drawable.bg_category);
            tv.setTextColor(Color.BLACK);
        }
    }
}

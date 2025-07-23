package com.dangthibichvang.dangthibichvang_2122110510;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {

    TextView tvWelcome;
    Button btnChangePassword, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Button btnBackToHome = findViewById(R.id.btnBackToHome);
        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(UserActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });


        tvWelcome = findViewById(R.id.tvWelcome);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnLogout = findViewById(R.id.btnLogout);

        String username = getIntent().getStringExtra("username");
        tvWelcome.setText("Xin chào, " + username);

        btnChangePassword.setOnClickListener(v -> {
            Intent intent = new Intent(UserActivity.this, ChangePasswordActivity.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> {
            Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UserActivity.this, LoginActivity.class));
            finish();
        });
    }
}

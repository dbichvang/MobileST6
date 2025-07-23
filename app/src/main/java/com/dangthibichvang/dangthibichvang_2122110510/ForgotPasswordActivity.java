package com.dangthibichvang.dangthibichvang_2122110510;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText etUsername;
    Button btnResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etUsername = findViewById(R.id.etUsername);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        btnResetPassword.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();

            if (username.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
                return;
            }

            if (username.equals("QQQQQQ")) {
                String newPassword = "ABCXYZ";
                Toast.makeText(this, "Mật khẩu mới: " + newPassword, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Tên đăng nhập không đúng!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.dangthibichvang.dangthibichvang_2122110510;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText etOldPassword, etNewPassword, etConfirmPassword;
    Button btnSubmitChange;

    SharedPreferences prefs;
    public static final String PREFS_NAME = "USER_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        etOldPassword = findViewById(R.id.etOldPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSubmitChange = findViewById(R.id.btnSubmitChange);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        String currentPassword = prefs.getString("password", "123456");

        btnSubmitChange.setOnClickListener(v -> {
            String oldPass = etOldPassword.getText().toString().trim();
            String newPass = etNewPassword.getText().toString().trim();
            String confirmPass = etConfirmPassword.getText().toString().trim();

            if (!oldPass.equals(currentPassword)) {
                Toast.makeText(this, "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
            } else if (newPass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ mật khẩu mới", Toast.LENGTH_SHORT).show();
            } else if (!newPass.equals(confirmPass)) {
                Toast.makeText(this, "Mật khẩu mới không khớp", Toast.LENGTH_SHORT).show();
            } else if (newPass.length() < 6) {
                Toast.makeText(this, "Mật khẩu mới phải ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
            } else {
                prefs.edit().putString("password", newPass).apply();
                Toast.makeText(this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}

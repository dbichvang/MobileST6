package com.dangthibichvang.dangthibichvang_2122110510;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword, edtConfirmPassword;
    Button btnRegister;
    String url = "https://6870657c7ca4d06b34b6af18.mockapi.io/LoginRegisterAPI/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = findViewById(R.id.txtdk);
        edtPassword = findViewById(R.id.txtpass);
        edtConfirmPassword = findViewById(R.id.txtpass2);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Character.isUpperCase(username.charAt(0)) || !username.matches("^[A-Za-z0-9]{6,10}$")) {
            Toast.makeText(this, "Username phải bắt đầu bằng chữ hoa và dài 6-10 ký tự, không chứa ký tự đặc biệt", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6 || password.length() > 20) {
            Toast.makeText(this, "Mật khẩu phải dài từ 6 đến 20 ký tự", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject body = new JSONObject();
        try {
            body.put("username", username);
            body.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body,
                response -> {
                    Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Lỗi đăng ký", Toast.LENGTH_SHORT).show();
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}

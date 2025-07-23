package com.dangthibichvang.dangthibichvang_2122110510;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin, btnRegister;
    TextView tvForgotPassword;
    String url = "https://6870657c7ca4d06b34b6af18.mockapi.io/LoginRegisterAPI/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.editTextText);
        edtPassword = findViewById(R.id.editTextTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        tvForgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
        });

        btnLogin.setOnClickListener(v -> loginUser());

        btnRegister.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(i);
        });
    }

    private void loginUser() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ", Toast.LENGTH_SHORT).show();
            return;
        }

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> checkUser(response, username, password),
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Lỗi kết nối MockAPI", Toast.LENGTH_SHORT).show();
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void checkUser(JSONArray response, String username, String password) {
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject user = response.getJSONObject(i);
                String savedUsername = user.getString("username");
                String savedPassword = user.getString("password");

                if (username.equals(savedUsername) && password.equals(savedPassword)) {
                    Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                    Intent intentHome = new Intent(this, HomeActivity.class);
                    intentHome.putExtra("username", username);
                    startActivity(intentHome);
                    finish();
                    return;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
    }
}

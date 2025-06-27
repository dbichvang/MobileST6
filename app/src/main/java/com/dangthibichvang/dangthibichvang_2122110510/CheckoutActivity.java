package com.dangthibichvang.dangthibichvang_2122110510;

import android.content.*;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    ListView lvSelectedItems;
    TextView tvTotalCheckout;
    EditText etCustomerName, etPhone, etAddress;
    Button btnConfirm;

    List<CartItem> selectedItems;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        lvSelectedItems = findViewById(R.id.lvSelectedItems);
        tvTotalCheckout = findViewById(R.id.tvTotalCheckout);
        etCustomerName = findViewById(R.id.etCustomerName);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        btnConfirm = findViewById(R.id.btnConfirm);
        prefs = getSharedPreferences("CART", MODE_PRIVATE);

        String json = getIntent().getStringExtra("selectedItems");
        Type listType = new TypeToken<List<CartItem>>() {}.getType();
        selectedItems = new Gson().fromJson(json, listType);

        if (selectedItems == null || selectedItems.isEmpty()) {
            Toast.makeText(this, "Không có sản phẩm nào được chọn!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                convertItemsToStringList(selectedItems)
        );
        lvSelectedItems.setAdapter(adapter);

        int total = 0;
        for (CartItem item : selectedItems) {
            int price = Integer.parseInt(item.price.replace(".", "").replace("đ", ""));
            total += price * item.quantity;
        }
        tvTotalCheckout.setText("Tổng tiền: " + total + "đ");

        btnConfirm.setOnClickListener(v -> {
            if (etCustomerName.getText().toString().isEmpty() ||
                    etPhone.getText().toString().isEmpty() ||
                    etAddress.getText().toString().isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            removePurchasedItemsFromCart();

            Toast.makeText(this, "Đặt hàng thành công! Cảm ơn bạn " + etCustomerName.getText().toString(), Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, HomeActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });
    }

    private List<String> convertItemsToStringList(List<CartItem> items) {
        List<String> list = new ArrayList<>();
        for (CartItem item : items) {
            list.add(item.name + " - SL: " + item.quantity + " - " + item.price);
        }
        return list;
    }

    private void removePurchasedItemsFromCart() {
        String currentJson = prefs.getString("cartItems", "[]");
        try {
            JSONArray currentArray = new JSONArray(currentJson);
            JSONArray newArray = new JSONArray();

            for (int i = 0; i < currentArray.length(); i++) {
                JSONObject obj = currentArray.getJSONObject(i);
                boolean found = false;

                for (CartItem purchased : selectedItems) {
                    if (purchased.name.equals(obj.getString("name"))) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    newArray.put(obj);
                }
            }

            prefs.edit().putString("cartItems", newArray.toString()).apply();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

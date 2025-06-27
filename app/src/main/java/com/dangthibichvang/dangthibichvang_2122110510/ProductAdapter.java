package com.dangthibichvang.dangthibichvang_2122110510;

import android.content.Context;
import android.view.*;
import android.widget.*;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<Product> originalList;
    private List<Product> filteredList;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.originalList = products;
        this.filteredList = new ArrayList<>(products);
    }

    public void filter(String keyword) {
        keyword = keyword.toLowerCase();
        filteredList.clear();

        if (keyword.isEmpty()) {
            filteredList.addAll(originalList);
        } else {
            for (Product p : originalList) {
                if (p.getName().toLowerCase().contains(keyword)) {
                    filteredList.add(p);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void filterByCategory(String category) {
        filteredList.clear();

        if (category.equalsIgnoreCase("Tất cả")) {
            filteredList.addAll(originalList);
        } else {
            for (Product p : originalList) {
                if (p.getCategory().equalsIgnoreCase(category)) {
                    filteredList.add(p);
                }
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        ImageView imgProduct;
        TextView tvName, tvPrice;
        Button btnAddToCart;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
            holder = new ViewHolder();
            holder.imgProduct = convertView.findViewById(R.id.imgProduct);
            holder.tvName = convertView.findViewById(R.id.tvName);
            holder.tvPrice = convertView.findViewById(R.id.tvPrice);
            holder.btnAddToCart = convertView.findViewById(R.id.btnAddToCart);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product p = filteredList.get(position);
        holder.imgProduct.setImageResource(p.getImageResId());
        holder.tvName.setText(p.getName());
        holder.tvPrice.setText("Giá: " + p.getPrice());

        holder.btnAddToCart.setOnClickListener(v -> {
            SharedPreferences prefs = context.getSharedPreferences("CART", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            String existingCart = prefs.getString("cartItems", "[]");
            try {
                JSONArray jsonArray = new JSONArray(existingCart);
                boolean found = false;

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    if (obj.getString("name").equals(p.getName())) {
                        int qty = obj.getInt("quantity");
                        obj.put("quantity", qty + 1);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    JSONObject item = new JSONObject();
                    item.put("name", p.getName());
                    item.put("price", p.getPrice());
                    item.put("desc", p.getDescription());
                    item.put("image", p.getImageResId());
                    item.put("quantity", 1);
                    jsonArray.put(item);
                }

                editor.putString("cartItems", jsonArray.toString());
                editor.apply();
                Toast.makeText(context, "Đã thêm vào giỏ!", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        return convertView;
    }
}

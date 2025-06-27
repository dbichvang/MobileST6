package com.dangthibichvang.dangthibichvang_2122110510;

import android.content.Context;
import android.view.*;
import android.widget.*;

import java.util.List;

public class CartAdapter extends BaseAdapter {

    Context context;
    List<CartItem> items;
    Runnable updateTotalCallback;

    public CartAdapter(Context context, List<CartItem> items, Runnable updateTotalCallback) {
        this.context = context;
        this.items = items;
        this.updateTotalCallback = updateTotalCallback;
    }

    @Override
    public int getCount() { return items.size(); }

    @Override
    public Object getItem(int position) { return items.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    static class ViewHolder {
        ImageView img;
        TextView tvName, tvPrice, tvQty, tvTotalItem;
        Button btnPlus, btnMinus, btnDelete;
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        ViewHolder h;
        if (view == null) {
            h = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
            h.img = view.findViewById(R.id.img);
            h.tvName = view.findViewById(R.id.tvName);
            h.tvPrice = view.findViewById(R.id.tvPrice);
            h.tvQty = view.findViewById(R.id.tvQuantity);
            h.tvTotalItem = view.findViewById(R.id.tvTotalItem);
            h.btnPlus = view.findViewById(R.id.btnPlus);
            h.btnMinus = view.findViewById(R.id.btnMinus);
            h.btnDelete = view.findViewById(R.id.btnDelete);
            view.setTag(h);
        } else {
            h = (ViewHolder) view.getTag();
        }

        CartItem item = items.get(pos);
        h.img.setImageResource(item.image);
        h.tvName.setText(item.name);
        h.tvPrice.setText(item.price);
        h.tvQty.setText(String.valueOf(item.quantity));
        h.tvTotalItem.setText(formatPrice(item.price, item.quantity));

        h.btnPlus.setOnClickListener(v -> {
            item.quantity++;
            notifyDataSetChanged();
            updateTotalCallback.run();
        });

        h.btnMinus.setOnClickListener(v -> {
            if (item.quantity > 1) item.quantity--;
            notifyDataSetChanged();
            updateTotalCallback.run();
        });

        h.btnDelete.setOnClickListener(v -> {
            items.remove(pos);
            notifyDataSetChanged();
            updateTotalCallback.run();
        });

        CheckBox cbSelect = view.findViewById(R.id.checkboxSelect);
        cbSelect.setChecked(item.isSelected);
        cbSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.isSelected = isChecked;
            updateTotalCallback.run();
        });

        return view;
    }


    private String formatPrice(String price, int quantity) {
        try {
            int singlePrice = Integer.parseInt(price.replace(".", "").replace("đ", ""));
            int total = singlePrice * quantity;
            return "Tổng: " + total + "đ";
        } catch (Exception e) {
            return "Tổng: -";
        }
    }
}

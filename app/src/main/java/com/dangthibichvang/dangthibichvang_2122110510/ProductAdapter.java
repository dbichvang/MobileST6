package com.dangthibichvang.dangthibichvang_2122110510;

import android.content.Context;
import android.view.*;
import android.widget.*;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.productList = products;
    }

    @Override
    public int getCount() { return productList.size(); }

    @Override
    public Object getItem(int position) { return productList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    static class ViewHolder {
        ImageView imgProduct;
        TextView tvName, tvDesc, tvPrice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
            holder = new ViewHolder();
            holder.imgProduct = convertView.findViewById(R.id.imgProduct);
            holder.tvName = convertView.findViewById(R.id.tvName);
            holder.tvDesc = convertView.findViewById(R.id.tvDesc);
            holder.tvPrice = convertView.findViewById(R.id.tvPrice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product p = productList.get(position);
        holder.imgProduct.setImageResource(p.getImageResId());
        holder.tvName.setText(p.getName());
        holder.tvDesc.setText(p.getDescription());
        holder.tvPrice.setText("Giá: " + p.getPrice());


        return convertView;
    }
}

package com.example.master;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class product_item_adapter extends RecyclerView.Adapter<product_item_adapter.products> {
    private ArrayList<products_data> arrayList;
    private Context context;

    public product_item_adapter(ArrayList<products_data> list)
    {
        this.arrayList = list;
    }
    @NonNull
    @Override
    public product_item_adapter.products onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new products(view);
    }

    @Override
    public void onBindViewHolder(@NonNull product_item_adapter.products holder, int position) {
        products_data pdf = arrayList.get(position);
        holder.product_name.setText(pdf.getName());
        // Picasso.get().load("https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.sbilife.co.in%2Fnewhomepagemobilebanner6&tbnid=MWo4t-t9AKnjCM&vet=12ahUKEwjDz4G7m9eBAxXkTGwGHYpuB5IQMygCegQIARBy..i&imgrefurl=https%3A%2F%2Fwww.sbilife.co.in%2F&docid=0WqHfCapX6suFM&w=768&h=800&q=sbilife%20products&ved=2ahUKEwjDz4G7m9eBAxXkTGwGHYpuB5IQMygCegQIARBy").into(holder.product_poster);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
       // return 0;
    }

    public class products extends RecyclerView.ViewHolder {
        TextView product_name;
        ImageView product_poster;
        public products(@NonNull View itemView) {
            super(itemView);
            product_name=itemView.findViewById(R.id.product_name);
            product_poster=itemView.findViewById(R.id.product_img);
        }
    }
}

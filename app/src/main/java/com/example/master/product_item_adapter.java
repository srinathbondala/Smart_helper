package com.example.master;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull product_item_adapter.products holder, int position) {
        try {
            products_data pdf = arrayList.get(position);
            holder.product_name.setText(pdf.getName() +"\n"+ "(" + pdf.getCode() + ")");
            Picasso.get().load(pdf.getUrl()).into(holder.product_poster);
            holder.description.setText(pdf.getDescription());
            holder.menue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "showing options", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e)
        {
            Toast.makeText(context, "error occurred please try later", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class products extends RecyclerView.ViewHolder {
        TextView product_name,description;
        ImageView product_poster,menue;
        public products(@NonNull View itemView) {
            super(itemView);
            product_name=itemView.findViewById(R.id.product_name);
            product_poster=itemView.findViewById(R.id.product_img);
            description=itemView.findViewById(R.id.description_item);
            menue=itemView.findViewById(R.id.delete_product);
        }
    }
}

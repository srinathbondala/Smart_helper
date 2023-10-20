package com.example.master;

import static com.example.master.MainActivity.A_data;
import static com.example.master.MainActivity.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public interface add_data_from_firebase_class  {
    FirebaseDatabase mdata=FirebaseDatabase.getInstance();
    default void add_data_from_firebase() {
        //mdata = FirebaseDatabase.getInstance();
        Query query = mdata.getReference().child("Product_Data");
        query.addListenerForSingleValueEvent(valueEvent);
        Query query1=mdata.getReference().child("Agent_data");
        query1.addListenerForSingleValueEvent(valueEvent1);
    }
    ValueEventListener valueEvent1 = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            A_data.clear();
            try {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                            A_data.add(ds.getValue(Agent_data.class));
                        }
                    }
                    if (A_data.isEmpty()) {
                        // Toast.makeText(this, "No data available", Toast.LENGTH_LONG).show();
                        Log.d("msg","size 00000");
                    }
                }
            } catch (Exception e) {
                Log.d("error",""+e.getMessage());
            }
            //p.dismiss();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    ValueEventListener valueEvent = new ValueEventListener() {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            data.clear();
            try {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        products_data i;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                            i = ds.getValue(products_data.class);
                            data.add(i);
                        }
                    }
                    if (data.isEmpty()) {
                       // Toast.makeText(a, "No data available", Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
               // Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error",""+e.getMessage());
            }
            //p.dismiss();
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    void make_toast();
}

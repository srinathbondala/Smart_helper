package com.example.master;

import static com.example.master.MainActivity.data;
import static com.example.master.MainActivity.demo1;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class products_details extends Fragment implements add_data_from_firebase_class,View.OnClickListener{
    private product_item_adapter adapterClass;
    //public static ArrayList<products_data> data;
    private RecyclerView list;
    private FloatingActionButton fa;
  //  private FirebaseDatabase mdata;
    private ProgressDialog p;
   // private DatabaseReference databaseReference;
    private View view;
    @SuppressLint("NotifyDataSetChanged")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.products_details,container,false);
        fa = view.findViewById(R.id.floatingActionButton);
        list = view.findViewById(R.id.recyclerView);
        fa.setOnClickListener(this);
        //data = new ArrayList<>();
        list.setLayoutManager(new LinearLayoutManager(view.getContext()));
        p = new ProgressDialog(view.getContext());
        p.setMessage("Please wait....");
        p.setTitle("Loading");
        p.setCanceledOnTouchOutside(false);
        p.setCancelable(false);
        //p.show();
        if(data.isEmpty())
        {
            data = new ArrayList<>();
            add_data_from_firebase();
        }
        adapterClass = new product_item_adapter(data);
        list.setAdapter(adapterClass);
        adapterClass.notifyDataSetChanged();

        return view;
    }

//    private void add_data_from_firebase() {
//        mdata = FirebaseDatabase.getInstance();
//        Query query = mdata.getReference().child("Product_Data");
//        query.addListenerForSingleValueEvent(valueEvent);
//    }

    @Override
    public void make_toast() {
        p.dismiss();
    }

//    ValueEventListener valueEvent = new ValueEventListener() {
//        @SuppressLint("NotifyDataSetChanged")
//        @Override
//        public void onDataChange(@NonNull DataSnapshot snapshot) {
//            list.getRecycledViewPool().clear();
//            data.clear();
//            try {
//                if (snapshot.exists()) {
//                    for (DataSnapshot ds : snapshot.getChildren()) {
//                            products_data i;
//                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
//                                i = ds.getValue(products_data.class);
//                                data.add(i);
//
//                        }
//                    }
//                    if (data.isEmpty()) {
//                        Toast.makeText(view.getContext(), "No data available", Toast.LENGTH_LONG).show();
//                    }
//                    adapterClass.notifyDataSetChanged();
//                }
//            } catch (Exception e) {
//                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d("error",""+e.getMessage());
//            }
//            p.dismiss();
//        }
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//
//        }
//    };

    @Override
    public void onClick(View view) {
        if(view==fa)
        {
            Toast.makeText(view.getContext(), "Adding and deleting products", Toast.LENGTH_SHORT).show();
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.swipe_in_right,R.anim.swipe_out_left);
            transaction.replace(R.id.main_fragment,new products_edit()).commit();
            demo1="edit_details";
        }
    }
}

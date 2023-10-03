package com.example.master;

import static com.example.master.MainActivity.demo1;

import android.annotation.SuppressLint;
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

public class products_details extends Fragment implements View.OnClickListener{
    private product_item_adapter adapterClass;
    private ArrayList<products_data> data;
    private RecyclerView list;
    private FloatingActionButton fa;
    @SuppressLint("NotifyDataSetChanged")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.products_details,container,false);
        fa = view.findViewById(R.id.floatingActionButton);
        list = view.findViewById(R.id.recyclerView);
        fa.setOnClickListener(this);
        data = new ArrayList<>();
        list.setLayoutManager(new LinearLayoutManager(view.getContext()));
        for(int i=0;i<10;i++)
        {
            data.add(new products_data("smart"+i,"","code"+i,"yearly",""));
        }
        adapterClass = new product_item_adapter(data);
        list.setAdapter(adapterClass);
        adapterClass.notifyDataSetChanged();
        return view;
    }

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

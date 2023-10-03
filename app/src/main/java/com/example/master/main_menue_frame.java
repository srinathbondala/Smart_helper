package com.example.master;

import static com.example.master.MainActivity.demo1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class main_menue_frame extends Fragment implements View.OnClickListener{
    private CardView agent_details,add_data,product_details,create_reprot;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_menue_frame,container,false);
        agent_details=view.findViewById(R.id.agent_details);
        add_data=view.findViewById(R.id.policy_add);
        product_details=view.findViewById(R.id.product);
        create_reprot=view.findViewById(R.id.report);
        create_reprot.setOnClickListener(this);
        product_details.setOnClickListener(this);
        add_data.setOnClickListener(this);
        agent_details.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view==agent_details) {
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.swipe_in_left, R.anim.swipe_out_right);
            transaction.replace(R.id.main_fragment, new agents_details_frame()).commit();
            // requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,new agents_details_frame()).commit();
            demo1 = "Agent";
        } else if (view==add_data) {
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.swipe_in_left, R.anim.swipe_out_right);
            transaction.replace(R.id.main_fragment, new add_data_frame()).commit();
            demo1="add_data";
        } else if (view==product_details) {
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.swipe_in_right,R.anim.swipe_out_left);
            transaction.replace(R.id.main_fragment,new products_details()).commit();
            demo1="product";
        }
        else if(view==create_reprot){
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.swipe_in_right,R.anim.swipe_out_left);
            transaction.replace(R.id.main_fragment,new create_report_fragment()).commit();
            demo1="create_report";
        }
    }
}

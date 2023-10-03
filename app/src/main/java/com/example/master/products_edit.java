package com.example.master;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class products_edit extends Fragment implements View.OnClickListener{
    private TextView add,lit_qr,user_faq,lit_faq;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.products_edit,container,false);
        add= view.findViewById(R.id.user_qr_code);
        lit_qr = view.findViewById(R.id.lit_qr);
        user_faq = view.findViewById(R.id.user_faqs);
        lit_faq = view.findViewById(R.id.lit_faqs);
        add.setOnClickListener(this);
        user_faq.setOnClickListener(this);
        add.setTextColor(Color.parseColor("#00ddff"));
        lit_qr.setBackgroundColor(Color.parseColor("#00ddff"));
        user_faq.setTextColor(Color.parseColor("#000000"));
        lit_faq.setBackgroundColor(0);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.main_profile, new details_add()).commit();
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view==add)
        {
            add.setTextColor(Color.parseColor("#00ddff"));
            lit_qr.setBackgroundColor(Color.parseColor("#00ddff"));
            user_faq.setTextColor(Color.parseColor("#000000"));
            lit_faq.setBackgroundColor(0);
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.main_profile, new details_add()).commit();
        }
        if(view==user_faq)
        {
            user_faq.setTextColor(Color.parseColor("#00ddff"));
            lit_faq.setBackgroundColor(Color.parseColor("#00ddff"));
            add.setTextColor(Color.parseColor("#000000"));
            lit_qr.setBackgroundColor(0);
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.main_profile, new product_del_edit()).commit();
        }
    }
}

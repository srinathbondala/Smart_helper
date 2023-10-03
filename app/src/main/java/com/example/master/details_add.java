package com.example.master;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class details_add extends Fragment implements View.OnClickListener{
    private Button select_file,submit;
    private ConstraintLayout image_select_container;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.details_add,container,false);
            select_file = view.findViewById(R.id.uplode);
            select_file.setOnClickListener(this);
            submit = view.findViewById(R.id.submit);
            image_select_container = view.findViewById(R.id.image_select_container);
            submit.setOnClickListener(this);
            image_select_container.setVisibility(View.GONE);
            return view;
    }

    @Override
    public void onClick(View view) {
        if (select_file.equals(view)) {
            image_select_container.setVisibility(View.VISIBLE);
        } else if (submit.equals(view)) {
            Toast.makeText(view.getContext(), "submit", Toast.LENGTH_SHORT).show();
        }
    }
}

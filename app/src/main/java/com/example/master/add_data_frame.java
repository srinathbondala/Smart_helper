package com.example.master;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class add_data_frame extends Fragment {

    private AutoCompleteTextView codes,product_name;
    private Spinner modes;
    private EditText prem,date,proposal_no;
    private Button submit;
    private String smode;
    private List<String> mode_name= Arrays.asList("Monthly","Quarterly","Yearly");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_data_frame,container,false);
        codes=view.findViewById(R.id.code1);
        product_name=view.findViewById(R.id.product_name_in);
        modes=view.findViewById(R.id.spinner);
        prem = view.findViewById(R.id.premium_in);
        date=view.findViewById(R.id.doc1);
        proposal_no = view.findViewById(R.id.phno1);
        submit = view.findViewById(R.id.button1);
        try {
            Date d = new Date();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdp = new SimpleDateFormat("dd/MM/yyyy");
            date.setText(String.valueOf(sdp.format(date)));
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.simple_snipper_item, mode_name);
            arrayAdapter.setDropDownViewResource(R.layout.simple_snipper_item);
            modes.setAdapter(arrayAdapter);
            modes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    smode = mode_name.get(i);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Toast.makeText(view.getContext(), "Please select an mode", Toast.LENGTH_SHORT).show();;
                }
            });
        }catch (Exception e)
        {}
        return view;
    }
}

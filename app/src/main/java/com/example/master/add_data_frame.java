package com.example.master;

import static com.example.master.MainActivity.A_data;
import static com.example.master.MainActivity.data;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class add_data_frame extends Fragment implements add_data_from_firebase_class{

    private AutoCompleteTextView codes,product_name;
    private Spinner modes;
    private EditText prem,date,proposal_no,term;
    private Button submit;
    private String smode,sname,scode;
    private List<String> mode_name1= Arrays.asList("Monthly","Quarterly","Yearly");
    List<String> product_name_list = new ArrayList<>();
    List<String> agent_list = new ArrayList<>();
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
        term = view.findViewById(R.id.term);
        if(data.isEmpty())
        {
            data = new ArrayList<>();
            add_data_from_firebase();
        }
        for(products_data x: data)
        {
            product_name_list.add(x.name);
        }
        for(int i=0;i<10;i++)
        {
            agent_list.add("6012u"+i);
        }
        for(Agent_data y : A_data) {
            agent_list.add(y.IA_Code + " (" + y.IA_Name.trim()+")");
        }
        try {
            Date d = new Date();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdp = new SimpleDateFormat("dd/MM/yyyy");
            date.setText(String.valueOf(sdp.format(d)));
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.simple_snipper_item, mode_name1);
            arrayAdapter.setDropDownViewResource(R.layout.simple_snipper_item);
            modes.setAdapter(arrayAdapter);
            CustomArrayAdapter arrayAdapter1 = new CustomArrayAdapter(view.getContext(), R.layout.simple_snipper_item, product_name_list);
           // ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(view.getContext(), R.layout.simple_snipper_item,product_name_list);
            product_name.setAdapter(arrayAdapter1);
            //ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(view.getContext(), R.layout.simple_snipper_item,agent_list);
            CustomArrayAdapter arrayAdapter2 = new CustomArrayAdapter(view.getContext(), R.layout.simple_snipper_item, agent_list);
            codes.setAdapter(arrayAdapter2);
            codes.setThreshold(1);
            product_name.setThreshold(1);
            codes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    scode = agent_list.get(position);
                }
            });
//            codes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
//                    if (!hasFocus) {
//                        String input = ((AutoCompleteTextView) v).getText().toString();
//                        if (!agent_list.contains(input)) {
//                            Toast.makeText(view.getContext(), "Please Enter a Valid Agent", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//            });
            product_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    sname = product_name_list.get(i);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Toast.makeText(view.getContext(), "Please Enter a Product", Toast.LENGTH_SHORT).show();
                }
            });
            modes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    smode = mode_name1.get(i);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Toast.makeText(view.getContext(), "Please select a mode", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e)
        {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    @Override
    public void make_toast() {
        //p.dismiss();
    }
}

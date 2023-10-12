package com.example.master;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class agents_details_frame extends Fragment implements View.OnClickListener{
    private EditText id,name,dob,mail,doc,mobile_no;
    String emailPat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String datepat = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    ProgressDialog p;
    Button submit;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agent_details_frame,container,false);
        id=view.findViewById(R.id.code);
        name=view.findViewById(R.id.name);
        dob=view.findViewById(R.id.dob);
        mail=view.findViewById(R.id.mail);
        doc=view.findViewById(R.id.doc);
        mobile_no=view.findViewById(R.id.phno);
        submit=view.findViewById(R.id.button);
        submit.setOnClickListener(this);
        p=new ProgressDialog(view.getContext());
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Agent_data");
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view==submit)
        {
            String codes=id.getText().toString();
            String names = name.getText().toString();
            String mails=mail.getText().toString();
            String docs=doc.getText().toString();
            String dobs=dob.getText().toString();
            String mobile=mobile_no.getText().toString();
            if(!mails.matches(emailPat))
                mail.setError("Enter proper Email Id");
            else if(mobile.equals("")||mobile.length()<10){
                mobile_no.setError("Enter correct Mobile Number");}
            else if(codes.equals("")||codes.length()<6) {
                id.setError("enter correct IA code");}
            else if(names.equals("")){name.setError("enter Name");}
            else if (dobs.equals("")||!dobs.matches(datepat)) {
                dob.setError("Enter Proper dob");}
            else if (docs.equals("")||!docs.matches(datepat)) {
                doc.setError("Enter Proper doc");}
            else {
                submit_details(codes,names,mails,docs,dobs,mobile);
            }
        }
    }

    private void submit_details(String codes, String names, String mails, String docs, String dobs, String mobile) {
        p.setMessage("Please wait...");
        p.setTitle("Adding Data");
        p.setCanceledOnTouchOutside(false);
        p.show();
        Agent_data data = new Agent_data(codes,names,dobs,docs,mails,mobile);
        myRef.child(codes).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                p.dismiss();
                Toast.makeText(requireView().getContext(), "Agent registered Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

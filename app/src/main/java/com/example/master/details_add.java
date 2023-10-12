package com.example.master;

import static android.app.appsearch.AppSearchResult.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class details_add extends Fragment implements View.OnClickListener{
    private Button submit;
    private ConstraintLayout image_select_container;
    private static final int PICK_IMAGE_REQUEST = 1;
    private String url;
    private EditText name,code,description;
    private TextView message;
    private Spinner mode;
    private Uri imageuri;
    private ImageView imageView , select_file;
    private View view;
    ProgressDialog p;
    FirebaseDatabase database;
    private DatabaseReference myRef;
    private StorageReference reference;
    private FusedLocationProviderClient mFusedLocationClient;
    private products_data data1;
    private String sname,scode,sdisc;
    private String smode;
    private List<String> mode_name= Arrays.asList("Monthly","Quarterly","Yearly");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.details_add,container,false);
            select_file = view.findViewById(R.id.probimg);
            select_file.setOnClickListener(this);
            submit = view.findViewById(R.id.submit);
            image_select_container = view.findViewById(R.id.image_select_container);
            name=view.findViewById(R.id.product_name_input);
            code=view.findViewById(R.id.product_code_input);
            mode=view.findViewById(R.id.product_mode_input);
            description=view.findViewById(R.id.product_disc_input);
            message=view.findViewById(R.id.messagel);
            submit.setOnClickListener(this);
            imageView=view.findViewById(R.id.imageView);
            image_select_container.setVisibility(View.GONE);
            p=new ProgressDialog(view.getContext());
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference().child("Product_Data");
            reference = FirebaseStorage.getInstance().getReference();
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(view.getContext());
            try {
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.simple_snipper_item, mode_name);
                arrayAdapter.setDropDownViewResource(R.layout.simple_snipper_item);
                mode.setAdapter(arrayAdapter);
                mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        smode = mode_name.get(i);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        Toast.makeText(view.getContext(), "Please select an mode", Toast.LENGTH_SHORT).show();;
                    }
                });
                Bundle bundle = getArguments();
                if (bundle != null) {
                    String receivedData = bundle.getString("key_name");
                    if(Objects.equals(receivedData, "edit"))
                    {
                        perform_edit();
                    }
                }
            }catch (Exception e)
            {}
            return view;
    }

    @SuppressLint("SetTextI18n")
    private void perform_edit() {
        name.setText("Product Details");
        code.setText("code1234");
        mode.setSelection(1);
        description.setText("This project is much important");
        imageView.setImageResource(R.drawable.img_2);
        Toast.makeText(view.getContext(), "edit to be performed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if (select_file.equals(view)) {
            try {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }catch (Exception e)
            {}
        } else if (submit.equals(view)) {
            sname=name.getText().toString();
            scode=code.getText().toString();
            sdisc=description.getText().toString();
            try{
                if(sname.equals("")){
                   name.setError("Enter Name");
                }
                else if(scode.length()<6)
                    code.setError("Enter proper code");
                else if(sdisc.equals(""))
                    description.setError("Enter description");
                else {
                    update_data(sname,scode, smode,sdisc);
                }
            }
            catch (Exception e)
            { e.printStackTrace();}
        }
    }

    private void update_data(String sname, String scode, String smode, String sdisc) {
        p.setMessage("Please wait....");
        p.setTitle("Adding Product");
        p.setCanceledOnTouchOutside(false);
        p.setCancelable(false);
        if(imageuri != null) {
            uplodeuri(imageuri);
        } else {
            Toast.makeText(view.getContext(), "select an image", Toast.LENGTH_SHORT).show();
        }
    }

    private void uplodeuri(Uri imageuri) {
        p.show();
        StorageReference fileref = reference.child(System.currentTimeMillis() + "." + getFileExtenction(imageuri));
        fileref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        url= uri.toString();
                        data1 = new products_data(sname, url, scode, smode, sdisc);
                        myRef.child(scode).setValue(data1).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                p.dismiss();
                                Toast.makeText(view.getContext(), "Product Added Successfully", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                p.show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                p.dismiss();
                Toast.makeText(view.getContext(), "Upload failed"+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getFileExtenction(Uri murl) {
        ContentResolver cr = requireActivity().getContentResolver();
        MimeTypeMap mine = MimeTypeMap.getSingleton();
        return MimeTypeMap.getFileExtensionFromUrl(cr.getType(murl));
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            imageuri = data.getData();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(requireActivity().getContentResolver().openInputStream(imageuri));
                imageView.setImageBitmap(bitmap);
                message.setText("Image Selected");
                image_select_container.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

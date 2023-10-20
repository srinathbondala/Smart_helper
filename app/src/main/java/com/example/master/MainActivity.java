package com.example.master;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements add_data_from_firebase_class,View.OnClickListener {

    public static String demo1;
    static ArrayList<products_data> data;
    static ArrayList<Agent_data> A_data;
    ImageView home;
    //  private FirebaseDatabase mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        ProgressDialog p;
        p = new ProgressDialog(this);
        p.setMessage("Please wait....");
        p.setTitle("Loading Data");
        p.setCanceledOnTouchOutside(false);
        p.setCancelable(false);
        p.show();
        home= findViewById(R.id.driver_house);
        home.setOnClickListener(this);
        data = new ArrayList<>();
        A_data = new ArrayList<>();
        try {
            if(data.isEmpty() || A_data.isEmpty()) {
                Toast.makeText(this, "loading data....", Toast.LENGTH_SHORT).show();
                add_data_from_firebase();
            }
        }catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,new main_menue_frame()).commit();
        demo1="home";
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (p.isShowing()) {
                    p.dismiss();
                }
            }
        }, 1500);
    }
    @Override
    public void onBackPressed() {
        if(!Objects.equals(demo1, "home")) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if(Objects.equals(demo1, "Agent") || Objects.equals(demo1, "add_data")) {
                transaction.setCustomAnimations(R.anim.swipe_in_right, R.anim.swipe_out_left);
                transaction.replace(R.id.main_fragment, new main_menue_frame()).commit();
                demo1 = "home";
            }
            else if(Objects.equals(demo1, "edit_details"))
            {
                transaction.setCustomAnimations(R.anim.swipe_in_left, R.anim.swipe_out_right);
                transaction.replace(R.id.main_fragment, new products_details()).commit();
                demo1 = "product_details";
            }
            else {
                transaction.setCustomAnimations(R.anim.swipe_in_left, R.anim.swipe_out_right);
                transaction.replace(R.id.main_fragment, new main_menue_frame()).commit();
                demo1 = "home";
            }

        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Do you want to exit?");
            builder.setTitle("Alert !");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
               // overridePendingTransition(R.anim.swipe_in_left, R.anim.swipe_out_right);
                finish();
            });
            builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    public void onClick(View view) {
        if(view==home && !Objects.equals(demo1, "home"))
        {
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            if(Objects.equals(demo1, "Agent") || Objects.equals(demo1, "add_data")) {
                transaction.setCustomAnimations(R.anim.swipe_in_right, R.anim.swipe_out_left);
            }
            else {
                transaction.setCustomAnimations(R.anim.swipe_in_left, R.anim.swipe_out_right);
            }
            transaction.replace(R.id.main_fragment, new main_menue_frame()).commit();
            demo1="home";
        }
    }

    @Override
    public void make_toast() {

    }
}
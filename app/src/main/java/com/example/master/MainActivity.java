package com.example.master;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String demo1;
    ImageView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        home= findViewById(R.id.driver_house);
        home.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,new main_menue_frame()).commit();
        demo1="home";
    }
    @Override
    public void onBackPressed() {
        if(!Objects.equals(demo1, "home")) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if(demo1=="Agent"||demo1=="add_data") {
                transaction.setCustomAnimations(R.anim.swipe_in_right, R.anim.swipe_out_left);
                transaction.replace(R.id.main_fragment, new main_menue_frame()).commit();
                demo1 = "home";
            }
            else if(demo1=="edit_details")
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
            if(demo1=="Agent"||demo1=="add_data") {
                transaction.setCustomAnimations(R.anim.swipe_in_right, R.anim.swipe_out_left);
            }
            else {
                transaction.setCustomAnimations(R.anim.swipe_in_left, R.anim.swipe_out_right);
            }
            transaction.replace(R.id.main_fragment, new main_menue_frame()).commit();
            demo1="home";
        }
    }
}
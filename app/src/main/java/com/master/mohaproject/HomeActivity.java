package com.master.mohaproject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    Button search,TryBtn;
    Spinner division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        division = findViewById(R.id.Division);
        search = findViewById(R.id.srch);



        ArrayAdapter<String> divisionAdapter = new ArrayAdapter<String>(HomeActivity.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.divisions));
        divisionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        division.setAdapter(divisionAdapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                String currentdiv = division.getSelectedItem().toString();
                if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()){
                    Dialog dialog = new Dialog(HomeActivity.this);
                    dialog.setContentView(R.layout.alert_dialog);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                            WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;

                    TryBtn = (Button) dialog.findViewById(R.id.trybtn);
                    TryBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            recreate();
                        }
                    });
                    dialog.show();
                }

                else if(currentdiv.equals(null)){
                    Toast.makeText(getApplicationContext(),"Please Select Division",Toast.LENGTH_LONG).show();
                }else if(currentdiv.equals("--Select Division--")){
                    Toast.makeText(getApplicationContext(),"Please Select Division",Toast.LENGTH_LONG).show();
                }else{
                    Intent i = new Intent(HomeActivity.this, resultActivity.class);
                    i.putExtra("key", currentdiv);
                    startActivity(i);
                }

            }
        });
    }

}

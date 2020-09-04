package com.master.mohaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    Button search;
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
                String currentdiv = division.getSelectedItem().toString();
                if(currentdiv.equals(null)){
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
//
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String json;
//                try {
//                    InputStream is = getAssets().open("list.json");
//                    int size = is.available();
//                    byte[] buffer = new byte[size];
//                    is.read(buffer);
//                    is.close();
//
//                    json = new String(buffer,"UTF-8");
//                    JSONArray jsonArray = new JSONArray(json);
//
//                    for (int j = 0; j < jsonArray.length(); j++){
//                        JSONObject obj = jsonArray.getJSONObject(j);
//                        if (obj.getString("Division").equals(division.getSelectedItem().toString())){
//                            emaillist.add(obj.getString("e-mail Address"));
//                        }
//                    }
//                    Intent i = new Intent(HomeActivity.this, resultActivity.class);
//                    i.putExtra("key", emaillist);
//                    startActivity(i);
//                    finish();
//
//                } catch (IOException | JSONException e) {
//                    Toast.makeText(getApplicationContext(),"Exception executed",Toast.LENGTH_SHORT).show();
//                    e.printStackTrace();
//                }
//            }
//        });
        //
    }

}

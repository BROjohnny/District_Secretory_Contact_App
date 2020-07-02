package com.master.mohaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    Button search;
    Spinner division;
    ArrayList<String> emaillist = new ArrayList<>();
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
                Intent i = new Intent(HomeActivity.this, resultActivity.class);
                startActivity(i);
                finish();
                String json;
                try {
                    InputStream is = getAssets().open("list.json");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();

                    json = new String(buffer,"UTF-8");
                    JSONArray jsonArray = new JSONArray(json);

                    for (int j = 0; j < jsonArray.length(); j++){
                        JSONObject obj = jsonArray.getJSONObject(j);
                        if (obj.getString("Division").equals(division.getSelectedItem().toString())){
                            emaillist.add(obj.getString("e-mail Address"));
                        }
                    }
                    Toast.makeText(getApplicationContext(),emaillist.toString(),Toast.LENGTH_LONG).show();
                } catch (IOException | JSONException e) {
                    Toast.makeText(getApplicationContext(),"Exception executed",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

}

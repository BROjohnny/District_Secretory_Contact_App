package com.master.mohaproject;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class resultActivity extends AppCompatActivity {

    ImageButton backButtonimgbtn,callsecimgbtn,callassistimgbtn,callfaximgbtn,copysecimgbtn,copyassistimgbtn,copyfaximgbtn,copymailimgbtn;
    TextView emailView,secnumView,assistnumView,diviView,faxView;
    RequestQueue mQueue;
    Spinner division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        backButtonimgbtn = findViewById(R.id.backbtn);
        callsecimgbtn = findViewById(R.id.call1);
        callassistimgbtn = findViewById(R.id.call2);
        callfaximgbtn = findViewById(R.id.call3);
        copysecimgbtn = findViewById(R.id.copy1);
        copyassistimgbtn = findViewById(R.id.copy2);
        copyfaximgbtn = findViewById(R.id.copy3);
        copymailimgbtn = findViewById(R.id.copy4);
        emailView = findViewById(R.id.emailtext);
        secnumView = findViewById(R.id.secretatytext);
        diviView = findViewById(R.id.divisiontext);
        assistnumView = findViewById(R.id.assistentsectext);
        faxView = findViewById(R.id.faxtext);
        mQueue = Volley.newRequestQueue(this);
        division = findViewById(R.id.Division);

        backButtonimgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(resultActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

        callsecimgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse("tel:"+ secnumView.getText().toString());
                Intent e = new Intent(Intent.ACTION_DIAL,number);
                startActivity(e);
            }
        });

        callassistimgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse("tel:"+ assistnumView.getText().toString());
                Intent e = new Intent(Intent.ACTION_DIAL,number);
                startActivity(e);
            }
        });

        callfaximgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse("tel:"+ faxView.getText().toString());
                Intent e = new Intent(Intent.ACTION_DIAL,number);
                startActivity(e);
            }
        });

        copysecimgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData Clip = ClipData.newPlainText("EditText",secnumView.getText().toString());
                clipboard.setPrimaryClip(Clip);

                Toast.makeText(resultActivity.this,"Copied",Toast.LENGTH_LONG).show();
            }
        });

        copyassistimgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData Clip = ClipData.newPlainText("EditText",assistnumView.getText().toString());
                clipboard.setPrimaryClip(Clip);

                Toast.makeText(resultActivity.this,"Copied",Toast.LENGTH_LONG).show();
            }
        });

        copyfaximgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData Clip = ClipData.newPlainText("EditText",faxView.getText().toString());
                clipboard.setPrimaryClip(Clip);

                Toast.makeText(resultActivity.this,"Copied",Toast.LENGTH_LONG).show();
            }
        });

        copymailimgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData Clip = ClipData.newPlainText("EditText",emailView.getText().toString());
                clipboard.setPrimaryClip(Clip);

                Toast.makeText(resultActivity.this,"Copied",Toast.LENGTH_LONG).show();
            }
        });

        jasonParse();

    }

    void jasonParse(){
        String url ="https://mohawebapi2.000webhostapp.com/";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                         try {
                            JSONArray jsonArray = response.getJSONArray("usersList");
                            String div = getIntent().getStringExtra("key");
                            for (int j = 0; j < jsonArray.length(); j++){
                                JSONObject obj = jsonArray.getJSONObject(j);
                                if (obj.getString("Division").equals(div)){
                                    String setdiv = obj.getString("Division");
                                    String setsec = obj.getString("divisionalSecretary");
                                    String setassist = obj.getString("assistant");
                                    String setfax = obj.getString("fax");
                                    String setmail = obj.getString("email");

                                    diviView.setText(setdiv);
                                    secnumView.setText(setsec);
                                    assistnumView.setText(setassist);
                                    faxView.setText(setfax);
                                    emailView.setText(setmail);

                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}

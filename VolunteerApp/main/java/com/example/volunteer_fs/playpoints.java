package com.example.volunteer_fs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class playpoints extends AppCompatActivity {
    String point;
    String day;
    String mail;
    String btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playpoints);
        Intent intent=getIntent();
        day=intent.getStringExtra("day");
        mail=intent.getStringExtra("mail");
        TextView t=findViewById(R.id.textView8);
        t.setText(day);
        btn=intent.getStringExtra("btn");
        Button btntwo=findViewById(R.id.TWO);
        btntwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                point="2";
                dotit(mail,point,day);
            }
        });
        Button btnfour=findViewById(R.id.FOUR);
        btnfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                point="4";
                dotit(mail,point,day);
            }
        });
        Button btnsix=findViewById(R.id.SIX);
        btnsix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                point="6";
                dotit(mail,point,day);
            }
        });
        Button btneight=findViewById(R.id.EIGHT);
        btneight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                point="8";
                dotit(mail,point,day);
            }
        });
        Button btnten=findViewById(R.id.TEN);
        btnten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                point="10";
                dotit(mail,point,day);
            }
        });
    }

    public void dotit(String mail,String epoint,String day){
        String url = "http://ec2-43-204-215-53.ap-south-1.compute.amazonaws.com/eventpoints.php";
        TextView t=findViewById(R.id.textView8);
        //t.setText("call");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        TextView t=findViewById(R.id.textView8);
                        t.setText(response.toString());

                        JSONObject jsonObj = null;

                        try {
                            jsonObj = new JSONObject(response.toString());
                            Intent p2s = new Intent(playpoints.this,scanUser.class);
                            p2s.putExtra("day",day);
                            p2s.putExtra("btn",btn);
                            startActivity(p2s);
                            /*if(jsonObj.getString("status").equals("success")) {
                                Toast.makeText(recharge2.this, "success", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(recharge2.this, "error", Toast.LENGTH_LONG).show();
                            }*/

                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TextView t=findViewById(R.id.textView8);
                        //t.setText(day);
                        TextView t1=findViewById(R.id.textView7);
                        t1.setText(error.toString());
                        Toast.makeText(playpoints.this, "ERROR CONNECTING", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("param1", mail);
                params.put("param2", epoint);
                params.put("param3",day);
                return params;
            }
        };
        try{
            RequestQueue requestQueue= Volley.newRequestQueue(playpoints.this);
            requestQueue.add(stringRequest);}
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
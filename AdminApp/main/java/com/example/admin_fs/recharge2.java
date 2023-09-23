package com.example.admin_fs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class recharge2 extends AppCompatActivity {
    String rechargeamount;
    String choose="recharge";
    String day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge2);
        Intent intent=getIntent();
        String mail = intent.getStringExtra("mail");
        day=intent.getStringExtra("day");
        TextView t=findViewById(R.id.textView7);
        t.setText(mail);
        //String dayof=intent.getStringExtra("day");
        findViewById(R.id.R100).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rechargeamount="100";
                t.setText(rechargeamount);
                dot(mail,rechargeamount,choose);
            }
        });
        findViewById(R.id.R200).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rechargeamount="200";
                t.setText(rechargeamount);
                dot(mail,rechargeamount,choose);
            }
        });
        findViewById(R.id.R300).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rechargeamount="300";
                t.setText(rechargeamount);
                dot(mail,rechargeamount,choose);
            }
        });
    }
    public void dot(String mail,String amount,String choose){
        //http://localhost/ticketapp/ticketCheck.php
        String url = "http://ec2-43-204-215-53.ap-south-1.compute.amazonaws.com/recharge.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        TextView t=findViewById(R.id.textView7);
                        t.setText(response.toString());
                        JSONObject jsonObj = null;

                        try {
                            jsonObj = new JSONObject(response.toString());
                            TextView t1=findViewById(R.id.textView7);
                            t1.setText(jsonObj.toString());

                            Intent i=new Intent(recharge2.this,HomeActivity.class);
                            i.putExtra("day",day);
                            startActivity(i);
                            finish();
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
                        Toast.makeText(recharge2.this, "ERROR CONNECTING", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(recharge2.this,HomeActivity.class);
                        i.putExtra("day",day);
                        startActivity(i);
                        finish();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("param1", mail);
                params.put("param2", amount);
                params.put("param3",choose);
                return params;
            }
        };
        try{
            RequestQueue requestQueue= Volley.newRequestQueue(recharge2.this);
            requestQueue.add(stringRequest);}
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
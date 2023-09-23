package com.example.admin_fs;

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

public class redeem extends AppCompatActivity {
    String reduce;
    String chooser="redeem";
    String day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem);

        Intent intent=getIntent();
        String mail=intent.getStringExtra("mail");
        day=intent.getStringExtra("day");

        if(!mail.equals("error")){
            TextView name = findViewById(R.id.NAMEr);
            name.setText(intent.getStringExtra("name"));
            TextView roll = findViewById(R.id.ROLLNOr);
            roll.setText(intent.getStringExtra("rollno"));
            TextView college = findViewById(R.id.COLLEGEr);
            college.setText(intent.getStringExtra("college"));
            TextView amount = findViewById(R.id.AMOUNTr);
            amount.setText(intent.getStringExtra("amount"));
            TextView points = findViewById(R.id.POINTSr);
            points.setText(intent.getStringExtra("points"));
        }
        else{
            TextView name = findViewById(R.id.NAMEr);
            name.setText("NOT REGISTERED");
            TextView roll = findViewById(R.id.ROLLNOr);
            roll.setText("NOT REGISTERED");
            TextView college = findViewById(R.id.COLLEGEr);
            college.setText("NOT REGISTERED");
            TextView amount = findViewById(R.id.AMOUNTr);
            amount.setText("NOT REGISTERED");
            TextView points = findViewById(R.id.POINTSr);
            points.setText("NOT REGISTERED");
        }

        int points=Integer.valueOf(intent.getStringExtra("points"));
        Button rbtn = findViewById(R.id.REDEEM);
        TextView rtxt = findViewById(R.id.redeempoint);
        if(points < 100){
            rtxt.setText("Cannot Redeem");
            rbtn.setEnabled(false);
        }
        else if(points>=100 && points<170){
            rtxt.setText("100");
            reduce="100";
            rbtn.setEnabled(true);
        }
        else if(points>=170 && points<300){
            rtxt.setText("170");
            reduce ="170";
            rbtn.setEnabled(true);
        }
        else if(points>=300 && points<500){
            rtxt.setText("300");
            reduce="300";
            rbtn.setEnabled(true);
        }
        else{
            rtxt.setText("500");
            reduce="500";
            rbtn.setEnabled(true);
        }
        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dot(mail,reduce,chooser);
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
                        Toast.makeText(redeem.this, "success", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(redeem.this,HomeActivity.class);
                        i.putExtra("day",day);
                        startActivity(i);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(redeem.this, "ERROR CONNECTING", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(redeem.this,HomeActivity.class);
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
            RequestQueue requestQueue= Volley.newRequestQueue(redeem.this);
            requestQueue.add(stringRequest);}
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
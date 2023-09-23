package com.example.volunteer_fs;

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

public class play extends AppCompatActivity {
    String btn;
    String day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Intent intent = getIntent();
        String mail=intent.getStringExtra("mail");
        day = intent.getStringExtra("day");
        TextView name = findViewById(R.id.NAME);
        name.setText(intent.getStringExtra("name"));
        TextView roll = findViewById(R.id.ROLLNO);
        roll.setText(intent.getStringExtra("rollno"));
        TextView college = findViewById(R.id.COLLEGE);
        college.setText(intent.getStringExtra("college"));
        TextView amount = findViewById(R.id.AMOUNT);
        amount.setText(intent.getStringExtra("amount"));
        TextView points = findViewById(R.id.POINTS);
        points.setText(intent.getStringExtra("points"));
        btn=intent.getStringExtra("game");
        TextView tt=findViewById(R.id.textView7);
        tt.setText(amount.toString());
        if(Integer.parseInt(intent.getStringExtra("amount"))<10){
            findViewById(R.id.PLAY).setEnabled(false);
        }
        if(Integer.parseInt(intent.getStringExtra("amount"))<50 && btn.equals("btnGAH")) findViewById(R.id.PLAY).setEnabled(false);
        if(Integer.parseInt(intent.getStringExtra("amount"))<25 && (btn.equals("btnGAH")||btn.equals("btnTIC"))) findViewById(R.id.PLAY).setEnabled(false);
        else{
            String eamount;
            if(btn.equals("btnGAH")) eamount="50";
            else if(btn.equals("btnTIC")) eamount="25";
            else eamount="10";
        findViewById(R.id.PLAY).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dot(mail,eamount,"eventamount");
                Intent intent=new Intent(play.this,playpoints.class);
                intent.putExtra("mail",mail);
                intent.putExtra("day",day);
                intent.putExtra("btn",btn);
                startActivity(intent);
            }
        });}
    }
    public void dot(String mail,String eamount,String chooset){
        //Intent h2r1 =new Intent(.this,recharge1.class);
        //http://localhost/ticketapp/ticketCheck.php
        String url = "http://ec2-43-204-215-53.ap-south-1.compute.amazonaws.com/recharge.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObj = null;

                        try {
                            jsonObj = new JSONObject(response.toString());
                            /*TextView t1=findViewById(R.id.textView7);
                            t1.setText(jsonObj.toString());*/


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

                        Toast.makeText(play.this, "ERROR CONNECTING", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("param1", mail);
                params.put("param2", eamount);
                params.put("param3",chooset);
                return params;
            }
        };
        try{
            RequestQueue requestQueue= Volley.newRequestQueue(play.this);
            requestQueue.add(stringRequest);}
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
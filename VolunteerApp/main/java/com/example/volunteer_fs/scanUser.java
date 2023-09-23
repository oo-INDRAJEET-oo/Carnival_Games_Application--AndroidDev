package com.example.volunteer_fs;

import androidx.activity.result.ActivityResultLauncher;
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
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class scanUser extends AppCompatActivity {
    String day;
    String btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_user);
        Intent intent=getIntent();
        day=intent.getStringExtra("day");
        btn=intent.getStringExtra("btn");
        String btname=intent.getStringExtra("btname");
        TextView tt=findViewById(R.id.EVENT);
        tt.setText(btname);
        Button scan=findViewById(R.id.SCAN);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });
    }
    private void scanCode(){

        ScanOptions options =new ScanOptions();
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barLauncher=registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents()!=null){
            //static/tickets/200301047@rajalakshmi.edu.inday14cc4DYe2.png
            String ticket = "static/tickets/"+result.getContents()+".png";
            dot(ticket,day,"redeem");

        }
    });

    public void dot(String tickets,String dayo,String chooset){
        //Intent h2r1 =new Intent(.this,recharge1.class);
        //http://localhost/ticketapp/ticketCheck.php
        String url = "http://ec2-43-204-215-53.ap-south-1.compute.amazonaws.com/register.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObj = null;

                        try {
                            jsonObj = new JSONObject(response.toString());
                            if(jsonObj.getString("Name").equals("error1")) {
                                Toast.makeText(scanUser.this, "INVALID TICKET", Toast.LENGTH_LONG).show();
                            }
                            else if(jsonObj.getString("Name").equals("errorredeem")){
                                Toast.makeText(scanUser.this, "REDEEM ERROR", Toast.LENGTH_LONG).show();

                            }
                            else{
                                //Toast.makeText(HomeActivity.this, "INVALID TICKET", Toast.LENGTH_LONG).show();
                                if(chooset.equals("recharge") || chooset.equals("register")){
                                    Toast.makeText(scanUser.this, "MAGIC HOW??????", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Intent s2p = new Intent(scanUser.this,play.class);
                                    s2p.putExtra("mail",jsonObj.getString("Mail").toString());
                                    s2p.putExtra("name",jsonObj.getString("Name").toString());
                                    s2p.putExtra("rollno",jsonObj.getString("RollNo").toString());
                                    s2p.putExtra("college",jsonObj.getString("CollegeName").toString());
                                    s2p.putExtra("amount",jsonObj.getString("Amount").toString());
                                    s2p.putExtra("points",jsonObj.getString("RedeemPoints").toString());
                                    s2p.putExtra("game",btn);
                                    s2p.putExtra("day",day);
                                    s2p.putExtra("btn",btn);
                                    startActivity(s2p);
                                }
                            }

                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(scanUser.this, "ERROR CONNECTING", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("param1", dayo);
                params.put("param2", tickets);
                params.put("param3",chooset);
                return params;
            }
        };
        try{
            RequestQueue requestQueue= Volley.newRequestQueue(scanUser.this);
            requestQueue.add(stringRequest);}
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
package com.example.admin_fs;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    String choose;
    String dayof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView ttt=findViewById(R.id.textView9);
        ttt.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        dayof = intent.getStringExtra("day");
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose = "register";
                scanCode();
            }
        });
        findViewById(R.id.recharge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose = "recharge";
                scanCode();
            }
        });
        findViewById(R.id.redeem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choose = "redeem";
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
            dot(ticket,dayof,choose);

        }
    });

    public void dot(String tickets,String dayo,String chooset){
        Intent h2r1 =new Intent(HomeActivity.this,recharge1.class);
        //http://localhost/ticketapp/ticketCheck.php
        String url = "http://ec2-43-204-215-53.ap-south-1.compute.amazonaws.com/register.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        TextView ttt=findViewById(R.id.textView9);
                        ttt.setTextColor(Color.parseColor("#0000FF"));
                        ttt.setVisibility(View.VISIBLE);
                        ttt.setText("working.....");
                        JSONObject jsonObj = null;

                        try {
                            jsonObj = new JSONObject(response.toString());
                            if(jsonObj.getString("Name").equals("error1")) {
                                Toast.makeText(HomeActivity.this, "INVALID TICKET", Toast.LENGTH_LONG).show();
                                ttt.setTextColor(Color.parseColor("#ED4337"));
                                ttt.setVisibility(View.VISIBLE);
                                ttt.setText("INVALID TICKET");
                            }
                            else if(jsonObj.getString("Name").equals("errorredeem")){
                                Toast.makeText(HomeActivity.this, "REDEEM ERROR", Toast.LENGTH_LONG).show();
                                ttt.setTextColor(Color.parseColor("#ED4337"));
                                ttt.setVisibility(View.VISIBLE);
                                ttt.setText("NOT REGISTERED");
                                /*Intent h2r = new Intent(HomeActivity.this,redeem.class);
                                h2r.putExtra("mail","error");
                                startActivity(h2r);*/
                            }
                            else{
                                //Toast.makeText(HomeActivity.this, "INVALID TICKET", Toast.LENGTH_LONG).show();
                                if(chooset.equals("recharge") || chooset.equals("register")){
                                h2r1.putExtra("mail",jsonObj.getString("Mail").toString());
                                h2r1.putExtra("name",jsonObj.getString("Name").toString());
                                h2r1.putExtra("rollno",jsonObj.getString("RollNo").toString());
                                h2r1.putExtra("college",jsonObj.getString("CollegeName").toString());
                                h2r1.putExtra("amount",jsonObj.getString("Amount").toString());
                                h2r1.putExtra("points",jsonObj.getString("Points").toString());
                                h2r1.putExtra("day",dayof);
                                startActivity(h2r1);ttt.setVisibility(View.INVISIBLE);
                                }
                                else{
                                    Intent h2r = new Intent(HomeActivity.this,redeem.class);
                                    h2r.putExtra("mail",jsonObj.getString("Mail").toString());
                                    h2r.putExtra("name",jsonObj.getString("Name").toString());
                                    h2r.putExtra("rollno",jsonObj.getString("RollNo").toString());
                                    h2r.putExtra("college",jsonObj.getString("CollegeName").toString());
                                    h2r.putExtra("amount",jsonObj.getString("Amount").toString());
                                    h2r.putExtra("points",jsonObj.getString("RedeemPoints").toString());
                                    h2r.putExtra("day",dayof);
                                    startActivity(h2r);ttt.setVisibility(View.INVISIBLE);
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
                        TextView ttt=findViewById(R.id.textView9);
                        ttt.setText("ERRORORORORORO");
                        Toast.makeText(HomeActivity.this, "ERROR CONNECTING", Toast.LENGTH_LONG).show();
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
            RequestQueue requestQueue= Volley.newRequestQueue(HomeActivity.this);
            requestQueue.add(stringRequest);}
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
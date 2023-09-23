package com.example.admin_fs;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

public class MainActivity extends AppCompatActivity {
    String day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent m2h= new Intent(MainActivity.this, HomeActivity.class);

        findViewById(R.id.day2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="day2";
                m2h.putExtra("day",day);
                startActivity(m2h);
            }
        });
        findViewById(R.id.day3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="day3";
                m2h.putExtra("day",day);
                startActivity(m2h);
            }
        });

    }

}
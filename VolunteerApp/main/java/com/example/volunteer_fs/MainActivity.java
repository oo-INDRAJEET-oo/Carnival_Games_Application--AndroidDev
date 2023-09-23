package com.example.volunteer_fs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    String day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button brn = findViewById(R.id.DAY2);
        brn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, eventlist.class);
                day="day2";
                intent.putExtra("day",day);
                startActivity(intent);
            }
        });
        Button brn2 = findViewById(R.id.DAY3);
        brn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, eventlist.class);
                day="day3";
                intent.putExtra("day",day);
                startActivity(intent);
            }
        });
    }

}
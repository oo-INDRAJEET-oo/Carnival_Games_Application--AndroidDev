package com.example.volunteer_fs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class eventlist extends AppCompatActivity {
    String day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventlist);

        Intent intent=getIntent();
        day= intent.getStringExtra("day");

    }

    public void doit(View view){
        Intent e2s = new Intent(eventlist.this,scanUser.class);
        e2s.putExtra("day",day);
        e2s.putExtra("btn",getResources().getResourceEntryName(view.getId()));
        e2s.putExtra("btname",((Button)view).getText().toString());
        startActivity(e2s);
        //Toast.makeText(eventlist.this, "d", Toast.LENGTH_LONG).show();
    }

}
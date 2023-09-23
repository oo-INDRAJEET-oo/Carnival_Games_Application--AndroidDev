package com.example.admin_fs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class recharge1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge1);

        Intent intent = getIntent();
        String mail=intent.getStringExtra("mail");
        String dayof = intent.getStringExtra("day");
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
        findViewById(R.id.RECHARGE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent r12r2 = new Intent(recharge1.this,recharge2.class);
                r12r2.putExtra("mail",mail);
                r12r2.putExtra("day",dayof);
                startActivity(r12r2);
            }
        });

    }
}
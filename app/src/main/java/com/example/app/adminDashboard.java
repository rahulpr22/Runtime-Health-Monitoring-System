package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminDashboard extends AppCompatActivity {
    Button add, remove, update, view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        add= findViewById(R.id.addPolicy);
        update= findViewById(R.id.updatePolicy);
        remove= findViewById(R.id.removePolicy);
        view= findViewById(R.id.viewPolicies);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(adminDashboard.this, Admin.class);
                startActivity(i);
            }
        });
    }
}
package com.example.btp_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Admin extends AppCompatActivity {
    TextView policy;
    String pol;
    Button inputPolicybtn,polsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        policy = findViewById(R.id.policy);
        inputPolicybtn=findViewById(R.id.inputPolicybtn);
        polsubmit=findViewById(R.id.inputPolicysubmitbtn);

        polsubmit.setVisibility(View.GONE);
        policy.setVisibility(View.GONE);

        inputPolicybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                policy.setVisibility(View.VISIBLE);
                polsubmit.setVisibility(View.VISIBLE);

            }
        });

        polsubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                InputStream dsl = getdsl(v);
                pol= policy.getText().toString();
                System.out.println(pol);
                InputStream stream = new ByteArrayInputStream(pol.getBytes(StandardCharsets.UTF_8));
                if(Parser.verifyProperty(dsl,stream))
                    Toast.makeText(Admin.this,"Property Submitted Successfully!!",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Admin.this,"Error, Invalid syntax!!",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public InputStream getdsl(View view){
        InputStream dsl = this.getResources().openRawResource(R.raw.dsl);
        return dsl;
    }
}
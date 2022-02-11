package com.example.btp_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private  DatabaseReference reference;
    private ProgressBar progressBar;

    EditText uname,pwd;
    TextView login,newUser;
    TextView forgotPasswd;
    boolean check=false;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        //progressBar=findViewById(R.id.progressBar);
        login=findViewById(R.id.loginbtn);
        newUser=findViewById(R.id.signupbtn);
        uname=findViewById(R.id.loginEmail);
        pwd=findViewById(R.id.Loginpwd);
        forgotPasswd=findViewById(R.id.forgotpwd);
        //progressBar.setVisibility(View.GONE);
    uname.setText("test@test.in");
    pwd.setText("test@pwd");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //progressBar.setVisibility(View.VISIBLE);
                        //progressBar.setTag("Please Wait...");
                        userLogin();
                        //progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainActivity.this,Registration.class);
                startActivity(i);
                finish();
            }
        });
        forgotPasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword(uname.getText().toString());
            }
        });
    }

    private void resetPassword(String email) {
        /*mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, OnCompleteListener{ task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Reset link sent to your email", Toast.LENGTH_LONG)
                        .show()
            } else {
                Toast.makeText(this, "Unable to send reset mail", Toast.LENGTH_LONG)
                        .show()
            }
        })*/
    }


    private void userLogin(){

        String email = uname.getText().toString().trim();
        String password  = pwd.getText().toString().trim();

        if(email.equals("admin@test.com") )
        {
            Intent intent = new Intent(MainActivity.this, adminDashboard.class);
            startActivity(intent);
        }


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                            //progressBar.setVisibility(View.GONE);

                            Intent intent = new Intent(MainActivity.this, dashboard.class);
                            intent.putExtra("Email",email);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
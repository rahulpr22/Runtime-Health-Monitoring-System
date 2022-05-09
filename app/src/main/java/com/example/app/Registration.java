package com.example.app;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    EditText regName,age,phone,email,pwd;
   // private final String url="https://btp-app-80033-default-rtdb.firebaseio.com/";
//boolean check=false;
   private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    private FirebaseAuth.AuthStateListener mAuthListener;
    Button nxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        reference = FirebaseDatabase.getInstance().getReference();
        mAuth =FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), monitorActivity.class));
        }
        regName=findViewById(R.id.PatientName);
        age=findViewById(R.id.dob);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        pwd = findViewById(R.id.Password);
        nxt=findViewById(R.id.signup);
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


    }
    private void registerUser(){

        //getting email and password from edit texts
        String email1 = email.getText().toString();
        String name = regName.getText().toString();
        String dob= age.getText().toString();
        String pwd1 = pwd.getText().toString();
        String phone1 = phone.getText().toString();
        user helper = new user(name, dob,phone1, email1, pwd1);

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email1)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(pwd1)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog



        //creating a new user
        mAuth.createUserWithEmailAndPassword(email1, pwd1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            reference.child("users").child(email1.replace(".",",")).setValue(helper);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();

                        }else{
                            //display some message here
                            Toast.makeText(Registration.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(Registration.this,MainActivity.class);
        startActivity(i);
        finish();
    }

}
class user{
    String name,phone,email,pwd,age;

    public user(String name, String age,String phone, String email, String pwd) {
        this.name = name;
        this.age=age;
        this.phone = phone;
        this.email = email;
        this.pwd = pwd;
    }



    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
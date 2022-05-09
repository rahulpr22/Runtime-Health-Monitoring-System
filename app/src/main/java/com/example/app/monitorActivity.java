package com.example.app;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class monitorActivity extends AppCompatActivity {
    TextView hr,bp,bo2,temp;
    boolean check=false;
    Button enableSensor,exit;
    Thread thread;
    JSONObject obj;
    ArrayList<Result> resultJson = new ArrayList<>();


    String result="";
    public InputStream getdsl(View view){
        InputStream dsl = this.getResources().openRawResource(R.raw.dsl);
        return dsl;
    }
    public InputStream gettest(View view){
        InputStream dsl = this.getResources().openRawResource(R.raw.test);
        return dsl;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        Intent i = getIntent();
        String email = i.getStringExtra("Email");
        hr= findViewById(R.id.hrValue);
        bp= findViewById(R.id.bpValue);
        temp= findViewById(R.id.tempValue);
        bo2= findViewById(R.id.bo2Value);
        enableSensor= findViewById(R.id.enblSensor);
        exit= findViewById(R.id.exitbtn);
        enableSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(monitorActivity.this,"Sensors Enabled !!",Toast.LENGTH_SHORT).show();
                thread = new Thread() {

                    @Override
                    public void run() {
                        try {
                            while (!isInterrupted() && !check) {
                                Thread.sleep(2000);
                                runOnUiThread(new Runnable() {
                                    @RequiresApi(api = Build.VERSION_CODES.O)
                                    @Override
                                    public void run() {
                                        Result rs=new Result();
                                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                                        LocalDateTime now = LocalDateTime.now();
                                        String ts= dtf.format(now);
                                        JSONObject jo =new JSONObject();
                                        try {
                                            jo.put("heartRate",generateRandomValues("hr"));
                                            jo.put("bloodPressure",generateRandomValues("bp"));
                                            jo.put("Temperature",generateRandomValues("temp"));
                                            jo.put("bloodOxygen",generateRandomValues("spo2"));

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        EventGenerator evg = new EventGenerator();
                                        obj= evg.generateEvent(jo);
                                        propertyVerifier verifier= new propertyVerifier();
                                        try {
                                            rs.setTime(ts);
                                            rs.setBloodPressure("Blood Pressure : "+jo.getString("bloodPressure"));
                                            rs.setHeartRate("Heart rate : "+jo.getString("heartRate"));
                                            rs.setTemperature("Temperature (F) :"+jo.getString("Temperature"));
                                            rs.setSpo2("Spo2 : "+jo.getString("bloodOxygen"));
                                            String res=verifier.verifyProperty(obj,getdsl(v),gettest(v)).split("::")[0];
                                            String gur = verifier.verifyProperty(obj,getdsl(v),gettest(v)).split("::")[1];
                                            rs.setResult(res);
                                            rs.setGuards(gur);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        resultJson.add(rs);
                                    }
                                });
                            }
                        } catch (InterruptedException e) {

                        }
                    }
                };

                thread.start();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check=true;
                Intent i = new Intent(monitorActivity.this,dashboard.class);
                i.putExtra("map",propertyVerifier.getGaurd());
                i.putExtra("email",email);
                i.putExtra("output", (ArrayList<Result>) resultJson);
                startActivity(i);
                finish();
            }
        });


    }
    public String generateRandomValues(String event) throws JSONException {
        String res ="";
        switch (event){
            case "bp":
                int bpsys = getRandomNumberUsingInts(90,140);
                int bpdia= getRandomNumberUsingInts(60,130);
                res= String.valueOf(bpsys)+"/"+String.valueOf(bpdia);
                bp.setText(res);

                break;
            case "hr":
                int hrval = getRandomNumberUsingInts(40,120);
                hr.setText(String.valueOf(hrval));
                res= String.valueOf(hrval);
                break;
            case "temp":
                int temperature= getRandomNumberUsingInts(95,104);
                temp.setText(String.valueOf(temperature));
                res = String.valueOf(temperature);
                break;

            case "spo2":
                int bo= getRandomNumberUsingInts(80,110);
                bo2.setText(String.valueOf(bo));
                res = String.valueOf(bo);
                break;

        }
        return res;
    }


    public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return random.ints(min, max)
                    .findFirst()
                    .getAsInt();
        }
        return 0;
    }
}
package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
@SuppressWarnings({"unchecked","unsafe"})
public class Report extends AppCompatActivity {
    private static final String TAG = "reached report";
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<Result> output = (ArrayList<Result>) getIntent().getSerializableExtra("output");
        System.out.println();
        Log.d(TAG, "onCreate: "+output.size());
        reference = FirebaseDatabase.getInstance().getReference();
        for(Result rs : output){
            reference.child("data").child(getIntent().getStringExtra("email").replace(".",",")).child(rs.getTime()).setValue(rs);
        }
        ReportAdapter adapter = new ReportAdapter(this,R.layout.list_view_row_layout,output);
        listView.setAdapter(adapter);


    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(Report.this,monitorActivity.class);
        startActivity(i);
        finish();
    }
}

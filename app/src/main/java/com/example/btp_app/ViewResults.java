package com.example.btp_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewResults extends AppCompatActivity {

    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_results);

        title= findViewById(R.id.Results);
        title.setText("Reports");
        title.setVisibility(View.VISIBLE);
        ListView listView = (ListView) findViewById(R.id.listView1);
        ArrayList<Results> results= new ArrayList<>();
        results.add(new Results("13 November 2021 13:50"));
        results.add(new Results("14 November 2021 23:15"));
        results.add(new Results("16 November 2021 10:20"));
        results.add(new Results("18 November 2021 15:30"));
        results.add(new Results("10 December 2021 12:30"));
        results.add(new Results("12 December 2021 18:45"));
        results.add(new Results("15 December 2021 20:10"));

        ResultAdapter rs= new ResultAdapter(this,R.layout.listitem,results);
        listView.setAdapter(rs);

    }
}
class Results{
    String dateTime;

    public Results(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
class ResultAdapter extends ArrayAdapter<Results>{
    private Context mcontext;
    int mresource;
    int lastPosition=-1;
    public ResultAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Results> objects) {
        super(context, R.layout.listitem, objects);
        this.mcontext = context;
        this.mresource =resource;
    }

    private static class ViewHolder{
        TextView time;
        ImageView image;
        TextView view;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String time=getItem(position).getDateTime();


        final View res;
        ResultAdapter.ViewHolder holder;
        if(convertView==null)
        {
            LayoutInflater inflater = LayoutInflater.from(mcontext);
            convertView = inflater.inflate(R.layout.listitem,parent,false);
            holder = new ResultAdapter.ViewHolder();
            holder.time=(TextView)convertView.findViewById(R.id.dateofresult);
            holder.image=(ImageView) convertView.findViewById(R.id.icon1);
            holder.view= (TextView) convertView.findViewById(R.id.viewResults);
            /*holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //displayPopupWindow(v,grd);
                }
            });*/


            holder.time.setText(time);

            res=convertView;
            convertView.setTag(holder);
        }
        else{
            holder = (ResultAdapter.ViewHolder) convertView.getTag();
            res=convertView;
        }


        return res;
    }
}
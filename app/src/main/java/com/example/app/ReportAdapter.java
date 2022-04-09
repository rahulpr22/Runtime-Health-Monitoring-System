package com.example.btp_app;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReportAdapter extends ArrayAdapter<Result> {
    private static final String Tag ="ReportAdapter";
    private Context mcontext;
    int mresource;
    int lastPosition=-1;
    PopupWindow pop;
    boolean click = true;

    private static class ViewHolder{
        TextView time;
        TextView result;
        TextView hr;
        TextView bp;
        TextView spo2;
        TextView temp;
        ImageView image;
        Button guards;
    }
    public ReportAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Result> objects) {
        super(context, R.layout.list_view_row_layout, objects);
        this.mcontext = context;
        this.mresource =resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String time = getItem(position).getTime();
        String result =getItem(position).getResult();
        String heartRate =getItem(position).getHeartRate();
        String temperature=getItem(position).getTemperature();
        String bloodPressure=getItem(position).getBloodPressure();
        String spo2=getItem(position).getSpo2();
        String grd = getItem(position).getGuards();

        final View res;
        ViewHolder holder;
        if(convertView==null)
        {
            LayoutInflater inflater = LayoutInflater.from(mcontext);
            convertView = inflater.inflate(R.layout.list_view_row_layout,parent,false);
            holder = new ViewHolder();
            holder.bp=(TextView)convertView.findViewById(R.id.bptv);
            holder.time =(TextView) convertView.findViewById(R.id.timestamp);
            holder.result =(TextView) convertView.findViewById(R.id.result);
            holder.hr =(TextView) convertView.findViewById(R.id.hrtv);
            holder.temp =(TextView) convertView.findViewById(R.id.temptv);
            holder.spo2 =(TextView) convertView.findViewById(R.id.spo2tv);
            holder.image=(ImageView) convertView.findViewById(R.id.icon);
            holder.guards= (Button) convertView.findViewById(R.id.gbtn);
            holder.guards.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayPopupWindow(v,grd);
                }
            });

            holder.spo2.setText(spo2);
            holder.temp.setText(temperature);
            holder.result.setText(result);
            holder.bp.setText(bloodPressure);
            holder.time.setText(time);
            holder.hr.setText(heartRate);

            res=convertView;
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            res=convertView;
        }


        return res;
    }

    private void displayPopupWindow(View v,String text) {
        TextView tv = null;
        PopupWindow popup = new PopupWindow(mcontext);
        LayoutInflater li=(LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = li.inflate(R.layout.pop_up_layout, null);
        popup.setContentView(layout);
       // tv=layout.findViewById(R.id.tvCaption);
        tv.setText(text);
        // Set content width and height
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        // Show anchored to button
        popup.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        popup.showAsDropDown(v);
    }
}

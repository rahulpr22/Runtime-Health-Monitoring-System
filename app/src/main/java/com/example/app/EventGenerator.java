package com.example.app;

import org.json.JSONException;
import org.json.JSONObject;

public class EventGenerator {
    public JSONObject generateEvent(JSONObject obj){
        JSONObject jsonObject= new JSONObject();
        try {
            int temp = Integer.parseInt(obj.get("Temperature").toString());
            int bo2 = Integer.parseInt(obj.get("bloodOxygen").toString());
            int hrate = Integer.parseInt(obj.get("heartRate").toString());
            String[] bp = obj.get("bloodPressure").toString().split("/");
            int bpsys = Integer.parseInt(bp[0]);
            int bpdia = Integer.parseInt(bp[1]);
            jsonObject.put("TemperatureEvent",getTemperatureEvent(temp));
            jsonObject.put("Spo2Event",getSpo2Event(bo2));
            jsonObject.put("HeartRateEvent",getHeartRateEvent(hrate));
            jsonObject.put("BloodPressureEvent",getBloodPressureEvent(bpsys,bpdia));


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return jsonObject;
    }
    public String getTemperatureEvent(int temp){
        String event="";
        if(temp<95)
            event = "Low";
        else if (temp>=95 && temp<100)
            event= "Normal";
        else
            event = "High";

        return event;
    }

    public String getHeartRateEvent(int hr){
        if(hr<65)
            return "Low";
        else if(hr>=65 && hr<90)
            return "Normal";
        else
            return  "High";
    }

    public String getSpo2Event(int bo2)
    {
        if(bo2<95)
            return "Low";
        else if(bo2>=95 && bo2<=100 )
            return "Normal";
        else
            return "High";
    }

    public String getBloodPressureEvent(int sys,int dia){
        boolean lbp=false,hbp=false;
        String systole="";
        String diastole="";
        if(sys<90)
            systole="Low";
        else if (sys>=90 && sys<=110)
            systole="Normal";
        else
            systole="High";

        if( dia<60)
            diastole="Low";
        else if (dia>=60 && dia <=80)
            diastole= "Normal";
        else
            diastole= "High";

        return systole+" / "+diastole;

    }
}
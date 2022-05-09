package com.example.app;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class propertyVerifier {
    static boolean flag= false;
    public static String getGaurd() {
        return gaurd;
    }

    public static void setGaurd(String gaurd) {
        propertyVerifier.gaurd = gaurd;
    }

    static String gaurd;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String verifyProperty(JSONObject obj, InputStream dsl, InputStream test) throws JSONException {
        String Temperature = obj.getString("TemperatureEvent");
        String Spo2 = obj.getString("Spo2Event");
        String HeartRate = obj.getString("HeartRateEvent");
        String BP= obj.getString("BloodPressureEvent");
        String[] bp= BP.split("/");
        String systole=bp[0];
        String diastole=bp[1];
        flags f = temp(HeartRate,systole,diastole,Temperature);
        String property="";
        //if(f.getFlag().equals("true"))
            //property= "( HR , "+HeartRate.toUpperCase()+" ) TIMEGAP > 5 ( BP , " + BP.toUpperCase() +  " ) TIMEGAP > 10 ( TEMP , "+Temperature.toUpperCase()+" ) := UNSAFE";
       // else
           // property= "( HR , "+HeartRate.toUpperCase()+" ) TIMEGAP > 5 ( BP , " + BP.toUpperCase() +  " ) TIMEGAP > 10 ( TEMP , "+Temperature.toUpperCase()+" ) := SAFE";


        //boolean gurd= Parser.verifyProperty(dsl,test);

        return f.getRes()+" :: success";
    }
    class flags{
        String res;
        String flag;

        public flags(String res, String flag) {
            this.res = res;
            this.flag = flag;
        }

        public String getRes() {
            return res;
        }

        public void setRes(String res) {
            this.res = res;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
    }
    public  flags temp(String HeartRate, String systole,String diastole,String Temperature)
    {
        String res="";
        if( HeartRate.equals("Low") && systole.equals("Low") && diastole.equals("High")) {
            flag=true;
            res= "Warning for Bradycardia";
        }
        else if(HeartRate.equals("Low") && systole.equals("Low") && diastole.equals("Low") && Temperature.equals("Low"))
        { flag=true;
            res= "Warning for Hypothermia";
        }
        else if(HeartRate.equals("Low") && systole.equals("Low") && diastole.equals("Low"))
        {
            flag=true;
            res= "Warning for Hypotension";
        }
        else if(HeartRate.equals("High") && systole.equals("Low") && diastole.equals("High"))
        {
            flag=true;
            res= "Warning for Tachycardia";
        }
        else if(HeartRate.equals("High") && systole.equals("Low") && diastole.equals("High") && Temperature.equals("High"))
        {
            flag=true;
            res= "Warning for fever";
        }
        else if(HeartRate.equals("High") && systole.equals("High") && diastole.equals("High"))
        {
            flag=true;
            res= "Warning for Hypertension";
        }
        else if(HeartRate.equals("Low") && systole.equals("High") && diastole.equals("High"))
        {
            flag=true;
            res= "Warning for Hypotension";
        }
        else if (HeartRate.equals("High"))
        {
            flag=true;
            res= "Warning for Trachycardia";
        }
        else if(Temperature.equals("High")){
            flag=true;
            res= "Warning for fever";
        }
        else
        {
            flag=false;
            res= "Normal";
        }
        flags f= new flags(res,String.valueOf(flag));
        return f;
    }
}

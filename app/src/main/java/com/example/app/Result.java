package com.example.btp_app;

import org.json.JSONObject;

import java.io.Serializable;

public class Result implements Serializable {
    private String heartRate,temperature,bloodPressure,spo2,guards;

    public Result(){

    };
    public Result(String heartRate, String temperature, String bloodPressure, String spo2, String time, String result,String guards) {
        this.heartRate = heartRate;
        this.temperature = temperature;
        this.bloodPressure = bloodPressure;
        this.spo2 = spo2;
        this.time = time;
        this.result = result;
        this.guards=guards;
    }

    public String getGuards() {
        return guards;
    }

    public void setGuards(String guards) {
        this.guards = guards;
    }

    private String time;
    private String result;

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getSpo2() {
        return spo2;
    }

    public void setSpo2(String spo2) {
        this.spo2 = spo2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

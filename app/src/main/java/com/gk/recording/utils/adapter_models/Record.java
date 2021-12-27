package com.gk.recording.utils.adapter_models;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Record {
    private int id;
    private String name;
    private long timeDuration;
    private String recordType;


    public Record(int id, String name, long timeDuration, String recordType) {
        this.id = id;
        this.name = name;
        this.timeDuration = timeDuration;
        this.recordType = recordType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(long timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String idToString(){
        try {
            return "" + this.id + "";
        } catch (Exception e){
            Log.e("numberToString","FAIL: "+e.toString());
        }
        return "";
    }

    public String timeDurationToString(){
        @SuppressLint("SimpleDateFormat") DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
        Date result = new Date(this.timeDuration);
        return result.toString();
    }

}

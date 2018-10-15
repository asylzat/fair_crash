package com.example.asylzat.fair_crash;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Presentation  implements Serializable {
    private String title;
    private int  time;
    private String begin;
    private String end;
    private String name;

    public Presentation(){}

    public Presentation(String title, int time, String begin, String end, String name){
        this.title = title;
        this.time = time;
        this.begin = begin;
        this.end = end;
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(String time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.time = calendar.get(Calendar.MINUTE);



    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
package com.example.asylzat.fair_crash;

import android.media.Image;
import android.provider.MediaStore;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Company implements Serializable {
    private String logo;
    private String name;
    private String info;
    private String link;
    private int duration;

    public Company(){}


    public Company(String logo, String name, String info,  String link, int duration ){
        this.logo = logo;
        this.name = name;
        this.info = info;
        this.link = link;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(duration));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.duration = calendar.get(Calendar.MINUTE);



    }
}

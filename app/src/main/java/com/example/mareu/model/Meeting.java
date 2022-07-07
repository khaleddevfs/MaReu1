package com.example.mareu.model;


public class Meeting {

    private int color;

    private String name;

    private String time;

    private String place;

    private String date;

    private String members;

    public Meeting(int color, String name, String time, String place, String date, String members) {
        this.color = color;
        this.name = name;
        this.time = time;
        this.place = place;
        this.date = date;
        this.members = members;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

}
package com.pubnub.braindrain.app.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Point {

    private long timeStamp;
    private int x;
    private int y;
    private String colorValue;

    public long getTimeStamp() {
        return timeStamp;
    }

    public Point(int x, int y){
        this.x = x;
        this.y = y;
        this.timeStamp = System.currentTimeMillis();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getColorValue() {
        return colorValue;
    }

    public void setColorValue(String colorValue) {
        this.colorValue = colorValue;
    }
}

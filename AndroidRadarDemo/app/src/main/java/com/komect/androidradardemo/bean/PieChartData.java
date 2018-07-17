package com.komect.androidradardemo.bean;

/**
 * Created by xieyusheng on 2018/6/1.
 */

public class PieChartData {

    private String name; // 名字
    private float value; // 值
    private int color; //颜色

    private float angle; // 角度
    private float percentage; // 百分比

    public PieChartData(String name, float value, int color) {
        this.name = name;
        this.value = value;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}

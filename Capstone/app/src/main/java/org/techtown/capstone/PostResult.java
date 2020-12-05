package org.techtown.capstone;

import com.google.gson.annotations.SerializedName;

public class PostResult {
    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("nowWeather")
    private int nowWeather;

    @SerializedName("rain")
    private double rain;

    @SerializedName("nowroad")
    private int nowroad;

    @SerializedName("videoUrl")
    private String videoUrl;

    @Override
    public String toString() {
        return "PostResult{" + "latitude=" + latitude + '\'' + "longitude=" + longitude + '\'' +
                "nowWeather=" + nowWeather + '\'' +
                "rain=" + rain + '\n' +
                "nowroad=" + nowroad + '\n' +
                "videoUrl=" + videoUrl + '\n' + '}';
    }

    public int getNowWeather(){return nowWeather;}
    public int getNowroad() {return nowroad;}
    public String getVideoUrl() {return videoUrl;}
    public double getRain() {return rain;}
}
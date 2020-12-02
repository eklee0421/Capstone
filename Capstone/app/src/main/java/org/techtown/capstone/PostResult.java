package org.techtown.capstone;

import com.google.gson.annotations.SerializedName;

public class PostResult {
    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("nowWeather")
    private double nowWeather;

    @Override
    public String toString() {
        return "PostResult{" + "latitude=" + latitude + '\'' + "longitude=" + longitude + '\'' + "nowWeather=" + nowWeather + '\'' +'}';
    }
}
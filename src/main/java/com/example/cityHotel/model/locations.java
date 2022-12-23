package com.example.cityHotel.model;

import com.sun.istack.NotNull;

public class locations {
    @NotNull()
    private double latitude;
    @NotNull()
    private double longitude;

    public locations() {
    }

    public locations(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
}

package com.example.cityHotel.utilities;

import org.springframework.stereotype.Component;


public class CalculateDistance {
    private static final double EARTH_RADIUS_IN_METERS = 6371e3; // Earth's radius in meters

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double f = (lat1 + lat2) / 2.0;
        double g = (lat1 - lat2) / 2.0;
        double l = (lon1 - lon2) / 2.0;
        double sinG = Math.sin(g);
        double cosG = Math.cos(g);
        double sinL = Math.sin(l);
        double cosL = Math.cos(l);
        double sinF = Math.sin(f);
        double cosF = Math.cos(f);

        double s = sinG * sinG * cosL * cosL + cosF * cosF * sinL * sinL;
        double c = cosG * cosG * cosL * cosL + sinF * sinF * sinL * sinL;
        double w = Math.atan(Math.sqrt(s / c));
        double r = Math.sqrt(s * c) / w;
        double d = 2 * w * EARTH_RADIUS_IN_METERS;
        double h1 = (3 * r - 1) / (2 * c);
        double h2 = (3 * r + 1) / (2 * s);

        return d * (1 + h1 * sinF * sinF * cosG * cosG - h2 * cosF * cosF * sinG * sinG);
    }

}

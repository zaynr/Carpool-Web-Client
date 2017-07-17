package com.zayn.util;


/**
 * Created by zaynr on 2017/7/13.
 */

public class GetDistanceUtil {
    private LatLng ori, dest;
    private final double EARTH_RADIUS = 6378.137;//地球半径

    public GetDistanceUtil(LatLng ori, LatLng dest){
        this.ori = ori;
        this.dest = dest;
    }

    private static double rad(double d){
        return d * Math.PI / 180.0;
    }

    public double getDistance(){
        double radLat1 = rad(ori.latitude);
        double radLat2 = rad(dest.latitude);
        double a = radLat1 - radLat2;
        double b = rad(ori.longitude) - rad(dest.longitude);

        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10;
        return s;
    }
}

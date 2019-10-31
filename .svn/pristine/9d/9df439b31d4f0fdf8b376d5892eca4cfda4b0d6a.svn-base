package com.soholy.cb.utils;

import java.util.Date;

public class FenceUtils {
    private static double EARTH_RADIUS = 6371.393D;

    private static double rad(double d) {
        return d * 3.141592653589793D / 180.0D;
    }

    public static void main(String[] args) {
        System.out.println(GetDistance(106.486654D, 29.220001D, 106.486654D, 29.490295D));
    }


    public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2.0D * Math.asin(Math.sqrt(
                Math.pow(Math.sin(a / 2.0D), 2.0D) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2.0D), 2.0D)));
        s *= EARTH_RADIUS;
        s = Math.round(s * 1000.0D);
        return s;
    }


    public static Double dateDiff(Date startTime, Date endTime) {
        return dateDiff(startTime, endTime, null);
    }


    public static Double dateDiff(Date startTime, Date endTime, String str) {
        Double nd = Double.valueOf(8.64E7D);
        Double nh = Double.valueOf(3600000.0D);
        Double nm = Double.valueOf(60000.0D);
        Double ns = Double.valueOf(1000.0D);

        Double day = Double.valueOf(0.0D);
        Double hour = Double.valueOf(0.0D);
        Double min = Double.valueOf(0.0D);
        Double sec = Double.valueOf(0.0D);
        try {
            if ((str == null) || (str.equals(""))) {
                str = "";
            }

            Double diff = Double.valueOf(endTime.getTime() - startTime.getTime());
            day = Double.valueOf(diff.doubleValue() / nd.doubleValue());
            hour = Double.valueOf(diff.doubleValue() / nh.doubleValue());
            min = Double.valueOf(diff.doubleValue() / nm.doubleValue());
            sec = Double.valueOf(diff.doubleValue() / ns.doubleValue());

            if (str.equalsIgnoreCase("day"))
                return Double.valueOf(Math.round(day.doubleValue()));
            if (str.equalsIgnoreCase("hour"))
                return Double.valueOf(Math.round(hour.doubleValue()));
            if (str.equalsIgnoreCase("min"))
                return Double.valueOf(Math.round(min.doubleValue()));
            if (str.equalsIgnoreCase("sec")) {
                return Double.valueOf(Math.round(sec.doubleValue()));
            }

            return diff;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}



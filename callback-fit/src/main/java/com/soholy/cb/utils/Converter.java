package com.soholy.cb.utils;

import com.soholy.cb.entity.iot.check.Point;

import java.util.ArrayList;
import java.util.List;


public class Converter {
    private static final double a = 6378245.0D;
    private static final double pi = 3.141592653589793D;
    private static final double ee = 0.006693421622965943D;

    public static List<Point> WGS_84GCJ_02(List<Point> points) {
        List<Point> pointList = new ArrayList();
        for (Point point : points) {
            Point apoint = new Point();

            Point dev = calDev(point.getLatitude(), point.getLongitude());
            apoint.setLatitude(Double.valueOf(point.getLatitude().doubleValue() + dev.getLatitude().doubleValue()));
            apoint.setLongitude(Double.valueOf(point.getLongitude().doubleValue() + dev.getLongitude().doubleValue()));
            pointList.add(apoint);
        }
        return pointList;
    }

    public static List<Point> GCJ_02ToWGS_84(List<Point> points) {
        List<Point> pointList = new ArrayList();
        for (Point point : points) {
            Point apoint = new Point();

            Point dev = calDev(point.getLatitude(), point.getLongitude());
            apoint.setLatitude(Double.valueOf(point.getLatitude().doubleValue() - dev.getLatitude().doubleValue()));
            apoint.setLongitude(Double.valueOf(point.getLongitude().doubleValue() - dev.getLongitude().doubleValue()));
            pointList.add(apoint);
        }
        return pointList;
    }

    private static Point calDev(Double wgLat, Double wgLon) {
        if (isOutOfChina(wgLat, wgLon)) {
            return new Point(Double.valueOf(0.0D), Double.valueOf(0.0D));
        }
        double dLat = calLat(Double.valueOf(wgLon.doubleValue() - 105.0D), Double.valueOf(wgLat.doubleValue() - 35.0D)).doubleValue();
        double dLon = calLon(Double.valueOf(wgLon.doubleValue() - 105.0D), Double.valueOf(wgLat.doubleValue() - 35.0D)).doubleValue();
        double radLat = wgLat.doubleValue() / 180.0D * 3.141592653589793D;
        double magic = Math.sin(radLat);
        magic = 1.0D - 0.006693421622965943D * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = dLat * 180.0D / (6335552.717000426D / (magic * sqrtMagic) * 3.141592653589793D);
        dLon = dLon * 180.0D / (6378245.0D / sqrtMagic * Math.cos(radLat) * 3.141592653589793D);
        return new Point(Double.valueOf(dLat), Double.valueOf(dLon));
    }

    private static boolean isOutOfChina(Double lat, Double lon) {
        if ((lon.doubleValue() < 72.004D) || (lon.doubleValue() > 137.8347D))
            return true;
        if ((lat.doubleValue() < 0.8293D) || (lat.doubleValue() > 55.8271D))
            return true;
        return false;
    }

    private static Double calLat(Double x, Double y) {
        Double resultLat = Double.valueOf(-100.0D + 2.0D * x.doubleValue() + 3.0D * y.doubleValue() + 0.2D * y.doubleValue() * y.doubleValue() + 0.1D * x.doubleValue() * y.doubleValue() + 0.2D * Math.sqrt(Math.abs(x.doubleValue())));
        resultLat = Double.valueOf(resultLat.doubleValue() + (20.0D * Math.sin(6.0D * x.doubleValue() * 3.141592653589793D) + 20.0D * Math.sin(2.0D * x.doubleValue() * 3.141592653589793D)) * 2.0D / 3.0D);
        resultLat = Double.valueOf(resultLat.doubleValue() + (20.0D * Math.sin(y.doubleValue() * 3.141592653589793D) + 40.0D * Math.sin(y.doubleValue() / 3.0D * 3.141592653589793D)) * 2.0D / 3.0D);
        resultLat = Double.valueOf(resultLat.doubleValue() + (160.0D * Math.sin(y.doubleValue() / 12.0D * 3.141592653589793D) + 320.0D * Math.sin(y.doubleValue() * 3.141592653589793D / 30.0D)) * 2.0D / 3.0D);
        return resultLat;
    }

    private static Double calLon(Double x, Double y) {
        Double resultLon = Double.valueOf(300.0D + x.doubleValue() + 2.0D * y.doubleValue() + 0.1D * x.doubleValue() * x.doubleValue() + 0.1D * x.doubleValue() * y.doubleValue() + 0.1D * Math.sqrt(Math.abs(x.doubleValue())));
        resultLon = Double.valueOf(resultLon.doubleValue() + (20.0D * Math.sin(6.0D * x.doubleValue() * 3.141592653589793D) + 20.0D * Math.sin(2.0D * x.doubleValue() * 3.141592653589793D)) * 2.0D / 3.0D);
        resultLon = Double.valueOf(resultLon.doubleValue() + (20.0D * Math.sin(x.doubleValue() * 3.141592653589793D) + 40.0D * Math.sin(x.doubleValue() / 3.0D * 3.141592653589793D)) * 2.0D / 3.0D);
        resultLon = Double.valueOf(resultLon.doubleValue() + (150.0D * Math.sin(x.doubleValue() / 12.0D * 3.141592653589793D) + 300.0D * Math.sin(x.doubleValue() / 30.0D * 3.141592653589793D)) * 2.0D / 3.0D);
        return resultLon;
    }
}


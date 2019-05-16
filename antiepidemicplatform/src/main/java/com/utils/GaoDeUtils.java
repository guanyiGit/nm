package com.utils;


import com.alibaba.fastjson.JSONObject;
import com.utils.http.HttpUtils;

import java.util.HashMap;
import java.util.Map;


public class GaoDeUtils {

    // 地球半径
    private static double EARTH_RADIUS = 6371.393;

    public  static final String key = "fb31f875889bf884bb05aee008112376";

    /**
     * 坐标转换
     */
    public  static  Map<String,Double>   coordinateSwitch(double lng, double lat){
        HashMap<String, Double> map = new HashMap<>();
        String url = " https://restapi.amap.com/v3/assistant/coordinate/convert?" +
               "locations=" + lng+","+lat+
               "&key="+key;
        String res = HttpUtils.sendGet(url);
        JSONObject json = JSONObject.parseObject(res);
        String status = json.get("status").toString();
        if("1".equals(status)){
            String[] locations = json.get("locations").toString().split(",");
            map.put("lng", Double.valueOf(locations[0]));
            map.put("lat", Double.valueOf(locations[1]));
        }
        return map;
    }

    /**
     * 计算两个经纬度之间的距离(单位：米)
     *
     * @param lat1  纬度1
     * @param lng1  经度1
     * @param lat2  纬度2
     * @param lng2  经度2
     * @return
     */
    public static double GetDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 1000);
        return s;
    }



    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }




    public static void main(String[] args) {
       GaoDeUtils.coordinateSwitch(116.481499,39.990475);
    }


}

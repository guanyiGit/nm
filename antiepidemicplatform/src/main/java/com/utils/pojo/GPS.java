/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: GPS
 * Author:   Administrator
 * Date:     2018/11/20 9:42
 * Description: 坐标对象
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.utils.pojo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈坐标对象〉
 *
 * @author Administrator
 * @create 2018/11/20
 * @since 1.0.0
 */
public class GPS {
    private double lat;
    private double lon;

    public GPS(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String toString() {
        return "lat:" + lat + "," + "lon:" + lon;
    }

}

package com.soholy.cb.entity.iot.check;

public class Point {
  private Double longitude;
  
  private Double latitude;
  
  public Point() {}
  
  public Point(Double longitude, Double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
  }
  
  public Double getLongitude() { return this.longitude; }
  
  public void setLongitude(Double longitude) { this.longitude = longitude; }
  
  public Double getLatitude() { return this.latitude; }
  
  public void setLatitude(Double latitude) { this.latitude = latitude; }
}

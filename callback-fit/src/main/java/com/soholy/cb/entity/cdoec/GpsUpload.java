package com.soholy.cb.entity.cdoec;

public class GpsUpload extends UploadBean {
  private float longitude;
  
  private float latitude;
  
  public GpsUpload(float longitude, float latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
  }
  
  public GpsUpload() {}
  
  public float getLongitude() { return this.longitude; }
  
  public void setLongitude(float longitude) { this.longitude = longitude; }
  
  public float getLatitude() { return this.latitude; }
  
  public void setLatitude(float latitude) { this.latitude = latitude; }
}

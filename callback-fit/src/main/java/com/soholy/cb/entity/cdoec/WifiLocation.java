package com.soholy.cb.entity.cdoec;

public class WifiLocation {
  private String bssid;
  
  private float rssi;
  
  public String getBssid() { return this.bssid; }
  
  public void setBssid(String bssid) { this.bssid = bssid; }
  
  public float getRssi() { return this.rssi; }
  
  public void setRssi(float rssi) { this.rssi = rssi; }
  
  public String toString() {
    return "WifiLocation [bssid=" + this.bssid + ", rssi=" + this.rssi + ", getBssid()=" + getBssid() + ", getRssi()=" + 
      getRssi() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super
      .toString() + "]";
  }
}

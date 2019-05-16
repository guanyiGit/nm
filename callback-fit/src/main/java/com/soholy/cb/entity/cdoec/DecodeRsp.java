package com.soholy.cb.entity.cdoec;

import java.util.Date;

public class DecodeRsp<T> extends Object {
  private int mid;
  
  private int resultCode;
  
  private String IMEI;
  
  private Date rspTime;
  
  private T t;
  
  public T getT() {
    return (T)this.t;
  }
  
  public void setT(T t) { this.t = t; }
  
  public String getIMEI() { return this.IMEI; }
  
  public void setIMEI(String iMEI) { this.IMEI = iMEI; }
  
  public int getMid() { return this.mid; }
  
  public void setMid(int mid) { this.mid = mid; }
  
  public int getResultCode() { return this.resultCode; }
  
  public void setResultCode(int resultCode) { this.resultCode = resultCode; }
  
  public Date getRspTime() { return this.rspTime; }
  
  public void setRspTime(Date rspTime) { this.rspTime = rspTime; }
  
  public String toString() {
    return "DecodeRsp [mid=" + this.mid + ", resultCode=" + this.resultCode + ", IMEI=" + this.IMEI + ", rspTime=" + this.rspTime + ", getIMEI()=" + 
      getIMEI() + ", getMid()=" + getMid() + ", getResultCode()=" + getResultCode() + ", getRspTime()=" + 
      getRspTime() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super
      .toString() + "]";
  }
  
  public DecodeRsp(int mid, int resultCode, String iMEI, Date rspTime) {
    this.mid = mid;
    this.resultCode = resultCode;
    this.IMEI = iMEI;
    this.rspTime = rspTime;
  }
  
  public DecodeRsp() {}
}

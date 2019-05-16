package com.soholy.cb.enums;

public  enum CodecVersion {
  BASIC(1.4F),
  ICCID(1.5F),
  VOLTAGE(1.6F),
  CODEC_VERSION(1.7F);
  
  private float version;
  
  CodecVersion(float version) { this.version = version; }
  
  public float getVersion() { return this.version; }
  
  public void setVersion(float version) { this.version = version; }
}

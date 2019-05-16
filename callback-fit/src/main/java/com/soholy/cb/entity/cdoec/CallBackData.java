package com.soholy.cb.entity.cdoec;

import com.soholy.cb.entity.TDeviceDataWifiEntity;
import com.soholy.cb.entity.TDeviceInfoEntity;
import com.soholy.cb.entity.TDeviceRecordEntity;
import java.util.List;

public class CallBackData extends TDeviceRecordEntity {
  private TDeviceInfoEntity tDevice;
  
  private List<TDeviceDataWifiEntity> tDeviceDataWifis;
  
  public TDeviceInfoEntity gettDevice() { return this.tDevice; }
  
  public void settDevice(TDeviceInfoEntity tDevice) { this.tDevice = tDevice; }
  
  public List<TDeviceDataWifiEntity> gettDeviceDataWifis() { return this.tDeviceDataWifis; }
  
  public void settDeviceDataWifis(List<TDeviceDataWifiEntity> tDeviceDataWifis) { this.tDeviceDataWifis = tDeviceDataWifis; }
}

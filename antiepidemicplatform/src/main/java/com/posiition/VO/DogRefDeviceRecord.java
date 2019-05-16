package com.posiition.VO;

import com.entities.TDogInfo;

import java.io.Serializable;
import java.util.List;

public class DogRefDeviceRecord implements Serializable {
    private TDogInfo tDogInfo;
    private List<DeviceInfoRefDeviceRecord> deviceInfoRefDeviceRecords;

    public TDogInfo gettDogInfo() {
        return tDogInfo;
    }

    public DogRefDeviceRecord() {
    }

    public void settDogInfo(TDogInfo tDogInfo) {
        this.tDogInfo = tDogInfo;
    }

    public List<DeviceInfoRefDeviceRecord> getDeviceInfoRefDeviceRecords() {
        return deviceInfoRefDeviceRecords;
    }

    public void setDeviceInfoRefDeviceRecords(List<DeviceInfoRefDeviceRecord> deviceInfoRefDeviceRecords) {
        this.deviceInfoRefDeviceRecords = deviceInfoRefDeviceRecords;
    }
}

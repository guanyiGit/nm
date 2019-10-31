package com.soholy.pojo.aep.vo_v2;

import com.soholy.entity.TDeviceDataWifi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/*
ALTER TABLE t_device_data MODIFY column `data_type` int NOT NULL DEFAULT '0' COMMENT '0gps,1wifi,2省电模式数据，3告警数据，4开机数据，5基站定位 6计步';
ALTER TABLE t_device_data_wifi  add column data_time datetime comment '数据上报时间';

 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TDataWifi extends TDeviceDataWifi implements Serializable {
    private Date dataTime;
}

package com.soholy.pojo.aep.vo_v2;

import com.soholy.entity.TDeviceRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
ALTER TABLE t_device_data  ADD column  `mode` int default '0' comment '1健康管理模式 2追踪模式';
ALTER TABLE t_device_data  ADD column  `info_type` int default '0' comment '1心跳包 2正常包';
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TData extends TDeviceRecord implements Serializable {

    /**
     * 0:混合数据包  				(locations + locations + wifis)
     * 1:gps位置  					(common + locations)
     * 2:wifi数据  					(common + wifis)
     * 3:设备开机数据  				(common)
     * 4:省电模式下的心跳数据包  	(common)
     * 5:设备电量不足告警  			(common)
     */
    private Integer info_type;

    /**
     * 协议版本号,固定1.0,string
     */
    private String version;
}

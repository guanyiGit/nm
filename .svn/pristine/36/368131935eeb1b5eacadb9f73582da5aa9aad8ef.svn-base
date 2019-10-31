package com.soholy.pojo.aep.vo_v2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
/*
CREATE TABLE t_data_gps(
	gps_record_id varchar(50) primary key	comment '主键id',
	device_id int not null comment '设备id',
	device_date_id varchar(50) not null comment '设备数据id',
	lng double comment '经度',
	lan double comment '纬度',
	data_time datetime comment '数据获取时间',
	cretion_time datetime not null comment '数据获取时间'
)CHARSET='utf8' comment='设备gsp数据表';

*/

/**
 * 设备gsp数据表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TDataGps implements Serializable {
    /**
     * '主键id'
     */
    private String gps_record_id;
    /**
     * '设备id'
     */
    private String device_id;
    /**
     * '设备数据id'
     */
    private String device_date_id;
    /**
     * '经度'
     */
    private Double lng;
    /**
     * 纬度
     */
    private Double lan;
    /**
     * '数据获取时间'
     */
    private Date data_time;
    /**
     * '数据获取时间'
     */
    private Date cretion_time;

}

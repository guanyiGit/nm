package com.soholy.pojo.aep.vo_v2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/*
CREATE TABLE t_data_station(
	station_record_id varchar(50) primary key	comment '主键id',
	device_id int not null comment '设备id',
	device_date_id varchar(50) not null comment '设备数据id',
	mnc varchar(250) comment '网络类型：0移动、1联通(电信对应sid),非空,string',
	lac varchar(250) comment '小区号(电信对应nid),非空,string',
	ci varchar(250) comment '基站号(电信对应bid),非空,string',
	data_time datetime comment '基站信息获取时间,非空,int64',
	mark int not null default '0' comment '0未转换，1已成功转换，2转换失败',
	cretion_time datetime not null comment '数据获取时间'
)CHARSET='utf8' comment='设备站点数据表';

*/


/**
 * 设备gsp数据表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TDataStation implements Serializable {

    /**
     * '主键id'
     */
    private String station_record_id;

    /**
     * '设备id'
     */
    private String device_id;
    /**
     * '设备数据id'
     */
    private String device_date_id;

    /**
     * '网络类型：0移动、1联通(电信对应sid),非空,string'
     */
    private String mnc;
    /**
     * '小区号(电信对应nid),非空,string'
     */
    private String lac;
    /**
     * '基站号(电信对应bid),非空,string'
     */
    private String ci;
    /**
     * '基站信息获取时间,非空,int64'
     */
    private Date data_time;


    /**
     * 0未转换，1已成功转换，2转换失败
     */
    private Integer mark;

    /**
     * '数据获取时间'
     */
    private Date cretion_time;

}

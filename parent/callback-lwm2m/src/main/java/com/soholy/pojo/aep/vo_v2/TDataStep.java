package com.soholy.pojo.aep.vo_v2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/*
CREATE TABLE t_data_step(
	step_record_id varchar(50) primary key	comment '主键id',
	device_id int not null comment '设备id',
	start_time datetime comment '开始时间',
	end_time datetime comment '结束时间',
	data_time datetime comment '步数',
	step_num 	int comment '步数',
	cretion_time datetime comment '创建时间'
)CHARSET='utf8mb4' comment='设备计步数据表';

*/

/**
 * 设备gsp数据表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TDataStep implements Serializable {


    /**
     * '主键id'
     */
    private String step_record_id;
    /**
     * '设备id'
     */
    private String device_id;
    /**
     * '开始时间'
     */
    private Date start_time;

    /**
     * '结束时间
     */
    private Date end_time;
    /**
     * 数据时间
     */
    private Date data_time;
    /**
     * 步数
     */
    private Integer step_num;


    /**
     * '创建时间'
     */
    private Date cretion_time;

}

package com.soholy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationConverToGpsResult {
    /**
     * 0, 成功 10000: 参数错误 10001: 无查询结果
     */
    private Integer errcode;
    private Double lat;
    private Double lon;
    private Integer radius;
    private String address;
}

package com.statisanalysis.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/4 12:04
 * @Version 1.0
 */
@Mapper
public interface OwnerAmountDao {
    //获取截止日期的犬主信息
//    List<Date> getOwnerInfo(Map<String,Object> params);

    //---------------10/15
    //防疫员、乡组织查询截止日期之前犬主
    List<Date> getOwnerCreateDate1(Map<String,Object> map);
    //县组织查询截止日期之前犬主
    List<Date> getOwnerCreateDate2(Map<String,Object> map);
    //州组织查询截止日期之前犬主
    List<Date> getOwnerCreateDate3(Map<String,Object> map);
    //犬主数量统计
    List<Date> getOwnerNumCount(Map<String,Object> map);

}

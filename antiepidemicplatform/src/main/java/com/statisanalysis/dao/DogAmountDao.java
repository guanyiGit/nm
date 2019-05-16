package com.statisanalysis.dao;

import com.entities.AreaInfo;
import com.statisanalysis.entity.DogVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: linchong
 * @Date: 2018/9/30 10:16
 * @Version 1.0
 * 犬只数量统计
 */
@Mapper
public interface DogAmountDao {
    //获取指定日期之前的所有存活犬只数量
    List<DogVO> getDogsByTime(Map<String,Object> params);
    //获取指定日期内已死亡的犬只
    List<Date> getDogDeadTime(Map<String,Object> params);
    //获取指定日期内注销的犬只
    List<Date> getDogCancelTime(Map<String,Object> params);

    //-----------------------10.12
    //统计犬只数量趋势（防疫员）
    List<DogVO> getDogAmount1(Map<String,Object> map);
    //统计犬只数量趋势（乡组织）
    List<DogVO> getDogAmount2(Map<String,Object> map);
    //统计犬只数量趋势（县组织）
    List<DogVO> getDogAmount3(Map<String,Object> map);
    //统计犬只数量趋势（州组织）
    List<DogVO> getDogAmount4(Map<String,Object> map);
    //根据父级组织查询所有子组织所在的区域
    List<AreaInfo> getAreaInfoByOrgId(Map<String,Object> map);
    //查询犬只存栏数量
    List<DogVO> getLiveNumber(Map<String,Object> map);
    //查询犬只新增数量
    List<Date> getNewNumber(Map<String,Object> map);
    //查询犬只注销数量
    List<Date> getCancelNumber(Map<String,Object> map);
    //查询犬只死亡数量
    List<Date> getDeadNumber(Map<String,Object> map);

}

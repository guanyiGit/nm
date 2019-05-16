package com.statisanalysis.dao;

import com.statisanalysis.entity.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: linchong
 * @Date: 2018/9/28 14:03
 * @Version 1.0
 */
@Mapper
public interface DogCountDao {
    //统计所有犬只数量
    Integer countDogs();
    //查询犬种对应数量
    List<Data> getDogBreed(Map<String,Object> map);
    //查询性别分布
    List<Data> getDogSex(Map<String,Object> map);
    //查询年龄分布
    List<Double> getDogAge(Map<String,Object> map);
    //查询区域分布（县查所有乡的犬只信息）
    List<Data> getDogAreaInfo1(Map<String,Object> map);
    //查询区域分布（州查所有县的犬只信息）
    List<Data> getDogAreaInfo2(Map<String,Object> map);
}

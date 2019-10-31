package com.statisanalysis.dao;

import com.statisanalysis.entity.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: linchong
 * @Date: 2018/9/29 13:45
 * @Version 1.0
 */
@Mapper
public interface OwnerCountDao {
    //查询犬主分布
    List<Data> getOwnerSex(Map<String,Object> map);

    //查询年龄分布
    List<Integer> getOwnerAge(Map<String,Object> map);
    //查询区域分布(县级)
    List<Data> getOwnerAreaInfo1(Map<String,Object> map);
    //查询区域分布(州级)
    List<Data> getOwnerAreaInfo2(Map<String,Object> map);

}

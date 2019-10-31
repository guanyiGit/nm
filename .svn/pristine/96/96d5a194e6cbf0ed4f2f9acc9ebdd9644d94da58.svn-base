package com.statisanalysis.dao;

import com.entities.TDogInfo;
import com.statisanalysis.entity.DogVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/11 10:31
 * @Version 1.0
 */
@Mapper
public interface ComprehensiveReportDao {
    List<Map<String,Object>> getMonth(Map<String,Object> params);
    Integer getMonthTotal(Map<String,Object> params);
    //查询本州下的县Id，县Name(过时)
    List<Map<String,Object>> getCounties(Map<String,Object> params);
    //查询本州所有的县信息
    List<Map<String,Object>> getCountiesInfo(Integer orgId);
    //查询县组织下的所有乡组织
    List<Integer> getTownId(Integer orgId);
    //查询县组织所管的狗
    List<TDogInfo> getDogsByTownId(Integer orgId);
    //根据areaId统计犬只
    List<DogVO> getDogsByAreaId(Map<String,Object> map);
    //查询流浪犬的处理数量（本年、本月）
    List<Date> getStrayDog(Map<String,Object> map);
    //根据orgId和（year或perMonth）查询犬粪检测数量
    Map<String,Object> getFenMount(Map<String,Object> map);
    //根据orgId和（year或perMonth）查询剖检检测数量
    Map<String,Object> getPouMount(Map<String,Object> map);
}

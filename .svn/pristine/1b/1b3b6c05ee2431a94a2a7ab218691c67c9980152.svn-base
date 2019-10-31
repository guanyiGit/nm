package com.statisanalysis.dao;

import com.statisanalysis.entity.DrugNumVO;
import com.statisanalysis.entity.DrugVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 防疫统计
 * @Author: linchong
 * @Date: 2018/10/5 7:43
 * @Version 1.0
 */
@Mapper
public interface AntiepidemicCountDao {
    //查询截止日期前的防疫信息(防疫员、乡级组织)
    List<Date> getInfoByEndTime(Map<String, Object> map);
    //查询截止日期前的防疫信息(县级组织)
    List<Date> getInfoByEndTime2(Map<String, Object> map);
    //查询截止日期前的防疫信息(州级组织)
    List<Date> getInfoByEndTime3(Map<String, Object> map);


    //查询使用量排前十的药品
    List<DrugNumVO> getTopTenDrug(Map<String, Object> map);
    //查询所有的药品名称
    List<String> getAllNames(Map<String, Object> map);
    //查询指定时间段的药品使用情况
    List<DrugVO> getDrugUse(Map<String, Object> map);
}

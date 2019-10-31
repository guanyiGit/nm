package com.statisanalysis.serivce;

import com.entities.TDogInfo;
import com.statisanalysis.entity.CompreReportVO;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/11 10:36
 * @Version 1.0
 */
public interface IComprehensiveReportService {
    //获取该年份哪些月份有防疫数据
    List<Map<String,Object>> getMonth(Map<String,Object> map);
    //获取月份总数
    Integer getMonthTotal(Map<String,Object> map);
    //获取本州所有县
    List<Map<String,Object>> getCounties(Map<String,Object> map);   //(过时)
    //获取本州所有县信息
    List<Map<String,Object>> getCountiesInfo(Integer orgId);
    //获取本县下所有的乡组织id
    List<Integer> getTownId(Integer orgId);
    //查询县组织所管的狗
    List<TDogInfo> getDogsByTownId(Integer orgId);

    /**
     * 统计某月的防疫情况
     * @param map
     * @return
     */
    List<CompreReportVO> getAntiReport(Map<String,Object> map) throws Exception;

}

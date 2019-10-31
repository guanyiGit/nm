package com.statisanalysis.dao;

import com.entities.Antiepidemic;
import com.entities.OrgInfo;
import com.entities.TDogInfo;
import com.entities.TMsg;
import com.statisanalysis.entity.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/5 15:54
 * @Version 1.0
 */
@Mapper
public interface HomePageDao {
    //查询我的所有消息(作废)
    List<TMsg> findAllMsgs(Map<String,Object> map);
    //查询上月的防疫次数
    Integer findAntiepidmicTimes(Map<String,Object> map);
    //查询上月新增犬只
    Integer findAddedDogs(Map<String,Object> map);
    //查询上月注销犬只
    Integer findCancelDogs(Map<String,Object> map);
    //查询上月死亡犬只
    Integer findDeadDogs(Map<String,Object> map);
    //查询上月使用量前十药品
    List<Data> findTopTenDrug(Map<String,Object> map);
    //查询近一年每月的防疫次数
    List<Antiepidemic> findPerMonthTimes(Map<String,Object> map);
    //获取所管犬只信息
    List<TDogInfo> findDogsByTime(Map<String,Object> map);
    //获取未读消息的总条数
    Integer findUnreadTotal(Map<String,Object> map);
    //获取未读消息
    List<TMsg> findUnreadMsgs(Map<String,Object> map);
    //获取已读消息的总条数
    Integer findReadedTotal(Map<String,Object> map);
    //获取已读消息
    List<TMsg> findReadMsgs(Map<String,Object> map);
    //首页消息
    List<TMsg> findHomePageUnreadMsgs(Map<String,Object> map);

    TMsg findMsgById(String id);
    int updateMsgStatus(String id);
    //获取所有机构的信息
    List<OrgInfo> orgInfoList();
    //获取用户所有消息
    List<TMsg> getMsgList(Map<String,Object> map);
    //查询所管犬只的traceId
    List<String> getTraceIdList(Map<String,Object> map);
//    //查询犬只总数
//    Integer countTotalAmount(Map<String,Object> map);
    //统计已防疫犬只
    List<Map<String,Object>> countAntiAmount(Map<String,Object> map);
    //统计已防疫犬只数量
    List<String> counpteAntiAmount(Map<String,Object> map);
    //已防疫犬只、未防犬只列表
    List<Map<String,Object>> dogList(Map<String,Object> map);
    String findUserId(String protectorId);
}

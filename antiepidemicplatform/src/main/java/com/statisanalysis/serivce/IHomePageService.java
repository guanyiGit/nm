package com.statisanalysis.serivce;

import com.entities.OrgInfo;
import com.entities.TMsg;
import com.utils.PageUtils;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/5 17:22
 * @Version 1.0
 */
public interface IHomePageService {
    //查询我的消息（已读、未读）（作废）
    List<TMsg> getAllMsgs(Map<String,Object> map);
    //查询我的所有消息
    List<TMsg> getMsgList(Map<String,Object> map);
    //查询上月免疫次数
    Integer getAntiepidemicTimes(Map<String,Object> map) throws Exception;
    //查询上月新增犬只
    Integer getAddedDogs(Map<String,Object> map) throws Exception;
    //查询上月使用量前十的药品
    Map<String,List<Object>> getToptenDrug(Map<String,Object> map) throws Exception;
    //查询近一年每月的防疫次数
    Map<String,List<String>> getPerMonthTimes(Map<String,Object> map) throws Exception;
    //查询所管犬只数量趋势
    Map<String,List<String>> getChargeDogs(Map<String,Object> map) throws Exception;
    //查询未读消息总记录数
    Integer getUnreadedTotal(Map<String,Object> map);
    //查询未读消息
    List<TMsg> getUnreadedMsgs(Map<String,Object> map);
    //查询已读消息总记录数
    Integer getReadedTotal(Map<String,Object> map);
    //查询已读消息
    List<TMsg> getReadedMsgs(Map<String,Object> map);
    //查询首页消息
    List<TMsg> getHomePageUnreadedMsg(Map<String,Object> map);

    int updateMsgStatus(String id);
   TMsg findMsgById(String id);
    //查询所有的组织
    List<OrgInfo> getAllOrgInfo();

    //防疫统计
    Map<String,Object> countAntiAmount(Map<String,Object> map) throws ParseException;
    //犬只状态统计
    Map<String,Object> countDogNumber(Map<String,Object> map) throws Exception;
    //查询已防疫犬只、未防疫犬只列表
    PageUtils dogList(Map<String,Object> map) throws ParseException;
}

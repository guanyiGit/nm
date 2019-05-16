package com.statisanalysis.wx.dao;

import com.entities.TDogInfo;
import com.statisanalysis.wx.vo.CountVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/11/22 16:24
 * @Version 1.0
 */
@Mapper
public interface AntieCountDao {
    //查询防疫次数（乡级管理员）
    List<CountVO> getAntieTimesVillage(Map<String,Object> map);
    //查询防疫次数（县级管理员）
    List<CountVO> getAntieTimesCounty(Map<String,Object> map);
    //查询防疫次数（州级管理员）
    List<CountVO> getAntieTimesState(Map<String,Object> map);
    //查询犬尸处理次数（乡级管理员）
    List<CountVO> getCorpseTimesVillage(Map<String,Object> map);
    //查询犬尸处理次数（县级管理员）
    List<CountVO> getCorpseTimesCounty(Map<String,Object> map);
    //查询全是处理次数（州级管理员）
    List<CountVO> getCorpseTimesState(Map<String,Object> map);
    //查询粪便处理次数（乡级管理员）
    List<CountVO> getManureTimesVillage(Map<String,Object> map);
    //查询粪便处理次数（县级管理员）
    List<CountVO> getManureTimesCounty(Map<String,Object> map);
    //查询粪便处理次数（州级管理员）
    List<CountVO> getManureTimesState(Map<String,Object> map);
    //查询犬粪抗原检测次数（县级管理员）
    List<CountVO> getAntiegenTimesCounty(Map<String,Object> map);
    //查询犬粪抗原检测次数（州级管理员）
    List<CountVO> getAntiegenTimesState(Map<String,Object> map);
    Integer getMyStateAntiegenTimes(Map<String,Object> map);
    //查询犬只解剖次数（县级管理员）
    List<CountVO> getAnatomyTimesCounty(Map<String,Object> map);
    //查询犬只解剖次数（州级管理员）
    List<CountVO> getAnatomyTimesState(Map<String,Object> map);
    Integer getMyStateAnatomyTimes(Map<String,Object> map);
    //查询牛羊抗体检测次数（县级管理员）
    List<CountVO> getAntibodyTimesCounty(Map<String,Object> map);
    //查询牛羊抗体检测次数（州级管理员）
    List<CountVO> getAntibodyTimesState(Map<String,Object> map);
    Integer getMyStateAntibodyTimes(Map<String,Object> map);
    //查询牛羊脏器处理次数（县级管理员）
    List<CountVO> getInfectionTimesCounty(Map<String,Object> map);
    //查询牛羊脏器处理次数（州级管理员）
    List<CountVO> getInfectionTimesState(Map<String,Object> map);
    Integer getMyStateInfectionTimes(Map<String,Object> map);
    List<Integer> getOrgIdByAreaId(Integer areaId);
    //查找所管的犬只
    List<TDogInfo> getDogList(Map<String,Object> map);
    //查询存活犬只
    List<String> getLiveDog(Map<String,Object> map);
}

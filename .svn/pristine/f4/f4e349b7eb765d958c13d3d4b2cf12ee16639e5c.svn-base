package com.statisanalysis.wx.dao;

import com.statisanalysis.wx.vo.CountVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/11/21 14:12
 * @Version 1.0
 */
@Mapper
public interface DogAndOwnerDao {

    //查询犬只存栏数量（乡级管理员）
    List<CountVO> getDogInfoByVillage(Map<String,Object> map);
    //查询犬只存栏数量（县级管理员）
    List<CountVO> getDogInfoByCounty(Map<String,Object> map);
    //查询犬只存栏数量（州级管理员）
    List<CountVO> getDogInfoByState(Map<String,Object> map);
    //查询犬主数量（乡级管理员）
    List<CountVO> getDogOwnerByVillage(Map<String,Object> map);
    //查询犬主数量（县级管理员）
    List<CountVO> getDogOwnerByCounty(Map<String,Object> map);
    //查询犬主数量（州级管理员）
    List<CountVO> getDogOwnerByState(Map<String,Object> map);
    //查询流浪狗处理数量（乡级管理员）      --按防疫员分组
    List<CountVO> getStrayByVillage(Map<String,Object> map);
    //查询流浪狗处理数量（乡级管理员）      --查询本组织所在区域所有的所有组织及防疫员的流浪狗处理数量
    Integer countStrayDog(Map<String,Object> map);
    //查询流浪狗处理数量（县级管理员）
    List<CountVO> getStrayByCounty(Map<String,Object> map);
    //查询流浪狗处理数量（州级管理员）
    List<CountVO> getStrayByState(Map<String,Object> map);
    //查询本乡组织所有的防疫员
    List<Integer> getProtectorIds(Integer orgId);
    //查询本组织所在的区域
    String getMyAreaName(Integer orgId);
    //查询县级组织的乡级组织所属区域
    List<Integer> getAreaIdList(Integer orgId);
    //查询州级组织的乡级组织所属区域
    List<Integer> getAreaIdList2(Integer orgId);
    //查询所有县级组织所属区域
    List<Integer> getCountyAreaIdList();
    //根据县级areaId查询其所有乡级areaId
    List<Integer> getVillageIdList(Integer areaId);
    //根据州级OrgId查询县级组织的areaId
    List<Integer> getMyCountyAreaIdList(Integer orgId);
    //根据乡级areaId查询县级AreaId
    Integer getPAreaId(Integer areaId);
    //根据乡级areaId查询乡级及县级的名称和id
    CountVO getPAreaInfo(Integer areaId);
    //根据县级areaId查询县级的名称
    CountVO getAreaInfo(Integer areaId);
    //查询防疫员信息
    CountVO getProtectorInfo(Integer protectorId);
}

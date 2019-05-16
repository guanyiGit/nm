package com.dogmanager.dao;

import com.dogmanager.VO.DogCanselVO;
import com.entities.TDogCancel;
import com.entities.TSysDict;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface TDogCancelMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(TDogCancel record);

    TDogCancel selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(TDogCancel record);

    List<DogCanselVO> findDogCancelList(HashMap<String,Object> map);

    DogCanselVO findDogCanselVOById(Integer Id);
    DogCanselVO findDogCanselVOByIdI18N(Map<String,Object> map);

    Integer findDogIdByDeviceNo(String deviceNo);

    List<TSysDict> findTSysDict(String type);
    //查询字典列表-多语言
    List<TSysDict> findTSysDictI18N(Map<String,Object> map);

    void deleteDeviceRefDog(String traceId);
}
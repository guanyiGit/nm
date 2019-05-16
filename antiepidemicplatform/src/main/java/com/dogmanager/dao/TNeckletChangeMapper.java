package com.dogmanager.dao;

import com.dogmanager.VO.NectletChangeVO;
import com.entities.TDeviceRefDog;
import com.entities.TNeckletChange;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface TNeckletChangeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TNeckletChange record);

    NectletChangeVO selectByPrimaryKey(Integer id);
    NectletChangeVO selectByPrimaryKeyI18N(Map<String,Object> map);

    int updateByPrimaryKey(TNeckletChange record);

    List<NectletChangeVO> findTNeckletChangeList(HashMap<String,Object> map);

    int updateDogRefDevice(TDeviceRefDog record );

    TDeviceRefDog findByDeviceNo(String deviceNo);
}
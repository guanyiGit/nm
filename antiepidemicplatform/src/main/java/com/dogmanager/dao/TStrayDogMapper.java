package com.dogmanager.dao;

import com.dogmanager.VO.StrayDogVO;
import com.entities.TStrayDog;
import com.entities.TStrayDogExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface TStrayDogMapper {
    int countByExample(TStrayDogExample example);

    int deleteByExample(TStrayDogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TStrayDog record);

    int insertSelective(TStrayDog record);

    List<TStrayDog> selectByExample(TStrayDogExample example);

    TStrayDog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TStrayDog record, @Param("example") TStrayDogExample example);

    int updateByExample(@Param("record") TStrayDog record, @Param("example") TStrayDogExample example);

    int updateByPrimaryKeySelective(TStrayDog record);

    int updateByPrimaryKey(TStrayDog record);

    List<StrayDogVO> findStrayDogList(HashMap<String,Object> map);

    StrayDogVO findStrayDogById(Integer Id);
    StrayDogVO findStrayDogByIdI18N(Map<String,Object> map);

    String checkNo(String no);
}
package com.soholy.mapper;

import com.soholy.entity.po.WifiExample;
import org.apache.ibatis.annotations.Param;
import com.soholy.entity.po.Wifi;

import java.util.List;

public interface WifiMapper {
    int countByExample(WifiExample example);

    int deleteByExample(WifiExample example);

    int deleteByPrimaryKey(String priId);

    int insert(Wifi record);

    int insertSelective(Wifi record);

    List<Wifi> selectByExample(WifiExample example);

    Wifi selectByPrimaryKey(String priId);

    int updateByExampleSelective(@Param("record") Wifi record, @Param("example") WifiExample example);

    int updateByExample(@Param("record") Wifi record, @Param("example") WifiExample example);

    int updateByPrimaryKeySelective(Wifi record);

    int updateByPrimaryKey(Wifi record);
}
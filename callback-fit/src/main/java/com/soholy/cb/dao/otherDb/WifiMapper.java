package com.soholy.cb.dao.otherDb;

import com.soholy.cb.entity.otherDb.po.Wifi;
import com.soholy.cb.entity.otherDb.po.WifiExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WifiMapper {
  int countByExample(WifiExample paramWifiExample);
  
  int deleteByExample(WifiExample paramWifiExample);
  
  int deleteByPrimaryKey(String paramString);
  
  int insert(Wifi paramWifi);
  
  int insertSelective(Wifi paramWifi);
  
  List<Wifi> selectByExample(WifiExample paramWifiExample);
  
  Wifi selectByPrimaryKey(String paramString);
  
  int updateByExampleSelective(@Param("record") Wifi paramWifi, @Param("example") WifiExample paramWifiExample);
  
  int updateByExample(@Param("record") Wifi paramWifi, @Param("example") WifiExample paramWifiExample);
  
  int updateByPrimaryKeySelective(Wifi paramWifi);
  
  int updateByPrimaryKey(Wifi paramWifi);
}

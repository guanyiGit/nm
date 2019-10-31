package com.soholy.cb.dao;

import com.soholy.cb.entity.iot.check.Msg;
import com.soholy.cb.entity.iot.check.Point;
import com.soholy.cb.entity.iot.check.RequiredForAlarm;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AlarmRelatedInforMapper {
  RequiredForAlarm queryInfo(String paramString);
  
  List<RequiredForAlarm> queryAllInfo();
  
  List<Point> queryPoints(@Param("fenceId") Integer paramInteger);
  
  Integer insertMsg(@Param("msg") Msg paramMsg);
  
  void refMsg(@Param("receiver") Integer paramInteger1, @Param("msgId") Integer paramInteger2);
}

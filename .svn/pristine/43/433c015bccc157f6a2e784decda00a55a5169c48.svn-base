package atshunhengli.com.mapper;

import atshunhengli.com.entity.callback.Msg;
import atshunhengli.com.entity.callback.Point;
import atshunhengli.com.entity.callback.RequiredForAlarm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlarmRelatedInforMapper {
    RequiredForAlarm queryInfo(String imei);
    List<RequiredForAlarm> queryAllInfo();
    List<Point> queryPoints(@Param("fenceId") Integer fenceId);
    Integer insertMsg(@Param("msg") Msg msg);
    void refMsg(@Param("receiver") Integer receiver,@Param("msgId") Integer msgId);
}

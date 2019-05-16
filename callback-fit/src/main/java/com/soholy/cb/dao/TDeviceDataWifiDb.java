package com.soholy.cb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soholy.cb.entity.TDeviceDataWifiEntity;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface TDeviceDataWifiDb extends BaseMapper<TDeviceDataWifiEntity> {
  @Insert({"<script>INSERT INTO t_device_data_wifi (     device_data_wifi_id, bssid, rssi, device_data_id, device_no, creation_time) VALUES<foreach collection='datas' item='data' open='(' separator='),(' close=')'>     #{data.deviceDataWifiId}, #{data.bssid}, #{data.rssi}, #{data.deviceDataId}, #{data.deviceNo}, #{data.creationTime}</foreach></script>"})
  int saves(@Param("datas") List<TDeviceDataWifiEntity> paramList);
}

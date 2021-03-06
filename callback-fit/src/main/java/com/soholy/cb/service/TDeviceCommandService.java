package com.soholy.cb.service;

import com.soholy.cb.entity.TDeviceCommandEntity;
import com.soholy.cb.entity.TDeviceInfoEntity;
import com.soholy.cb.entity.cdoec.CallBackData;
import com.soholy.cb.entity.cdoec.DecodeRsp;
import com.soholy.cb.enums.CodecVersion;

import java.util.List;

public interface TDeviceCommandService {
    /**
     * 查询命令
     *
     * @param deviceNo 设备no  为空查询所有设备
     * @param status   设备状态
     * @return
     */
    List<TDeviceCommandEntity> findCmdByNoAndStatus(String deviceNo, Integer status);

    boolean updateById(TDeviceCommandEntity paramTDeviceCommandEntity);

    void resStart(CallBackData paramCallBackData, TDeviceInfoEntity paramTDeviceInfoEntity, CodecVersion codecVersion);

    boolean cmdResHandle(DecodeRsp paramDecodeRsp);
}

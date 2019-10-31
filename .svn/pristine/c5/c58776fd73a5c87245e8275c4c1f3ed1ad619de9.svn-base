package com.soholy.cb.service;

import com.soholy.cb.entity.TDeviceCommandEntity;
import com.soholy.cb.entity.TDeviceInfoEntity;
import com.soholy.cb.entity.cdoec.CallBackData;
import com.soholy.cb.entity.cdoec.DecodeRsp;
import java.util.List;

public interface TDeviceCommandService {
  List<TDeviceCommandEntity> findCmdByNoAndStatus(String paramString, Integer paramInteger);
  
  boolean updateById(TDeviceCommandEntity paramTDeviceCommandEntity);
  
  void resStart(CallBackData paramCallBackData, TDeviceInfoEntity paramTDeviceInfoEntity);
  
  boolean cmdResHandle(DecodeRsp paramDecodeRsp);
}

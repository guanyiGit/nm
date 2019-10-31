package com.soholy.cb.service;

import com.soholy.cb.entity.TDeviceCommandEntity;
import com.soholy.cb.entity.TDeviceInfoEntity;
import com.soholy.cb.entity.cdoec.CallBackData;
import com.soholy.cb.entity.cdoec.DecodeRsp;
import com.soholy.cb.enums.CodecVersion;

import java.util.List;

public interface TDeviceCommandService {
  List<TDeviceCommandEntity> findCmdByNoAndStatus(String paramString, Integer paramInteger);
  
  boolean updateById(TDeviceCommandEntity paramTDeviceCommandEntity);
  
  void resStart(CallBackData paramCallBackData, TDeviceInfoEntity paramTDeviceInfoEntity, CodecVersion codecVersion);
  
  boolean cmdResHandle(DecodeRsp paramDecodeRsp, CodecVersion codecVersion);
}

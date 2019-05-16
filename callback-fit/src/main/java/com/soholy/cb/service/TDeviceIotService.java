package com.soholy.cb.service;

import com.soholy.cb.entity.TDeviceInfoEntity;
import com.soholy.cb.entity.cdoec.CallBackData;
import com.soholy.cb.entity.cdoec.UploadBean;
import java.util.List;

public interface TDeviceIotService {
  List<TDeviceInfoEntity> findDevicesByIotId(String paramString);
  
  CallBackData dataPrepare(UploadBean paramUploadBean);
  
  boolean modifyDeviceInfo(String paramString1, String paramString2) throws Exception;
  
  TDeviceInfoEntity register(String paramString1, String paramString2, String paramString3) throws Exception;
  
  List<TDeviceInfoEntity> registerAll(List<TDeviceInfoEntity> paramList) throws Exception;
  
  boolean deleteDeviceByIotId(String paramString) throws Exception;
  
  void logout(String paramString);
}

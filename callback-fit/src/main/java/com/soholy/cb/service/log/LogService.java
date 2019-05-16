package com.soholy.cb.service.log;

import com.soholy.cb.entity.cdoec.CodecBean;
import com.soholy.cb.entity.cdoec.T_iot_data;
import java.util.List;

public interface LogService {
  void saveLog(CodecBean paramCodecBean, byte[] paramArrayOfByte, String paramString);
  
  List<T_iot_data> findLog(String paramString, int paramInt1, int paramInt2);
  
  void printLog(CodecBean paramCodecBean, byte[] paramArrayOfByte, String paramString);
  
  void printLog(CodecBean paramCodecBean);
  
  void printLog(byte[] paramArrayOfByte);
  
  void printLog(String paramString);
}

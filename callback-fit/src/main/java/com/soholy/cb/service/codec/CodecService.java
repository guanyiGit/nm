package com.soholy.cb.service.codec;

import com.soholy.cb.entity.cdoec.CodecBean;
import com.soholy.cb.enums.CmdType;
import com.soholy.cb.enums.CodecVersion;

public interface CodecService {
  CodecBean decodec(byte[] paramArrayOfByte);
  
  byte[] generateComanmd(CmdType paramCmdType, int paramInt1, int paramInt2, CodecVersion codecVersion);
}

package com.soholy.service.codec;

import com.soholy.pojo.aep.em.CmdType_lwM2M;

public interface CodecService {

    /**
     * @param imei
     * @param cmdType  命令类型
     * @param cmdValue 命令值
     * @param mid      命令id
     * @return
     * @Description (生产响应命令)
     */
    byte[] generateComanmd_lwM2M(String imei,CmdType_lwM2M cmdType,String cmdValue, Long mid);
}

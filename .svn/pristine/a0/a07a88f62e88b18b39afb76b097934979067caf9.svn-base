package atshunhengli.com.service;


import atshunhengli.com.entity.cdoec.CodecBean;
import atshunhengli.com.entity.iot.CmdType;

public interface CodecService {

    /**
     * @param inputBinary
     * @return
     * @throws Exception
     * @Description (解析数据)
     */
    public CodecBean decodec(byte[] inputBinary) throws Exception;

    /**
     * @param cmdType  命令类型
     * @param cmdValue 命令值
     * @param mid      命令id
     * @return
     * @Description (生产响应命令)
     */
    byte[] generateComanmd(CmdType cmdType, int cmdValue, int mid);

}

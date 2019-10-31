package com.soholy.pojo.aep.command.rq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCommandAepRp implements Serializable {

    /**
     * 协议版本号,固定1.0,string
     */
    private String version;
    /**
     * 设备imei号,非空,string
     */
    private String imei;

    /**
     * 指令结果,非空,多组
     */
    private List<CommandAepRpRet> rets;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommandAepRpRet {
        /**
         * 指令id(下发指令的mid),非空，string
         */
        private String mid;
        /**
         * 执行结果,非空，int32
         * 1成功 2失败 3其他或错误
         */
        private Integer ret;
        /**
         * 命令值
         */
        private String val;
        /**
         * 命令响应发时间
         */
        private String time;

    }

}

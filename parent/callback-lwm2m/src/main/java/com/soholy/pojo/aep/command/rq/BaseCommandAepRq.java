package com.soholy.pojo.aep.command.rq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCommandAepRq implements Serializable {

    /**
     * 协议版本号,固定1.0,string
     */
    private String version;
    /**
     * 设备imei号,非空,string
     */
    private String imei;

    /**
     * 指令,非空,多组
     */
    private List<CommandAepRqMode> modes;

    static
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class CommandAepRqMode {

        /**
         * ：指令id,非空，string
         */
        private String mid;
        /**
         * 指令类型,非空，int32
         * 0:开机回复
         * 1:模式切换
         * 2:设备当前模式的上传间隔调整
         */
        private String type;

        /**
         * :指令值,非空,int32
         * 当type = 0时，模式切换指令，val可选值为：
         * 1:成功激活
         * 2:未激活
         * 当type = 1时，模式切换指令，val可选值为：
         * 1:普通模式
         * 2:省电模式
         * 当type = 2时，间隔设置指令，val为普通数值，单位秒
         */
        private String val;

        /**
         * :指令下发时间
         */
        private String time;

    }


}

/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DeviceStatusEnum
 * Author:   Administrator
 * Date:     2018/12/4 9:35
 * Description: 设备状态
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dogmanager.enmus;

/**
 * 〈一句话功能简述〉<br> 
 * 〈设备状态〉
 *
 * @author Administrator
 * @create 2018/12/4
 * @since 1.0.0
 */
public enum DeviceStatusEnum {
    DEVICE_STATUS_INACTIVE(0,"未激活"),
    DEVICE_STATUS_ACTIVATED(1,"已激活"),
    DEVICE_STATUS_LOSE(2,"丢失"),
    DEVICE_STATUS_DAMAGE(3,"损坏"),
    ;
    private DeviceStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private Integer code;
    private String msg;
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}

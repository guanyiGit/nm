package com.dogmanager.enmus;

/**
 * Created by Administrator on 2018/9/28.
 */
public enum DogStatusEnum {
    DOG_STATUS_NORMAL(0,"正常"),
    DOG_STATUS_DIE(1,"死亡"),
    DOG_STATUS_LOSE(2,"丢失"),
    DOG_STATUS_CHECK(3,"送检"),
    ;
    private DogStatusEnum(Integer code, String msg) {
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

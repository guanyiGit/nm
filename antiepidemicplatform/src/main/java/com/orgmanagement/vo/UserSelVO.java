package com.orgmanagement.vo;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/19 0:06
 * @Version 1.0
 */
public class UserSelVO {
    private Integer id;
    private String name;
    private String isCheck; //下拉回显选中

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    @Override
    public String toString() {
        return "UserSelVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isCheck='" + isCheck + '\'' +
                '}';
    }
}

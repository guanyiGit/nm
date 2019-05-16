package com.statisanalysis.wx.vo;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/11/26 11:30
 * @Version 1.0
 */
public class Result {
    private String name;
    private Integer count;

    public Result() {
    }

    public Result(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

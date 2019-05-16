package com.statisanalysis.entity;

/**
 * @Description 统计犬主数量
 * @Author: linchong
 * @Date: 2018/10/4 12:00
 * @Version 1.0
 */
public class OwnerNumVO {
    private String perMonth;
    private Integer totalNum;   //犬主总数
    private Integer newNum;     //新增犬主

    public OwnerNumVO() {
    }

    public OwnerNumVO(String perMonth, Integer totalNum, Integer newNum) {
        this.perMonth = perMonth;
        this.totalNum = totalNum;
        this.newNum = newNum;
    }

    public String getPerMonth() {
        return perMonth;
    }

    public void setPerMonth(String perMonth) {
        this.perMonth = perMonth;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getNewNum() {
        return newNum;
    }

    public void setNewNum(Integer newNum) {
        this.newNum = newNum;
    }

    @Override
    public String toString() {
        return "OwnerNumVO{" +
                "perMonth='" + perMonth + '\'' +
                ", totalNum=" + totalNum +
                ", newNum=" + newNum +
                '}';
    }
}

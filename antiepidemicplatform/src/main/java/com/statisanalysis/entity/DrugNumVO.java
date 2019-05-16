package com.statisanalysis.entity;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/16 13:29
 * @Version 1.0
 */
public class DrugNumVO {
    private Integer id;
    private String drugName;
    private Integer drugNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Integer getDrugNum() {
        return drugNum;
    }

    public void setDrugNum(Integer drugNum) {
        this.drugNum = drugNum;
    }

    @Override
    public String toString() {
        return "DrugNumVO{" +
                "id=" + id +
                ", drugName='" + drugName + '\'' +
                ", drugNum=" + drugNum +
                '}';
    }
}

package com.statisanalysis.entity;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/12 17:16
 * @Version 1.0
 */
public class AllNumVO {
    private Integer dogLiveNum;    //犬只存栏数量
    private Integer dogNewNum;     //犬只新增数量
    private Integer dogCancelNum;  //犬只注销数量
    private Integer ownerNewNum;    //新增犬主数量
    private Integer antiepidemicTimes;  //防疫次数
    private Integer dogCorpseNum;   //犬尸处理数量
    private Integer manureNum;      //粪便处理次数
    private Integer llqNum;     //流浪犬处理数量
    private Integer qzghTimes;   //犬主更换次数
    private Integer xqghTimes;      //项圈更换次数

    public Integer getDogLiveNum() {
        return dogLiveNum;
    }

    public void setDogLiveNum(Integer dogLiveNum) {
        this.dogLiveNum = dogLiveNum;
    }

    public Integer getDogNewNum() {
        return dogNewNum;
    }

    public void setDogNewNum(Integer dogNewNum) {
        this.dogNewNum = dogNewNum;
    }

    public Integer getDogCancelNum() {
        return dogCancelNum;
    }

    public void setDogCancelNum(Integer dogCancelNum) {
        this.dogCancelNum = dogCancelNum;
    }

    public Integer getOwnerNewNum() {
        return ownerNewNum;
    }

    public void setOwnerNewNum(Integer ownerNewNum) {
        this.ownerNewNum = ownerNewNum;
    }

    public Integer getAntiepidemicTimes() {
        return antiepidemicTimes;
    }

    public void setAntiepidemicTimes(Integer antiepidemicTimes) {
        this.antiepidemicTimes = antiepidemicTimes;
    }

    public Integer getDogCorpseNum() {
        return dogCorpseNum;
    }

    public void setDogCorpseNum(Integer dogCorpseNum) {
        this.dogCorpseNum = dogCorpseNum;
    }

    public Integer getManureNum() {
        return manureNum;
    }

    public void setManureNum(Integer manureNum) {
        this.manureNum = manureNum;
    }

    public Integer getLlqNum() {
        return llqNum;
    }

    public void setLlqNum(Integer llqNum) {
        this.llqNum = llqNum;
    }

    public Integer getQzghTimes() {
        return qzghTimes;
    }

    public void setQzghTimes(Integer qzghTimes) {
        this.qzghTimes = qzghTimes;
    }

    public Integer getXqghTimes() {
        return xqghTimes;
    }

    public void setXqghTimes(Integer xqghTimes) {
        this.xqghTimes = xqghTimes;
    }

    @Override
    public String toString() {
        return "AllNumVO{" +
                "dogLiveNum=" + dogLiveNum +
                ", dogNewNum=" + dogNewNum +
                ", dogCancelNum=" + dogCancelNum +
                ", ownerNewNum=" + ownerNewNum +
                ", antiepidemicTimes=" + antiepidemicTimes +
                ", dogCorpseNum=" + dogCorpseNum +
                ", manureNum=" + manureNum +
                ", llqNum=" + llqNum +
                ", qzghTimes=" + qzghTimes +
                ", xqghTimes=" + xqghTimes +
                '}';
    }
}

/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ManureVO
 * Author:   Administrator
 * Date:     2018/11/13 18:47
 * Description: 粪便处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dogmanager.VO;

import com.entities.TMediaInfo;

import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈粪便处理〉
 *
 * @author Administrator
 * @create 2018/11/13
 * @since 1.0.0
 */
public class ManureVO {
    private Integer id;
    private String mode;    //处理方法ID
    private String processMode;     //处理方法ID(冗余，不要删)
    private String modeName;    //处理方法名
    private String methodDes; //方法说明
    private String period;  //处理周期
    private Date dealTime;  //处理时间
    private String name;    //处理人员
    private String description;
    private List<TMediaInfo> urlList;     //照片
    private List<String> vedioList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getProcessMode() {
        return processMode;
    }

    public void setProcessMode(String processMode) {
        this.processMode = processMode;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public String getMethodDes() {
        return methodDes;
    }

    public void setMethodDes(String methodDes) {
        this.methodDes = methodDes;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TMediaInfo> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<TMediaInfo> urlList) {
        this.urlList = urlList;
    }

    public List<String> getVedioList() {
        return vedioList;
    }

    public void setVedioList(List<String> vedioList) {
        this.vedioList = vedioList;
    }
}

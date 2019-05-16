/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: StrayDogVO
 * Author:   Administrator
 * Date:     2018/10/15 10:28
 * Description: 流浪犬处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dogmanager.VO;

import com.entities.TStrayDog;

/**
 * 〈一句话功能简述〉<br> 
 * 〈流浪犬处理〉
 *
 * @author Administrator
 * @create 2018/10/15
 * @since 1.0.0
 */
public class StrayDogVO extends TStrayDog {
    private String  smallUrl;
    private String  bigUrl;
    private long  userId;
    private String dealName;
    public String getDealName() {
        return dealName;
    }
    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getBigUrl() {
        return bigUrl;
    }

    public void setBigUrl(String bigUrl) {
        this.bigUrl = bigUrl;
    }
}

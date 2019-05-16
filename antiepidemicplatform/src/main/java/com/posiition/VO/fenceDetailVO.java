/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: fenceDetailVO
 * Author:   Administrator
 * Date:     2018/11/19 18:53
 * Description: YL电子围栏vo
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.posiition.VO;

import com.entities.TFenceDetail;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈YL电子围栏vo〉
 *
 * @author Administrator
 * @create 2018/11/19
 * @since 1.0.0
 */
public class fenceDetailVO {
    private Integer id;
    private  String fenceName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFenceName() {
        return fenceName;
    }

    public void setFenceName(String fenceName) {
        this.fenceName = fenceName;
    }

    private List<TFenceDetail> tFenceDetail;

    public List<TFenceDetail> gettFenceDetail() {
        return tFenceDetail;
    }

    public void settFenceDetail(List<TFenceDetail> tFenceDetail) {
        this.tFenceDetail = tFenceDetail;
    }
}

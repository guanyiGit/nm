package com.utils;

import java.io.Serializable;

/**
 * requset请求page对象
 */
public class ReqPage implements Serializable {
    /**
     * 当前页码  默认1
     */
    private Integer pageNum = 1;
    /**
     * 页面大小  默认10
     */
    private Integer pageSize = 10;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

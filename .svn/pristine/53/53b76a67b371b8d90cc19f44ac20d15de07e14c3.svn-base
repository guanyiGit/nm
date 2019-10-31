package com.soholy.cb.utils;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class ReqPage
        implements Serializable {
    @Min(1L)
    @NotNull
    private Integer offset = Integer.valueOf(1);


    @Min(1L)
    @NotNull
    private Integer limit = Integer.valueOf(10);

    private Integer pageNum = Integer.valueOf(1);

    public ReqPage(Integer pageNum, Integer limit) {
        setPageNum(pageNum);
        setLimit(limit);
    }


    public ReqPage() {
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        if ((limit == null) || (limit.intValue() < 1)) {
            this.limit = Integer.valueOf(1);
        } else {
            this.limit = limit;
        }
    }

    public Integer getPageNum() {
        return getOffset();
    }

    public void setPageNum(Integer pageNum) {
        if ((pageNum == null) || (pageNum.intValue() < 1)) {
            this.pageNum = Integer.valueOf(1);
        } else {
            this.pageNum = pageNum;
        }
    }

    public Integer getOffset() {
        return Integer.valueOf((this.pageNum.intValue() - 1) * this.limit.intValue());
    }

    public int getTotalRows(int totalCount) {
        return (int) Math.ceil(totalCount / this.limit.intValue());
    }


    public String toString() {
        StringBuffer sb = new StringBuffer("ReqPage{");
        sb.append("pageNum=").append(this.pageNum);
        sb.append(", limit=").append(this.limit);
        sb.append('}');
        return sb.toString();
    }
}


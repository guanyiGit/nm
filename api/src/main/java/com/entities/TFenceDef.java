package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TFenceDef implements Serializable {


    private Integer id;
    private Integer fence_type;
    private Integer create_by;
    private Integer status;
    private Date start_time;
    private Integer area_id;
    private Date create_date;
    private Date expire_date;
    private Integer org_id;
    private String fence_name;
    private String fence_no;
    private List<TFenceDetail> list;

    public String getFence_no() {
        return fence_no;
    }

    public void setFence_no(String fence_no) {
        this.fence_no = fence_no;
    }

    public List<TFenceDetail> getList() {
        return list;
    }

    public void setList(List<TFenceDetail> list) {
        this.list = list;
    }

    public TFenceDef() {
    }

    public String getFence_name() {
        return fence_name;
    }

    public void setFence_name(String fence_name) {
        this.fence_name = fence_name;
    }

    public TFenceDef(Integer fence_type, Integer create_by, Integer status, Date start_time, Integer area_id, Date create_date, Date expire_date) {
        this.fence_type = fence_type;
        this.create_by = create_by;
        this.status = status;
        this.start_time = start_time;
        this.area_id = area_id;
        this.create_date = create_date;
        this.expire_date = expire_date;
    }

    public Integer getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFence_type() {
        return fence_type;
    }

    public void setFence_type(Integer fence_type) {
        this.fence_type = fence_type;
    }

    public Integer getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Integer create_by) {
        this.create_by = create_by;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Integer getArea_id() {
        return area_id;
    }

    public void setArea_id(Integer area_id) {
        this.area_id = area_id;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(Date expire_date) {
        this.expire_date = expire_date;
    }
}

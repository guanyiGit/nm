package com.entities;

import java.util.Date;
import java.util.List;

public class TPastoralCommittee {
    private Integer id;

    private String name;

    private Integer cooperationId;

    private Integer forageId;

    private String address;

    private String gucNum;

    private Double floorArea;

    private Double useableArea;

    private String description;

    private Integer areaId;

    private Date createDate;

    private Date updateDate;

    private Integer orgId;
    
    private String remarks;
    
    private List<AreaInfo> townList;//所属乡镇
    
    

    public List<AreaInfo> getTownList() {
		return townList;
	}

	public void setTownList(List<AreaInfo> townList) {
		this.townList = townList;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCooperationId() {
        return cooperationId;
    }

    public void setCooperationId(Integer cooperationId) {
        this.cooperationId = cooperationId;
    }

    public Integer getForageId() {
        return forageId;
    }

    public void setForageId(Integer forageId) {
        this.forageId = forageId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getGucNum() {
        return gucNum;
    }

    public void setGucNum(String gucNum) {
        this.gucNum = gucNum == null ? null : gucNum.trim();
    }

    public Double getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Double floorArea) {
        this.floorArea = floorArea;
    }

    public Double getUseableArea() {
        return useableArea;
    }

    public void setUseableArea(Double useableArea) {
        this.useableArea = useableArea;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}
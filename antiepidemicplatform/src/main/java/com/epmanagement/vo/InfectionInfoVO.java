package com.epmanagement.vo;

import java.util.List;

import com.entities.OrgInfo;
import com.entities.TInfectionInfo;
import com.entities.TMediaInfo;

public class InfectionInfoVO extends TInfectionInfo {
	private OrgInfo orgInfo;//一对一关联组织表
	private List<TMediaInfo>imageList;//一对多关联照片
	public OrgInfo getOrgInfo() {
		return orgInfo;
	}
	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}
	public List<TMediaInfo> getImageList() {
		return imageList;
	}
	public void setImageList(List<TMediaInfo> imageList) {
		this.imageList = imageList;
	}
}

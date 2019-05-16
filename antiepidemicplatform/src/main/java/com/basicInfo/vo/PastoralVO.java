package com.basicInfo.vo;

import java.util.List;

import com.entities.TCooperation;
import com.entities.TForageInfo;
import com.entities.TMediaInfo;
import com.entities.TPastoralCommittee;

public class PastoralVO extends TPastoralCommittee {
	
	private TCooperation tCooperation;//一对一关联合作社信息

	private TForageInfo forage;//一对一关联饲草
	
	private  List<TMediaInfo> imageList;//一对多关联照片
	
	
	public List<TMediaInfo> getImageList() {
		return imageList;
	}

	public void setImageList(List<TMediaInfo> imageList) {
		this.imageList = imageList;
	}

	public TCooperation gettCooperation() {
		return tCooperation;
	}

	public void settCooperation(TCooperation tCooperation) {
		this.tCooperation = tCooperation;
	}

	public TForageInfo getForage() {
		return forage;
	}

	public void setForage(TForageInfo forage) {
		this.forage = forage;
	}
	

}

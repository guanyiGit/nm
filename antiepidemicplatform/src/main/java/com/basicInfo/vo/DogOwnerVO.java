package com.basicInfo.vo;

import java.util.List;

import com.dogmanager.VO.DogInfoVO;
import com.entities.AreaInfo;
import com.entities.TDogOwner;
import com.entities.TMediaInfo;
import com.entities.TPastoralCommittee;
import com.entities.TSysDict;

public class DogOwnerVO extends TDogOwner {
	
	private TPastoralCommittee tCommittee;//一对一关联牧委会
	
	private  List<TMediaInfo> imageList;//一对多关联照片
	
	
	private String protectorName;//防疫员
	
	private List<AreaInfo> townList;//所属乡镇
	
	
	
	//private TSysDict nationDic;//一对一关联字典表
	

	public List<AreaInfo> getTownList() {
		return townList;
	}

	public void setTownList(List<AreaInfo> townList) {
		this.townList = townList;
	}

	public String getProtectorName() {
		return protectorName;
	}

	public void setProtectorName(String protectorName) {
		this.protectorName = protectorName;
	}

	public TPastoralCommittee gettCommittee() {
		return tCommittee;
	}

	public void settCommittee(TPastoralCommittee tCommittee) {
		this.tCommittee = tCommittee;
	}

	public List<TMediaInfo> getImageList() {
		return imageList;
	}

	public void setImageList(List<TMediaInfo> imageList) {
		this.imageList = imageList;
	}

//	public TSysDict getNationDic() {
//		return nationDic;
//	}
//
//	public void setNationDic(TSysDict nationDic) {
//		this.nationDic = nationDic;
//	}



	
}

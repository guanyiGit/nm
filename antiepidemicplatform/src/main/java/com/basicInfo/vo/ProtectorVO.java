package com.basicInfo.vo;

import java.util.List;

import com.entities.AreaInfo;
import com.entities.OrgInfo;
import com.entities.TMediaInfo;
import com.entities.TProtector;
import com.entities.UserInfo;

public class ProtectorVO extends TProtector{
	
	//private TSysDict nationDic;//一对一nation
	
	private OrgInfo orgInfo;//一对一关联组织
	
	private UserInfo userInfo;//一对一关联用户

	private List<TMediaInfo>imageList;
	
	private List<AreaInfo> townList;//所属乡镇
	
	public List<AreaInfo> getTownList() {
		return townList;
	}

	public void setTownList(List<AreaInfo> townList) {
		this.townList = townList;
	}

//	public TSysDict getNationDic() {
//		return nationDic;
//	}
//
//	public void setNationDic(TSysDict nationDic) {
//		this.nationDic = nationDic;
//	}

	public List<TMediaInfo> getImageList() {
		return imageList;
	}

	public void setImageList(List<TMediaInfo> imageList) {
		this.imageList = imageList;
	}

	public OrgInfo getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
}

package com.epmanagement.vo;

import com.entities.*;

import java.util.List;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/10 16:20
 * @Version 1.0
 */
public class CorpseDisposalVO extends CorpseDisposal{

	
	private UserInfo user;//一对一关联用户
	
	//private List<MediaRefVO>mediaRefList;//一对一关联媒体关联表
	
	private List<TMediaInfo>imageList;
	
	private TSysDict deathReason;
	
	private TSysDict handleMethod;
	
	private TSysDict deviceDeal;
	
	

	public TSysDict getDeviceDeal() {
		return deviceDeal;
	}

	public void setDeviceDeal(TSysDict deviceDeal) {
		this.deviceDeal = deviceDeal;
	}

	private OrgInfo orgInfo;
	
	
	private String deviceNo;//设备编号
	
	private String committeeName;//村委会
	
	private TDogOwner dogOwner;//一对一关联犬主
	private TDogInfo dog;//一对一关联犬只

	private String town;
	
	private List<AreaInfo> townList;//所属乡镇
	
	
	public List<AreaInfo> getTownList() {
		return townList;
	}

	public void setTownList(List<AreaInfo> townList) {
		this.townList = townList;
	}

	public TDogOwner getDogOwner() {
		return dogOwner;
	}

	public void setDogOwner(TDogOwner dogOwner) {
		this.dogOwner = dogOwner;
	}

	public TDogInfo getDog() {
		return dog;
	}

	public void setDog(TDogInfo dog) {
		this.dog = dog;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getCommitteeName() {
		return committeeName;
	}

	public void setCommitteeName(String committeeName) {
		this.committeeName = committeeName;
	}

	public OrgInfo getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(OrgInfo orgInfo) {
		this.orgInfo = orgInfo;
	}

	public TSysDict getDeathReason() {
		return deathReason;
	}

	public void setDeathReason(TSysDict deathReason) {
		this.deathReason = deathReason;
	}

	public TSysDict getHandleMethod() {
		return handleMethod;
	}

	public void setHandleMethod(TSysDict handleMethod) {
		this.handleMethod = handleMethod;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public List<TMediaInfo> getImageList() {
		return imageList;
	}

	public void setImageList(List<TMediaInfo> imageList) {
		this.imageList = imageList;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
/*public List<MediaRefVO> getMediaRefList() {
		return mediaRefList;
	}

	public void setMediaRefList(List<MediaRefVO> mediaRefList) {
		this.mediaRefList = mediaRefList;
	}
*/


	 
	
}

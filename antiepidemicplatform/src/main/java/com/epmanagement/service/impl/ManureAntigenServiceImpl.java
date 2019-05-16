package com.epmanagement.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basicInfo.dao.TMediaInfoMapper;
import com.basicInfo.dao.TMediaRefMapper;
import com.entities.OrgInfo;
import com.entities.TManureAntigen;
import com.entities.TManureAntigenExample;
import com.entities.TMediaRef;
import com.entities.TMediaRefExample;
import com.entities.TManureAntigenExample.Criteria;
import com.epmanagement.dao.TManureAntigenMapper;
import com.epmanagement.service.ManureAntigenService;
import com.epmanagement.vo.ManureAntigenVO;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.utils.PageUtils;
import com.utils.ShiroUtils;

@Service
public class ManureAntigenServiceImpl implements ManureAntigenService {
	@Autowired
	private TManureAntigenMapper manureAntigenMapper;
	@Autowired
	private OrgInfoDao orgDao;
	@Autowired
	private TMediaRefMapper refMapper;
	@Autowired
	private TMediaInfoMapper mediaInfoMapper;
	/**
	 * 查找犬粪抗原列表
	 * @param index 索引
	 * @param pageSize 页面大小
	 * @return
	 */
	@Override
	public PageUtils findManureAntigenList(Integer index, Integer pageSize,Integer orgId1,Date startDate,Date endDate) {
		//初始化组织信息
		List<Integer>orgList=new ArrayList<>();
		
		//初始化map
		HashMap<String, Object>map=new HashMap<String, Object>();
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			map.put("index", index);
			map.put("pageSize", pageSize);
		//当前登录用户的相关信息
		UserDO user = ShiroUtils.getUser();
		//用户角色类型
		Integer type = user.getRoles().get(0).getType();
		//用户部门id
		Integer orgId = user.getDeptId().intValue();
		//州级管理员州级办事人员
		if(type==6||type==10) 
		{
				//前端选择全部或者没选
				if(orgId1==null || orgId1==-1) 
				{
					//查出本部门和下级所属县级组织的id
					List<OrgInfo> orgInfo = manureAntigenMapper.findOrgList(orgId);
						for(OrgInfo o:orgInfo) {
							orgList.add(o.getId());
						}
				}else 
				{
					//前端选择组织
					orgList.add(orgId1);
				}
				
		}
		//当前登录用户为县级管理员
		else
		{
			orgList.add(orgId);
			
		}
		//查看犬粪抗原总记录数
		TManureAntigenExample example=new TManureAntigenExample();
			Criteria criteria = example.createCriteria();
				criteria.andOrgIdIn(orgList);
			if(startDate !=null) {
				criteria.andTestDateGreaterThan(startDate);
			}
			if(endDate !=null) {
				criteria.andTestDateLessThan(endDate);
			}
			
		Integer total=manureAntigenMapper.countByExample(example);
			map.put("orgList",orgList);
		List<ManureAntigenVO>manureAntigenList=manureAntigenMapper.findManureAntigenList(map);
		return new PageUtils(manureAntigenList, total);
	}
	
	/**
	 * 查看本州和下属县级组织
	 */
	@Override
	public List<OrgInfo> findOrgList() {
		//当前登录人的组织id
		Integer orgId = ShiroUtils.getUser().getDeptId().intValue();
		return manureAntigenMapper.findOrgList(orgId);
	}

	/**
	 * 新增犬粪抗原
	 */
	@Override
	public Integer save(TManureAntigen manureAntigen) {
		UserDO user = ShiroUtils.getUser();
			manureAntigen.setCreatedBy(user.getUserId().intValue());
			manureAntigen.setOrgId(user.getDeptId().intValue());
			manureAntigen.setTestDate(new Date());
			manureAntigenMapper.insertSelective(manureAntigen);
		return manureAntigen.getId();
	}

	/**
	 * 修改
	 */
	@Override
	public Integer update(TManureAntigen manureAntigen) {
		
		return manureAntigenMapper.updateByPrimaryKeySelective(manureAntigen);
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public Integer remove(Integer id) {
		TMediaRefExample example=new TMediaRefExample();
		com.entities.TMediaRefExample.Criteria criteria = example.createCriteria();
			criteria.andRefIdEqualTo(id);
			criteria.andTypeEqualTo(14);
	//根据犬粪抗原id查找媒体id
	List<TMediaRef> list = refMapper.selectByExample(example);
		for(TMediaRef m:list) {
			Integer mediaId = m.getMediaId();
				//删除媒体表
				mediaInfoMapper.deleteByPrimaryKey(mediaId);
		}
		//删除媒体关联表
		refMapper.deleteByExample(example);
		//删除犬主信息
	return manureAntigenMapper.deleteByPrimaryKey(id);
	}

}

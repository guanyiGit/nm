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
import com.entities.TInfectionInfo;
import com.entities.TInfectionInfoExample;
import com.entities.TMediaRef;
import com.entities.TMediaRefExample;
import com.entities.TInfectionInfoExample.Criteria;
import com.epmanagement.dao.TInfectionInfoMapper;
import com.epmanagement.dao.TManureAntigenMapper;
import com.epmanagement.service.InfectionInfoService;
import com.epmanagement.vo.InfectionInfoVO;
import com.orgmanagement.domain.UserDO;
import com.utils.PageUtils;
import com.utils.ShiroUtils;
@Service
public class InfectionInfoServiceImpl implements InfectionInfoService {
	@Autowired
	private TInfectionInfoMapper infectionInfoMapper;
	@Autowired
	private TManureAntigenMapper manureAntigenMapper;
	
	@Autowired
	private TMediaRefMapper refMapper;
	@Autowired
	private TMediaInfoMapper mediaInfoMapper;
	//牛羊感染病变列表
	@Override
	public PageUtils findInfectionInfoList(Integer index, Integer pageSize, Integer orgId1, Date startDate,
			Date endDate) {
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
				//前端选择全部
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
		//查看牛羊免疫抗体总记录数
		TInfectionInfoExample example=new TInfectionInfoExample();
			 Criteria criteria = example.createCriteria();
				criteria.andOrgIdIn(orgList);
			if(startDate !=null) {
				criteria.andDetectionDateGreaterThan(startDate);
			}
			if(endDate !=null) {
				criteria.andDetectionDateLessThan(endDate);
			}
			
		Integer total=infectionInfoMapper.countByExample(example);
			map.put("orgList",orgList);
		List<InfectionInfoVO>list=infectionInfoMapper.findInfectionInfoList(map);
		return new PageUtils(list, total);
	}

	@Override
	public int update(TInfectionInfo tInfectionInfo) {
		
		return infectionInfoMapper.updateByPrimaryKeySelective(tInfectionInfo);
		
	}

	@Override
	public int save(TInfectionInfo tInfectionInfo) {
		UserDO user = ShiroUtils.getUser();
		tInfectionInfo.setCreatedBy(user.getUserId().intValue());
		tInfectionInfo.setOrgId(user.getDeptId().intValue());
		tInfectionInfo.setDetectionDate(new Date());
		infectionInfoMapper.insertSelective(tInfectionInfo);
	return tInfectionInfo.getId();
		
	}

	@Override
	@Transactional
	public int delete(Integer id) {
		TMediaRefExample example=new TMediaRefExample();
		com.entities.TMediaRefExample.Criteria criteria = example.createCriteria();
			criteria.andRefIdEqualTo(id);
			criteria.andTypeEqualTo(17);
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
	return infectionInfoMapper.deleteByPrimaryKey(id);
	}

}

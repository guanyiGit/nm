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
import com.entities.TDogAnatomy;
import com.entities.TDogAnatomyExample;
import com.entities.TMediaRef;
import com.entities.TMediaRefExample;
import com.epmanagement.dao.TDogAnatomyMapper;
import com.epmanagement.dao.TManureAntigenMapper;
import com.epmanagement.service.DogAnatomyService;
import com.epmanagement.vo.DogAnatomyVO;
import com.orgmanagement.domain.UserDO;
import com.utils.PageUtils;
import com.utils.ShiroUtils;
@Service
public class DogAnatomyServiceImpl implements DogAnatomyService {
	@Autowired
	private TDogAnatomyMapper tDogAnatomyMapper;
	@Autowired
	private TManureAntigenMapper manureAntigenMapper;
	@Autowired
	private TMediaRefMapper refMapper;
	@Autowired
	private TMediaInfoMapper mediaInfoMapper;
	/**
	 * 查找犬粪抗原列表
	 * @param index 索引
	 * @param pageSize 页面大小
	 * @param orgId1 州级管理员从下拉框中选择的组织id 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	@Override
	public PageUtils findDogAnatomyList(Integer index, Integer pageSize, Integer orgId1, Date startDate, Date endDate) {
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
				//查看犬粪抗原总记录数
				TDogAnatomyExample example=new TDogAnatomyExample();
					com.entities.TDogAnatomyExample.Criteria criteria = example.createCriteria();
						criteria.andOrgIdIn(orgList);
					if(startDate !=null) {
						criteria.andTestDateGreaterThan(startDate);
					}
					if(endDate !=null) {
						criteria.andTestDateLessThan(endDate);
					}
					
				Integer total=tDogAnatomyMapper.countByExample(example);
					map.put("orgList",orgList);
				List<DogAnatomyVO>list=tDogAnatomyMapper.findDogAnatomyList(map);
				return new PageUtils(list, total);
	}
	/**
	 * 新增
	 */
	@Override
	public Integer save(TDogAnatomy dogAnatomy) {
		UserDO user = ShiroUtils.getUser();
		dogAnatomy.setCreatedBy(user.getUserId().intValue());
		dogAnatomy.setOrgId(user.getDeptId().intValue());
		dogAnatomy.setTestDate(new Date());
		tDogAnatomyMapper.insertSelective(dogAnatomy);
	return dogAnatomy.getId();
	}
	/**
	 * 删除
	 */
	@Override
	@Transactional
	public int remove(Integer id) {
		TMediaRefExample example=new TMediaRefExample();
		com.entities.TMediaRefExample.Criteria criteria = example.createCriteria();
			criteria.andRefIdEqualTo(id);
			criteria.andTypeEqualTo(15);
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
	return tDogAnatomyMapper.deleteByPrimaryKey(id);
	}

}

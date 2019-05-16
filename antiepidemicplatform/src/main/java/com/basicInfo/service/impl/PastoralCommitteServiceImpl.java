package com.basicInfo.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basicInfo.dao.TPastoralCommitteeMapper;
import com.basicInfo.service.PastoralCommitteeService;
import com.basicInfo.vo.PastoralVO;
import com.entities.AreaInfo;
import com.entities.OrgInfo;
import com.entities.TPastoralCommittee;
import com.entities.TPastoralCommitteeExample;
import com.entities.TPastoralCommitteeExample.Criteria;
import com.orgmanagement.dao.AreaInfoDao;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.utils.OrgUtils;
import com.utils.PageUtils;
import com.utils.ShiroUtils;
import com.utils.areaUtils;

@Service
public class PastoralCommitteServiceImpl implements PastoralCommitteeService{

	@Autowired 
	private TPastoralCommitteeMapper committeMapper;
	@Autowired
	private OrgInfoDao orgDao;
	@Autowired
	private AreaInfoDao areaDao;
	//新增牧委会
	@Transactional
	@Override
	public int addPastoralCommittee(TPastoralCommittee committee) {
		//获取用户相关信息
		UserDO user = ShiroUtils.getUser();
		committee.setCreateDate(new Date());
		
		committee.setAreaId(user.getAreaId().intValue());
		committee.setOrgId(user.getDeptId().intValue());
		
		return committeMapper.insertSelective(committee);
	}
	
	//删除牧委会
	@Override
	@Transactional
	public int deletePastoralCommittee(Integer id) {
		return committeMapper.deleteByPrimaryKey(id);
	}

	//修改牧委会
	@Override
	@Transactional
	public int updatePastoralCommittee(TPastoralCommittee committee) {
		committee.setUpdateDate(new Date());
		return committeMapper.updateByPrimaryKeySelective(committee);
	}

	//查看牧委会详情
	@Override
	public TPastoralCommittee findPastoralCommitteeDetail(Integer id) {
		return committeMapper.selectByPrimaryKey(id);
	}
	//查看牧委会列表
	@Override
	public PageUtils findPastoralCommitteeList(Integer index, Integer pageSize,String searchKey) throws UnsupportedEncodingException {
		//获取用户相关信息
				UserDO user = ShiroUtils.getUser();
				Integer orgId=user.getDeptId().intValue();
		//查出所有组织信息
		List<OrgInfo> list = orgDao.orgList();
		List<Integer>orgList=new ArrayList<>();
		//求出当前登录人下面所有乡级组织信息
			orgList= OrgUtils.orgReverse(list, orgId, orgList);
		Map<String, Object>map=new HashMap<>();
			//求出总记录数
		TPastoralCommitteeExample example=new TPastoralCommitteeExample();
				Criteria criteria = example.createCriteria();
			criteria.andOrgIdIn(orgList);
			if(searchKey !=null && !("").equals(searchKey)) {
				criteria.andNameLike("%"+searchKey+"%");
			}
			int total = committeMapper.countByExample(example);
				map.put("orgList", orgList);
				map.put("index", index);
				map.put("pageSize", pageSize);
				map.put("searchKey", searchKey);
			List<TPastoralCommittee> committeeList = committeMapper.findPastoralCommitteeList(map);
					for(TPastoralCommittee t:committeeList) {
						Integer areaId = t.getAreaId();
						List<AreaInfo> lists=new ArrayList<>();
						lists=areaUtils.areaR(areaDao.getList(), areaId, lists);
						t.setTownList(lists);
					}
			return new PageUtils(committeeList, total);
	}

	/**
	 * 根据区域id查找下属牧委会
	 */
	@Override
	public List<TPastoralCommittee> findPastoralByAreaId() {
		Integer areaId = ShiroUtils.getUser().getAreaId().intValue();
		TPastoralCommitteeExample example=new TPastoralCommitteeExample();
		Criteria criteria = example.createCriteria();
			criteria.andAreaIdEqualTo(areaId);
			
			return committeMapper.selectByExample(example);
	}

}

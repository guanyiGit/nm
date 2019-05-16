package com.basicInfo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basicInfo.dao.TPadInfoMapper;
import com.basicInfo.service.PadService;
import com.basicInfo.vo.PadVO;
import com.entities.OrgInfo;
import com.entities.TPadInfo;
import com.entities.TPadInfoExample;
import com.entities.TPadInfoExample.Criteria;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.utils.PageUtils;
import com.utils.ShiroUtils;
import com.utils.OrgUtils;

@Service
public class PadServiceImpl implements PadService {

	@Autowired
	private TPadInfoMapper padMapper;
	@Autowired
	private OrgInfoDao orgDao;
	@Override
	public int addPad(TPadInfo pad) {
		UserDO user = ShiroUtils.getUser();
		Integer areaId=user.getAreaId().intValue();
		Integer orgId = user.getDeptId().intValue();
		pad.setAreaId(areaId);
		pad.setOrgId(orgId);
		pad.setCreateDate(new Date());
		return padMapper.insertSelective(pad);
	}

	@Override
	public PageUtils findPadList(Integer index, Integer pageSize, Integer orgId,String searchKey) {
		  orgId = ShiroUtils.getUser().getDeptId().intValue();
		//查出所有组织信息
		List<OrgInfo> list = orgDao.orgList();
		List<Integer> orgList=new ArrayList<>();
		orgList= OrgUtils.orgReverse(list, orgId, orgList);
		
		TPadInfoExample example=new TPadInfoExample();
			Criteria criteria = example.createCriteria();
				criteria.andOrgIdIn(orgList);
				if(searchKey !=null && !("").equals(searchKey)) {
					criteria.andPadNoEqualTo(searchKey);
				}
				
		//求出所有pad记录数
		int total=padMapper.countByExample(example);
		
		HashMap<String, Object>map=new HashMap<>();
			map.put("orgList", orgList);
			map.put("index", index);
			map.put("pageSize", pageSize);
			map.put("searchKey", searchKey);
		//求出所有pad信息
		List<PadVO> padList = padMapper.findPadList(map);
	return new PageUtils(padList, total);
	}

	@Override
	public TPadInfo findPadDetail(Integer id) {
		return padMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deletePad(Integer id) {
		return padMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updatePad(TPadInfo padInfo) {
		padInfo.setUpdateDate(new Date());
		return padMapper.updateByPrimaryKeySelective(padInfo);
	}

}

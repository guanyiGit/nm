package com.basicInfo.service.impl;

import com.basicInfo.dao.TCooperationMapper;
import com.basicInfo.service.CooperationService;
import com.entities.OrgInfo;
import com.entities.TCooperation;
import com.entities.TCooperationExample;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.utils.OrgUtils;
import com.utils.PageUtils;
import com.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class CooperationServiceImpl implements CooperationService {

	@Autowired
	private TCooperationMapper tCooperationMapper;
	
	@Autowired
	private OrgInfoDao orgDao;
	//添加合作社
	@Override
	@Transactional
	public int addCooperation(TCooperation cooperation) {
		UserDO user = ShiroUtils.getUser();
		//待完善需要调用用户的接口
		cooperation.setAreaId(user.getAreaId().intValue());
		cooperation.setOrgId(user.getDeptId().intValue());
		cooperation.setCreateDate(new Date());
		return tCooperationMapper.insertSelective(cooperation);
	}

	
	//合作社列表
	@Override
	public PageUtils findCooperationList(Integer index, Integer pageSize, Integer orgId,String searchKey) throws UnsupportedEncodingException {
		UserDO user = ShiroUtils.getUser();
		orgId=user.getDeptId().intValue();
		//查出所有组织信息
			List<OrgInfo> list = orgDao.orgList();
				List<Integer>orgList=new ArrayList<>();
			//求出当前登录人下面所有乡级组织信息
				orgList= OrgUtils.orgReverse(list, orgId, orgList);
				Map<String, Object>map=new HashMap<>();
					//求出总记录数
				TCooperationExample example=new TCooperationExample();
						com.entities.TCooperationExample.Criteria criteria = example.createCriteria();
							criteria.andOrgIdIn(orgList);
							if(searchKey !=null && !("").equals(searchKey)) {
								criteria.andNameLike("%"+searchKey+"%");
							}
					int total = tCooperationMapper.countByExample(example);
					map.put("orgList", orgList);
					map.put("index", index);
					map.put("pageSize", pageSize);
					map.put("searchKey", searchKey);
					List<TCooperation> dogOwnerList = tCooperationMapper.findCooperationList(map);
				return new PageUtils(dogOwnerList, total);
	}

	/**
	 * 合作社详情
	 */
	@Override
	public TCooperation findCooperationDetail(Integer id) {
		return tCooperationMapper.selectByPrimaryKey(id);
	}

	//删除合作社
	@Override
	@Transactional
	public int deleteCooperation(Integer id) {
		return tCooperationMapper.deleteByPrimaryKey(id);
	}

	//修改合作社
	@Override
	@Transactional
	public int updateCooperation(TCooperation cooperation) {
		cooperation.setUpdateDate(new Date());
		return  tCooperationMapper.updateByPrimaryKeySelective(cooperation);
	}

	//根据区域id查找下面合作社
	@Override
	public List<TCooperation> findCooperationByAreaId(Integer areaId) {
		 areaId=ShiroUtils.getUser().getAreaId().intValue();
			TCooperationExample example=new TCooperationExample();
			com.entities.TCooperationExample.Criteria criteria = example.createCriteria();
			 criteria.andAreaIdEqualTo(areaId);
			
		return tCooperationMapper.selectByExample(example);
	}
	
	

}

package com.basicInfo.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orgmanagement.dao.UserInfoDao;
import com.orgmanagement.dao.UserRefRoleDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basicInfo.dao.TVeterinarianMapper;
import com.basicInfo.service.VeterinarianService;
import com.basicInfo.vo.VeterinarianVO;
import com.entities.OrgInfo;
import com.entities.TVeterinarian;
import com.entities.TVeterinarianExample;
import com.entities.TVeterinarianExample.Criteria;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.domain.UserRoleDO;
import com.orgmanagement.service.UserInfoService;
import com.utils.MD5Utils;
import com.utils.PageUtils;
import com.utils.ShiroUtils;
import com.utils.OrgUtils;

@Service
public class VeterinarianServiceImpl implements VeterinarianService{
	@Autowired
	private TVeterinarianMapper tVeterinarianMapper;
	@Autowired
	private OrgInfoDao orgDao;
	@Autowired
	UserInfoDao userInfoDao;
	
	@Autowired
	UserRefRoleDao userRoleMapper;
	//新增兽医
	@Override
	@Transactional
	public int addVeterinarian(TVeterinarian veterinarian){
		//登录获取用户相关信息
		UserDO user = ShiroUtils.getUser();
		Integer areaId = user.getAreaId().intValue();
		Integer userIdLogin=user.getUserId().intValue();
		Integer orgId=user.getDeptId().intValue();
		
		//新建用户对象
		UserDO u2=new UserDO();
			u2.setAreaId(user.getAreaId());
			u2.setDeptId(user.getDeptId());
			u2.setPassword(MD5Utils.encrypt(veterinarian.getPhoneNum(), (int)((Math.random()*9+1)*100000)+""));
			u2.setMobile(veterinarian.getPhoneNum());
			u2.setUsername(veterinarian.getName());
			u2.setName(veterinarian.getPhoneNum());
			u2.setBirth(veterinarian.getBirthDay());
			u2.setStatus(1);
			u2.setGmtCreate(new Date());
			//插入用户
		Integer a=userInfoDao.selecAccount(veterinarian.getPhoneNum());
		if (a!=0){
			return -2;
		}
		userInfoDao.save(u2); 
			//返回自增主键
			Long userId = u2.getUserId();
			
			Long roleId=(long)7;//兽医角色id
			
			
			//向角色兽医表中插入数据
			List<UserRoleDO> list=new ArrayList<UserRoleDO>();
				UserRoleDO u=new UserRoleDO();
					u.setRoleId(roleId);
					u.setUserId(userId);
				list.add(u);
				//插入
				userRoleMapper.batchSave(list);
			
			veterinarian.setAreaId(areaId);
			veterinarian.setCreateBy(userIdLogin);
			veterinarian.setOrgId(orgId);
			veterinarian.setUserId(userId.intValue());
			veterinarian.setCreateDate(new Date());
			
		//根据身份证号判断
		String cardNum = veterinarian.getCardNum();
		TVeterinarianExample example=new TVeterinarianExample();
			Criteria criteria = example.createCriteria();
				criteria.andCardNumEqualTo(cardNum.trim());
				int i=tVeterinarianMapper.countByExample(example);
				if(i>0) {
					return -2;
				}
				
		return tVeterinarianMapper.insertSelective(veterinarian);
	}
	
	//删除兽医
	@Override
	@Transactional
	public int deleteVeterinarian(Integer id) {
		return tVeterinarianMapper.deleteByPrimaryKey(id);
	}
	
	//修改兽医
	@Override
	public int updateVeterinarian(TVeterinarian veterinarian) {
		veterinarian.setUpdateDate(new Date());
		return tVeterinarianMapper.updateByPrimaryKeySelective(veterinarian);
	}
	
	//兽医列表
	@Override
	public PageUtils findVeterinarianList(Integer index, Integer pageSize, Integer orgId,String searchKey) throws UnsupportedEncodingException {
		orgId=ShiroUtils.getUser().getDeptId().intValue();
		//查出所有组织信息
				List<OrgInfo> list = orgDao.orgList();
				List<Integer>orgList=new ArrayList<>();
				//求出当前登录人下面所有乡级组织信息
					orgList= OrgUtils.orgReverse(list, orgId, orgList);
				Map<String, Object>map=new HashMap<>();
					//求出总记录数
					TVeterinarianExample example=new TVeterinarianExample();
						com.entities.TVeterinarianExample.Criteria criteria = example.createCriteria();
							criteria.andOrgIdIn(orgList);
							if(searchKey !=null && !("").equals(searchKey)) {
								criteria.andNameLike("%"+searchKey+"%");
							}
					int total = tVeterinarianMapper.countByExample(example);
					map.put("orgList", orgList);
					map.put("index", index);
					map.put("pageSize", pageSize);
					map.put("searchKey", searchKey);
					List<VeterinarianVO> veterinarianList = tVeterinarianMapper.findVeterinarianList(map);
				return new PageUtils(veterinarianList, total);
	}

	//兽医详情
	@Override
	public VeterinarianVO findVeterinarianDetail(Integer id) {

		return tVeterinarianMapper.findVeterinarianDetail(id);
	}

}

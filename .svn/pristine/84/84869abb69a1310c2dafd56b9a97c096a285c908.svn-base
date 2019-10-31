package com.basicInfo.service.impl;

import com.basicInfo.dao.TDogOwnerMapper;
import com.basicInfo.dao.TMediaInfoMapper;
import com.basicInfo.dao.TMediaRefMapper;
import com.basicInfo.service.DogOwnerService;
import com.basicInfo.vo.DogOwnerVO;
import com.entities.*;
import com.entities.TDogOwnerExample.Criteria;
import com.entities.TMediaRef;
import com.entities.TMediaRefExample;
import com.orgmanagement.dao.AreaInfoDao;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.utils.OrgUtils;
import com.utils.PageUtils;
import com.utils.ShiroUtils;
import com.utils.areaUtils;

import com.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class DogOwnerServiceImpl implements DogOwnerService{
	
	@Autowired
	private TDogOwnerMapper dogOwnerMapper;

	@Autowired
	private OrgInfoDao orgInfoDao;
	
	@Autowired
	private TMediaInfoMapper mediaInfoMapper;
	
	@Autowired
	private TMediaRefMapper refMapper;
	@Autowired
	private AreaInfoDao areaDao;
	//新增犬主信息
	@Override
	@Transactional
	public int insertSelective(TDogOwner record) {
		//登录获取用户相关信息
		UserDO user = ShiroUtils.getUser();
			record.setAreaId(user.getAreaId().intValue());
			record.setOperatorId(user.getUserId().intValue());
			record.setOrgId(user.getDeptId().intValue());
			record.setCreateDate(new Date());
			
			String cardNum = record.getCardNum();
			TDogOwnerExample example=new TDogOwnerExample();
					Criteria criteria = example.createCriteria();
					criteria.andCardNumEqualTo(cardNum.trim());
			int i = dogOwnerMapper.countByExample(example);
			if(i>0) {
				return -2;
			}
			//插入
			dogOwnerMapper.insertSelective(record);
			//返回主键id
			Integer id = record.getId();
		return id;
	}

	//查看所有犬主信息
	@Override
	public PageUtils findDogOwnerList(Integer index,Integer pageSize,String searchKey,Integer orgId2,Integer userId2) throws UnsupportedEncodingException {
		 HashMap<String, Object> map = new HashMap<>();
	        map.put("searchKey",searchKey);
	        UserDO user = ShiroUtils.getUser();
	        int type=user.getRoles().get(0).getType();
	        if(type==2){
	            map.put("userId",user.getUserId().intValue());//
	        }else{
	            List<OrgInfo> orgList=orgInfoDao.orgList();
	            List<Integer>res=new ArrayList<>();
	            if(orgId2==null){
	                res= OrgUtils.orgReverse(orgList, user.getDeptId().intValue(), res);
	            }else {
	                res= OrgUtils.orgReverse(orgList, orgId2, res);
	            }
	            map.put("orgList",res);//组织集合
	        }
	        if (userId2!=null && userId2!=0){
	            map.put("userId",userId2);//
	        }
		
			//求出总记录数
			int total = dogOwnerMapper.findDogOwnerCount(map);
			map.put("index", index);
			map.put("pageSize", pageSize);
			Object typee = ShiroUtils.getSubjct().getSession().getAttribute("type");
			map.put("type", typee);
			List<DogOwnerVO> dogOwnerList = dogOwnerMapper.findDogOwnerList(map);
				for(DogOwnerVO d:dogOwnerList) {
					List<AreaInfo>lists=new ArrayList<AreaInfo>();
					lists=areaUtils.areaR(areaDao.getList(), d.getAreaId(), lists);
					d.setTownList(lists);
				}
			return new PageUtils(dogOwnerList, total);
			
			
	}

	/**
	 * 犬主详情
	 * @param  id 兽医id
	 */
	@Override
	public DogOwnerVO findDogOwnerDetail(Integer id) {
		Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
		return dogOwnerMapper.findDogOwnerDetail(id,type);
	}

	/**
	 * 删除犬主
	 */
	@Override
	@Transactional
	public int deleteDogOwner(Integer id) {
		TMediaRefExample example=new TMediaRefExample();
			com.entities.TMediaRefExample.Criteria criteria = example.createCriteria();
				criteria.andRefIdEqualTo(id);
				criteria.andTypeEqualTo(8);
		//根据犬主id查找媒体id
		List<TMediaRef> list = refMapper.selectByExample(example);
			for(TMediaRef m:list) {
				Integer mediaId = m.getMediaId();
					//删除媒体表
					mediaInfoMapper.deleteByPrimaryKey(mediaId);
			}
			//删除媒体关联表
			refMapper.deleteByExample(example);
			//删除犬主信息
		return dogOwnerMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 修改犬主
	 */
	@Override
	public int updateDogOwner(TDogOwner dogOwner) {
			dogOwner.setUpdateDate(new Date());
		return dogOwnerMapper.updateByPrimaryKeySelective(dogOwner);
	}

	
	/**
	 * 根据身份证或者电话号码进行唯一性判断
	 * @param cardNum:犬主身份证号
	 * @param phoneNum:犬主电话 号码
	 */
	@Override
	public int judugeExit(String cardNum, String phoneNum) {
		
		TDogOwnerExample example=new TDogOwnerExample();
			Criteria criteria = example.createCriteria();
			int count=0;
		if(cardNum !=null) {
				criteria.andCardNumEqualTo(cardNum);
			count=dogOwnerMapper.countByExample(example);
		}else if(phoneNum !=null){
				criteria.andPhoneNumEqualTo(phoneNum);
			count=dogOwnerMapper.countByExample(example);
		}else {
			count=0;
		}
		
		return count;
	}

	/**
	 * 修改时根据身份证或者电话号码进行唯一性判断
	 * @param id:主键id
	 * @param cardNum:犬主身份证号
	 * @param phoneNum:犬主电话 号码
	 */
	@Override
	public int judugeUpdateExit(Integer id, String cardNum, String phoneNum) {
		//根据主键id查找犬主信息
 		TDogOwner owner = dogOwnerMapper.selectByPrimaryKey(id);
			
			String cardNum2 = owner.getCardNum();
			String phoneNum2 = owner.getPhoneNum();
			int i =0;
			TDogOwnerExample example=new TDogOwnerExample();
				Criteria criteria = example.createCriteria();
			//判断身份证号码是否更改
			if(cardNum !=null && !cardNum.equals(cardNum2)) {
					criteria.andCardNumEqualTo(cardNum);
				//如果更改,如果i>0,则身份证号和其他重复
				 i = dogOwnerMapper.countByExample(example);
			}else if(phoneNum !=null &&!phoneNum.equals(phoneNum2)) {
					criteria.andPhoneNumEqualTo(phoneNum);
					//如果更改,如果i>0,则电话号和其他重复
					 i = dogOwnerMapper.countByExample(example);	
			}else {
				i=0;
			}
		return i;
	}
	

	@Override
	public R wxjudugeUpdateExit(Integer id, String cardNum, String phoneNum) {
		//根据主键id查找犬主信息
		TDogOwner owner = dogOwnerMapper.selectByPrimaryKey(id);
		String cardNum2 = owner.getCardNum();
		String phoneNum2 = owner.getPhoneNum();
		int i =0;
		TDogOwnerExample example=new TDogOwnerExample();
		Criteria criteria = example.createCriteria();
		//判断身份证号码是否更改
		if(cardNum !=null && !cardNum.equals(cardNum2)) {
			criteria.andCardNumEqualTo(cardNum);
			//如果更改,如果i>0,则身份证号和其他重复
			i = dogOwnerMapper.countByExample(example);
			if (i>0){
				return R.error(1,"证件号码重复");
			}
		}else if(phoneNum !=null &&!phoneNum.equals(phoneNum2)) {
			criteria.andPhoneNumEqualTo(phoneNum);
			//如果更改,如果i>0,则电话号和其他重复
			i = dogOwnerMapper.countByExample(example);
			if (i>0){
				return R.error(2,"电话号码重复");
			}
		}
		return R.ok();
	}
	
}

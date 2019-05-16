package com.basicInfo.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basicInfo.dao.TForageInfoMapper;
import com.basicInfo.service.ForageService;
import com.entities.TForageInfo;
import com.entities.TForageInfoExample;
import com.entities.TForageInfoExample.Criteria;
import com.utils.PageUtils;

@Service
public class ForageServiceImpl implements ForageService {
	
	@Autowired
	private TForageInfoMapper forageMapper;
	/**
	 * 新增饲草
	 * @param forage
	 * @return
	 */
	@Override
	public int addForage(TForageInfo forage) {
		forage.setCreateDate(new Date());
		return forageMapper.insertSelective(forage);
	}

	/**
	 * 饲草列表
	 * @param  index:索引
	 * 		   pageSize:页面大小
	 * 		   orgId:当前登录人部门id
	 * @throws UnsupportedEncodingException 
	 */
	@Override
	public PageUtils findForageList(Integer index, Integer pageSize,String searchKey) throws UnsupportedEncodingException {
		TForageInfoExample example=new TForageInfoExample();
			Criteria criteria = example.createCriteria();
			if(searchKey !=null && !("").equals(searchKey)) {
				criteria.andNameLike("%"+searchKey+"%");
			}
		//统计饲草总记录数
		int total = forageMapper.countByExample(example);
		Map<String, Object>map=new HashMap<>();
			map.put("index", index);
			map.put("pageSize", pageSize);
			map.put("searchKey", searchKey);
		//统计饲料列表	
		List<TForageInfo>list=forageMapper.selectForageList(map);
		return new PageUtils(list, total);
	}

	/**
	 * 饲草详情
	 * @param  id 兽医id
	 */
	@Override
	public TForageInfo findForageDetail(Integer id) {
		return forageMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除饲草
	 */
	@Override
	public int deleteForage(Integer id) {
		
		return forageMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 修改饲草
	 */
	@Override
	public int updateForage(TForageInfo forageInfo) {
		return forageMapper.updateByPrimaryKeySelective(forageInfo);
	}

}

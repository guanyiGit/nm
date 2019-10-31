package com.basicInfo.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basicInfo.dao.TDrugInfoMapper;
import com.basicInfo.service.DrugService;
import com.entities.TDrugInfo;
import com.entities.TDrugInfoExample;
import com.entities.TDrugInfoExample.Criteria;
import com.utils.PageUtils;

@Service
public class DrugServiceImpl implements DrugService{

	@Autowired
	private TDrugInfoMapper drugMapper;
	
	
	
	@Override
	public int addDrug(TDrugInfo drug) {
		drug.setCreateDate(new Date());
		return drugMapper.insertSelective(drug);
	}

	@Override
	public PageUtils findDrugList(Integer index, Integer pageSize, Integer type,String searchKey) throws UnsupportedEncodingException {
		
		TDrugInfoExample example=new TDrugInfoExample();
			Criteria criteria = example.createCriteria();
				criteria.andTypeEqualTo(type);
				if(searchKey !=null && !("").equals(searchKey)) {
					criteria.andDrugNameLike("%"+searchKey+"%");
				}
				//统计药品总记录数
				int total=drugMapper.countByExample(example);
				HashMap<String, Object>map=new HashMap<String,Object>();
					map.put("index", index);
					map.put("pageSize", pageSize);
					map.put("type", type);
					map.put("searchKey", searchKey);
				//统计药品列表
				List<TDrugInfo>list=drugMapper.findDrugList(map);
		return new PageUtils(list, total);
	}

	@Override
	public TDrugInfo findDrugDetail(Integer id) {
		return drugMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteDrug(Integer id) {
		return drugMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateDrug(TDrugInfo drugInfo) {
		drugInfo.setUpdateDate(new Date());
		return drugMapper.updateByPrimaryKeySelective(drugInfo);
	}

	@Override
	public List<TDrugInfo> initDrugSelect(HashMap<String, Object> map) {
		return drugMapper.initDrugSelect(map);
	}

	



	
}

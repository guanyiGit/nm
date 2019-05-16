package com.epmanagement.dao;

import com.entities.ManureDisposal;
import com.epmanagement.vo.ManureDisposalVO;
import com.epmanagement.vo.MediaUrl;
import com.epmanagement.vo.SelectVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-27 18:21:05
 */
@Mapper
public interface ManureDisposalDao {

	ManureDisposalVO get(Integer id);
	ManureDisposalVO getI18N(Map<String,Object> map);

	List<MediaUrl> getMediaUrlById(Integer id);
	
	List<Map<String,Object>> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);

	int save(ManureDisposal manureDisposal);
	
	int update(ManureDisposal manureDisposal);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

// 根据title查询
	Map<String,String> selectByTitle(String title);

	//初始化下拉框
	List<SelectVO> getDictData(Map<String,Object> map);
}

package com.epmanagement.service;

import com.entities.Antiepidemic;
import com.epmanagement.vo.AntiepidemicVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-27 14:33:11
 */
public interface AntiepidemicService {
	
	AntiepidemicVO get(Integer id);
	
	List<AntiepidemicVO> pagelist(Map<String, Object> map);

	List<Map<String,Object>> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(Antiepidemic antiepidemic);
	
	int update(Antiepidemic antiepidemic);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	//	根据溯源id或防疫药品查询
	Map<String,String> selectBySidOrDrugName(String str);

	List<HashMap<String,Object>> findDrug(Integer type);
}

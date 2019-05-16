package com.epmanagement.service;

import com.entities.ManureDisposal;
import com.epmanagement.vo.ManureDisposalVO;
import com.epmanagement.vo.MediaUrl;
import com.epmanagement.vo.SelectVO;
import com.utils.UploadResult;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-27 18:21:05
 */
public interface ManureDisposalService {

	ManureDisposalVO get(Integer id);

	List<MediaUrl> getMediaUrl(Integer id);
	
	List<Map<String,Object>> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ManureDisposal manureDisposal);
	
	int update(ManureDisposal manureDisposal);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	Map<String,String> selectByTitle(String title);

	/**
	 * 保存图片及关联
	 * @param resultList
	 * @param map
	 * @return
	 */
	int saveMediaAndRef(List<UploadResult> resultList,Map<String,Object> map);

	/**
	 * 保存视频及关联
	 * @param imgPath
	 * @param map
	 * @return
	 */
    int saveVideoMediaAndRef(List<String> imgPath,Map<String,Object> map);
	/**
	 * 初始化下拉列表（通用）
	 * @param map
	 * @return
	 */
	List<SelectVO> initSelData(Map<String,Object> map);
}

package com.epmanagement.dao;

import com.entities.CorpseDisposal;
import com.epmanagement.vo.CorpseDisposalVO;
import com.epmanagement.vo.MediaUrl;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-28 09:52:35
 */
@Mapper
public interface CorpseDisposalDao {

	Map<String,String> get(Integer id);
	
	List<CorpseDisposalVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CorpseDisposal corpseDisposal);
	
	int update(CorpseDisposal corpseDisposal);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

//	根据title查询
	Map<String,String> selectByTitle(Map<String, Object> map);
	/**
	 * 查找尸体详情
	 * @param id 主键id
	 * @return
	 */
	CorpseDisposalVO getCorpseDetail(Integer id);
	CorpseDisposalVO getCorpseDetaili18n(Map<String, Object> map);

	/**
	 * 查看尸体照片和视频详情
	 * @param id
	 * @return
	 */
	List<MediaUrl> getMedia(Integer id);
	
}

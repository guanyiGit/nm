package com.goodsmanagement.dao;

import com.entities.GoodsInfoDO;
import com.goodsmanagement.vo.GoodsInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-05 15:17:41
 */
@Mapper
public interface GoodsInfoDao {

	GoodsInfoVO get(@Param("id") Integer id, @Param("type") Object type);

	List<GoodsInfoVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GoodsInfoDO goodsInfo);
	
	int update(GoodsInfoDO goodsInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}

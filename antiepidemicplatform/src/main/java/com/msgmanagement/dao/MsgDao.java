package com.msgmanagement.dao;

import com.entities.Msg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-10-06 10:06:04
 */
@Mapper
public interface MsgDao {

	Msg get(Integer id);
	
	List<Msg> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Msg msg);
	
	int update(Msg msg);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}

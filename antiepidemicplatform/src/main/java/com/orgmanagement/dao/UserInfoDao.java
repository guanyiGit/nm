package com.orgmanagement.dao;


import com.orgmanagement.domain.UserDO;
import com.orgmanagement.vo.UserSelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 11:05:22
 */
@Mapper
public interface UserInfoDao {

//	Map<String,String> get(Integer id);
//
//	List<Map<String,Object>> list(Map<String, Object> map);
//
//	int count(Map<String, Object> map);
//
//	int save(UserInfo userInfo);
//
//	int update(UserInfo userInfo);
//
//	int remove(Integer id);
//
//	int batchRemove(Integer[] ids);
//
//	Map<String,String> selectByUserNoOrName(Map<String,Object> map);
//
//	List<UserInfo> userList(Map<String,Object> map);
//
	List<UserSelVO> userSelList();

	UserDO get(Long userId);

	List<UserDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(UserDO user);

	int update(UserDO user);

	int remove(Long userId);

	int batchRemove(Long[] userIds);

	Long[] listAllDept();

	List<UserDO> pageList(Map<String,Object> map);

	Integer selecAccount(@Param("userName") String userName);

	int checkAccountRepeat(String account);

}

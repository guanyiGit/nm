package com.basicInfo.dao;

import com.basicInfo.vo.ProtectorVO;
import com.entities.TProtector;
import com.entities.TProtectorExample;
import com.entities.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TProtectorMapper {
    int countByExample(TProtectorExample example);

    int deleteByExample(TProtectorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TProtector record);

    int insertSelective(TProtector protector);

    List<TProtector> selectByExample(TProtectorExample example);

    TProtector selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TProtector record, @Param("example") TProtectorExample example);

    int updateByExample(@Param("record") TProtector record, @Param("example") TProtectorExample example);

    int updateByPrimaryKeySelective(TProtector record);

    int updateByPrimaryKey(TProtector record);

    //查看防疫员详情
	ProtectorVO findProtectorDetail(@Param("id") Integer id,@Param("type") Object type);

	//防疫员列表
	List<ProtectorVO> findProtectorList(Map<String, Object> map);

	//初始话下拉框
    List<Map<String,Object>> initProtectorSel(Map<String, Object> map);

    List<TProtector> initProtectorSel2(Long deptId);
    
    //求出总记录数
	int countProtector(Map<String, Object> map);

    UserInfo findUser(Integer userId);

    int updateUser(UserInfo user);
}
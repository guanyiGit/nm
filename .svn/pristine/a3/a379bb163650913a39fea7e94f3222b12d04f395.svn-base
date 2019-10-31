package com.basicInfo.dao;

import com.entities.TCooperation;
import com.entities.TCooperationExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface TCooperationMapper {
    int countByExample(TCooperationExample example);

    int deleteByExample(TCooperationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCooperation record);

    int insertSelective(TCooperation record);

    List<TCooperation> selectByExample(TCooperationExample example);

    TCooperation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TCooperation record, @Param("example") TCooperationExample example);

    int updateByExample(@Param("record") TCooperation record, @Param("example") TCooperationExample example);

    int updateByPrimaryKeySelective(TCooperation record);

    int updateByPrimaryKey(TCooperation record);

    
    /**
     * 查看合作社列表信息
     * @param map
     * @return
     */
	List<TCooperation> findCooperationList(Map<String, Object> map);
}
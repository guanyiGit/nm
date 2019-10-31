package com.basicInfo.dao;

import com.entities.TDrugInfo;
import com.entities.TDrugInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface TDrugInfoMapper {
    int countByExample(TDrugInfoExample example);

    int deleteByExample(TDrugInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TDrugInfo record);

    int insertSelective(TDrugInfo record);

    List<TDrugInfo> selectByExample(TDrugInfoExample example);

    TDrugInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TDrugInfo record, @Param("example") TDrugInfoExample example);

    int updateByExample(@Param("record") TDrugInfo record, @Param("example") TDrugInfoExample example);

    int updateByPrimaryKeySelective(TDrugInfo record);

    int updateByPrimaryKey(TDrugInfo record);

    /**
     * 统计药品列表
     * @param map
     * @return
     */
	List<TDrugInfo> findDrugList(HashMap<String, Object> map);

	//初始话药品下拉框
    List<TDrugInfo> initDrugSelect(HashMap<String,Object> map);
}
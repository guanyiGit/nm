package com.basicInfo.dao;

import com.entities.TForageInfo;
import com.entities.TForageInfoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TForageInfoMapper {
    int countByExample(TForageInfoExample example);

    int deleteByExample(TForageInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TForageInfo record);

    int insertSelective(TForageInfo record);

    List<TForageInfo> selectByExample(TForageInfoExample example);

    TForageInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TForageInfo record, @Param("example") TForageInfoExample example);

    int updateByExample(@Param("record") TForageInfo record, @Param("example") TForageInfoExample example);

    int updateByPrimaryKeySelective(TForageInfo record);

    int updateByPrimaryKey(TForageInfo record);

    /**
     * 统计饲料列表	
     * @param map
     * @return
     */
	List<TForageInfo> selectForageList(Map<String, Object> map);
}
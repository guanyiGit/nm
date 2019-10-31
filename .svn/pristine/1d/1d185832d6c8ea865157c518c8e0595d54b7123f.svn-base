package com.basicInfo.dao;

import com.entities.TMediaInfo;
import com.entities.TMediaInfoExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TMediaInfoMapper {
    int countByExample(TMediaInfoExample example);

    int deleteByExample(TMediaInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TMediaInfo record);

    int insertSelective(TMediaInfo record);

    List<TMediaInfo> selectByExample(TMediaInfoExample example);

    TMediaInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TMediaInfo record, @Param("example") TMediaInfoExample example);

    int updateByExample(@Param("record") TMediaInfo record, @Param("example") TMediaInfoExample example);

    int updateByPrimaryKeySelective(TMediaInfo record);

    int updateByPrimaryKey(TMediaInfo record);
}
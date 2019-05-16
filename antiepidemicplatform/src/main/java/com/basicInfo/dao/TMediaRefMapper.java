package com.basicInfo.dao;

import com.entities.TMediaRef;
import com.entities.TMediaRefExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TMediaRefMapper {
    int countByExample(TMediaRefExample example);

    int deleteByExample(TMediaRefExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TMediaRef record);

    int insertSelective(TMediaRef record);

    List<TMediaRef> selectByExample(TMediaRefExample example);

    TMediaRef selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TMediaRef record, @Param("example") TMediaRefExample example);

    int updateByExample(@Param("record") TMediaRef record, @Param("example") TMediaRefExample example);

    int updateByPrimaryKeySelective(TMediaRef record);

    int updateByPrimaryKey(TMediaRef record);
}
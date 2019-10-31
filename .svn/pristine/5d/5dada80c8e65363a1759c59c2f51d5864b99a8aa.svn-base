package com.basicInfo.dao;

import com.entities.TSysDict;
import com.entities.TSysDictExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TSysDictMapper {
    int countByExample(TSysDictExample example);

    int deleteByExample(TSysDictExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSysDict record);

    int insertSelective(TSysDict record);

    List<TSysDict> selectByExample(TSysDictExample example);
    List<TSysDict> selectEthnic(@Param("type") Object type);
    List<TSysDict> selectEducationBackground(@Param("type") Object type);

    TSysDict selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TSysDict record, @Param("example") TSysDictExample example);

    int updateByExample(@Param("record") TSysDict record, @Param("example") TSysDictExample example);

    int updateByPrimaryKeySelective(TSysDict record);

    int updateByPrimaryKey(TSysDict record);
}
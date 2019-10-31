package com.basicInfo.dao;

import com.basicInfo.vo.PadVO;
import com.entities.TPadInfo;
import com.entities.TPadInfoExample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TPadInfoMapper {
    int countByExample(TPadInfoExample example);

    int deleteByExample(TPadInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TPadInfo record);

    int insertSelective(TPadInfo record);

    List<TPadInfo> selectByExample(TPadInfoExample example);

    TPadInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TPadInfo record, @Param("example") TPadInfoExample example);

    int updateByExample(@Param("record") TPadInfo record, @Param("example") TPadInfoExample example);

    int updateByPrimaryKeySelective(TPadInfo record);

    int updateByPrimaryKey(TPadInfo record);
    
  	
  	//Pad列表
  	List<PadVO> findPadList(HashMap<String, Object>map);
}
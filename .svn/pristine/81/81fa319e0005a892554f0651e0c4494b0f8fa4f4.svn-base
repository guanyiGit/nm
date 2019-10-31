package com.basicInfo.dao;

import com.basicInfo.vo.VeterinarianVO;
import com.entities.TVeterinarian;
import com.entities.TVeterinarianExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TVeterinarianMapper {
    int countByExample(TVeterinarianExample example);

    int deleteByExample(TVeterinarianExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TVeterinarian record);

    int insertSelective(TVeterinarian record);

    List<TVeterinarian> selectByExample(TVeterinarianExample example);

    TVeterinarian selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TVeterinarian record, @Param("example") TVeterinarianExample example);

    int updateByExample(@Param("record") TVeterinarian record, @Param("example") TVeterinarianExample example);

    int updateByPrimaryKeySelective(TVeterinarian record);

    int updateByPrimaryKey(TVeterinarian record);

    //查找兽医列表
	List<VeterinarianVO> findVeterinarianList(Map<String, Object> map);
	//兽医详情
	VeterinarianVO findVeterinarianDetail(Integer id);
}
package com.epmanagement.dao;

import com.entities.TAntibodyDetection;
import com.entities.TAntibodyDetectionExample;
import com.epmanagement.vo.AntibodyDetectionVO;
import com.epmanagement.vo.MediaUrl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TAntibodyDetectionMapper {
    int countByExample(TAntibodyDetectionExample example);

    int deleteByExample(TAntibodyDetectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAntibodyDetection record);

    int insertSelective(TAntibodyDetection record);

    List<TAntibodyDetection> selectByExample(TAntibodyDetectionExample example);

    TAntibodyDetection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAntibodyDetection record, @Param("example") TAntibodyDetectionExample example);

    int updateByExample(@Param("record") TAntibodyDetection record, @Param("example") TAntibodyDetectionExample example);

    int updateByPrimaryKeySelective(TAntibodyDetection record);

    int updateByPrimaryKey(TAntibodyDetection record);

    /**
     * 查看牛羊免疫抗体列表
     * @param map
     * @return
     */
	List<AntibodyDetectionVO> findAntibodyDetectionList(HashMap<String, Object> map);
	/**
	 * 查找牛羊抗体详情
	 * @param id 主键id
	 * @return
	 */
	AntibodyDetectionVO getAntibodyDetectionDetail(Integer id);
	/**
	 * 查找牛羊抗体详情中的照片和视频
	 * @param id 主键id
	 * @return
	 */
	List<MediaUrl> getMedia(Integer id);
}
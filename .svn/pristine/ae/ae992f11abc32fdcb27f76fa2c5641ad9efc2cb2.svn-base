package com.epmanagement.dao;

import com.entities.TInfectionInfo;
import com.entities.TInfectionInfoExample;
import com.epmanagement.vo.InfectionInfoVO;
import com.epmanagement.vo.MediaUrl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TInfectionInfoMapper {
    int countByExample(TInfectionInfoExample example);

    int deleteByExample(TInfectionInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TInfectionInfo record);

    int insertSelective(TInfectionInfo record);

    List<TInfectionInfo> selectByExample(TInfectionInfoExample example);

    TInfectionInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TInfectionInfo record, @Param("example") TInfectionInfoExample example);

    int updateByExample(@Param("record") TInfectionInfo record, @Param("example") TInfectionInfoExample example);

    int updateByPrimaryKeySelective(TInfectionInfo record);

    int updateByPrimaryKey(TInfectionInfo record);
    
    /**
     * 牛羊病变感染列表
     * @param map
     * @return
     */
	List<InfectionInfoVO> findInfectionInfoList(HashMap<String, Object> map);

	/**
	 * 查找牛羊抗体详情
	 * @param id 主键id
	 * @return
	 */
	InfectionInfoVO getInfectionInfoDetail(Integer id);
	
	/**
	 * 查找牛羊抗体详情中的照片和视频
	 * @param id 主键id
	 * @return
	 */
	List<MediaUrl> getMedia(Integer id);
}
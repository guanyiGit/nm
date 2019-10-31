package com.epmanagement.dao;

import com.entities.TDogAnatomy;
import com.entities.TDogAnatomyExample;
import com.epmanagement.vo.DogAnatomyVO;
import com.epmanagement.vo.MediaUrl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TDogAnatomyMapper {
    int countByExample(TDogAnatomyExample example);

    int deleteByExample(TDogAnatomyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TDogAnatomy record);

    int insertSelective(TDogAnatomy record);

    List<TDogAnatomy> selectByExample(TDogAnatomyExample example);

    TDogAnatomy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TDogAnatomy record, @Param("example") TDogAnatomyExample example);

    int updateByExample(@Param("record") TDogAnatomy record, @Param("example") TDogAnatomyExample example);

    int updateByPrimaryKeySelective(TDogAnatomy record);

    int updateByPrimaryKey(TDogAnatomy record);

    /**
     * 查看犬只解剖列表
     * @param map
     * @return
     */
	List<DogAnatomyVO> findDogAnatomyList(HashMap<String, Object> map);
	
	/**
	 * 查找犬只解剖详情
	 * @param id 主键id
	 * @return
	 */
	DogAnatomyVO getDogAnatomyDetail(Integer id);
	
	/**
	 * 查找犬只解剖详情照片和视频
	 * @param id 主键id
	 * @return
	 */
	List<MediaUrl> getMedia(Integer id);
}
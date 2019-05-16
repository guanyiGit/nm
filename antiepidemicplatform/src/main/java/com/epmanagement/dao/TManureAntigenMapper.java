package com.epmanagement.dao;

import com.entities.OrgInfo;
import com.entities.TManureAntigen;
import com.entities.TManureAntigenExample;
import com.epmanagement.vo.ManureAntigenVO;
import com.epmanagement.vo.MediaUrl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TManureAntigenMapper {
    int countByExample(TManureAntigenExample example);

    int deleteByExample(TManureAntigenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TManureAntigen record);

    int insertSelective(TManureAntigen record);

    List<TManureAntigen> selectByExample(TManureAntigenExample example);

    TManureAntigen selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TManureAntigen record, @Param("example") TManureAntigenExample example);

    int updateByExample(@Param("record") TManureAntigen record, @Param("example") TManureAntigenExample example);

    int updateByPrimaryKeySelective(TManureAntigen record);

    int updateByPrimaryKey(TManureAntigen record);
    
    //犬粪抗原列表
	List<ManureAntigenVO> findManureAntigenList(HashMap<String, Object> map);
	/**
	 * 查看本州和下属县级组织
	 */
	List<OrgInfo> findOrgList(Integer orgId);
	
	/**
	 * 查看犬粪抗原详情
	 * @param id
	 * @return
	 */
	ManureAntigenVO getManureAntigenDetail(Integer id);
	
	/**
	 * 获得详情中的照片和视频
	 * @param id
	 * @return
	 */
	List<MediaUrl> getMedia(Integer id);
}
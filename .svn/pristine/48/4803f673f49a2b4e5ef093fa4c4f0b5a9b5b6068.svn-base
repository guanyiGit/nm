package com.basicInfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.basicInfo.vo.PastoralVO;
import com.entities.TPastoralCommittee;
import com.entities.TPastoralCommitteeExample;

@Mapper
public interface TPastoralCommitteeMapper {
    int countByExample(TPastoralCommitteeExample example);

    int deleteByExample(TPastoralCommitteeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TPastoralCommittee record);

    int insertSelective(TPastoralCommittee record);

    List<TPastoralCommittee> selectByExample(TPastoralCommitteeExample example);

    TPastoralCommittee selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TPastoralCommittee record, @Param("example") TPastoralCommitteeExample example);

    int updateByExample(@Param("record") TPastoralCommittee record, @Param("example") TPastoralCommitteeExample example);

    int updateByPrimaryKeySelective(TPastoralCommittee record);

    int updateByPrimaryKey(TPastoralCommittee record);

    /**
     * 查看牧委会列表
     * @param map
     * @return
     */
	List<TPastoralCommittee> findPastoralCommitteeList(Map<String, Object> map);
}
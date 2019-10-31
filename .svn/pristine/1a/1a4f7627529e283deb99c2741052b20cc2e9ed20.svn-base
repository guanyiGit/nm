package com.posiition.mapper;

import com.entities.OrgInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BizMapper {


    /**
     * 查询我的机构id
     *
     * @param userId
     * @return
     */
    Integer findOrgIdByUserId(Integer userId);


    /**
     * 查询所有机构信息
     *
     * @return
     */
    List<OrgInfo> findOrgAll();


    /**
     * 查询我的角色
     * @param userId
     * @return
     */
    List<Integer> findRoleIdsByUserId(Integer userId);
}

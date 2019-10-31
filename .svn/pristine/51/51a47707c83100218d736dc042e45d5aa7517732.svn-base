package com.posiition.service;

import com.posiition.VO.Org;

import java.util.List;

/**
 * 业务查询
 */
public interface BizService {


    /**
     * 当前用户是否是机构
     *
     * @param userId
     * @return
     */
    boolean isOrg(Integer userId);

    /**
     * 当前用户机构信息(不是机构没有机构信息)
     *
     * @param userId
     * @return
     */
    Org orgs(Integer userId);


    /**
     * 当前用户下的机构id集（不是机构类型返回null）
     *
     * @param userId
     * @return
     */
    List<Integer> orgIds(Integer userId);

}

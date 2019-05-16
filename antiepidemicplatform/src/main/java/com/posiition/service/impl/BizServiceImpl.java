package com.posiition.service.impl;

import com.posiition.VO.Org;
import com.posiition.mapper.BizMapper;
import com.posiition.service.BizService;
import com.utils.OrgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BizServiceImpl implements BizService {

    @Autowired
    private BizMapper bizMapper;

    @Override
    public boolean isOrg(Integer userId) {
        List<Integer> roleIds = bizMapper.findRoleIdsByUserId(userId);
        Boolean isprotector  = false;
        if (roleIds == null ||roleIds.size()<=0){
            return  false;
        }else{
            for (Integer roleId: roleIds) {
                if (roleId == 2){
                    isprotector = true;
                    break;
                }
            }
        }
        return isprotector;
    }


    @Override
    public Org orgs(Integer userId) {
        Org org = new Org();
        if (this.isOrg(userId)) {
            org.setOrg(false);//不是机构类型
        } else {
            org.setOrg(true);
            org.setOrgid(bizMapper.findOrgIdByUserId(userId));
            org.setOrgInfos(bizMapper.findOrgAll());
        }
        return org;
    }


    @Override
    public List<Integer> orgIds(Integer userId) {
        Org orgs = this.orgs(userId);
        if (orgs.isOrg()) {
            return OrgUtils.orgReverse(orgs.getOrgInfos(), orgs.getOrgid(), new ArrayList<>());
        }
        return null;
    }
}

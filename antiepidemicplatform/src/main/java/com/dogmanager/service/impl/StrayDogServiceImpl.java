/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: StrayDogServiceImpl
 * Author:   Administrator
 * Date:     2018/9/28 14:11
 * Description: 流浪犬管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dogmanager.service.impl;

import com.dogmanager.VO.StrayDogVO;
import com.dogmanager.dao.TDogInfoMapper;
import com.dogmanager.dao.TStrayDogMapper;
import com.dogmanager.service.StrayDogService;
import com.entities.OrgInfo;
import com.entities.TStrayDog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.utils.OrgUtils;
import com.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈流浪犬管理〉
 *
 * @author Administrator
 * @create 2018/9/28
 * @since 1.0.0
 */
@Service
public class StrayDogServiceImpl implements StrayDogService {

    @Autowired
    private TStrayDogMapper strayDogMapper;
    @Autowired
    private TDogInfoMapper dogInfoMapper;
    @Autowired
    private OrgInfoDao orgInfoDao;

    @Override
    public void delete(Integer Id) {
        strayDogMapper.deleteByPrimaryKey(Id);
    }

    @Override
    public void update(TStrayDog strayDog) {
        strayDog.setUpdateDate(new Date());
        strayDogMapper.updateByPrimaryKey(strayDog);
    }

    /**
     * 新增犬只
     * */
    @Override
    public void saveStrayDog(TStrayDog strayDog) {
        strayDog.setDealTime(new Date());
        strayDog.setCreateDate(new Date());
        strayDog.setUpdateDate(new Date());
        UserDO user = ShiroUtils.getUser();
        if(user!=null){
            strayDog.setOrgId(user.getDeptId().intValue());
            strayDog.setOperator(user.getUserId().intValue());
            strayDog.setAreaId(user.getAreaId().intValue());
        }
        strayDogMapper.insertSelective(strayDog);
    }

    /**
     * 列表
     * */
    @Override
    public PageInfo<StrayDogVO> findStrayDogList(HashMap<String,Object> map) {
        UserDO user = ShiroUtils.getUser();
        int type=user.getRoles().get(0).getType();
        if(type==2){
            map.put("operator",user.getUserId());//userid
        }else{
            List<OrgInfo> orgList=orgInfoDao.orgList();
            List<Integer> res=new ArrayList<>();
            if(StringUtils.isEmpty(map.get("orgId"))){
                res= OrgUtils.orgReverse(orgList, user.getDeptId().intValue(), res);
            }else {
                res= OrgUtils.orgReverse(orgList, Integer.parseInt(map.get("orgId").toString()), res);
            }
            map.put("orgList",res);//组织集合
        }
        int pageNum=Integer.parseInt(map.get("pageNum").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        PageHelper.startPage(pageNum, pageSize);
        Object langType = ShiroUtils.getSubjct().getSession().getAttribute("type");
        map.put("langType",langType);
        List<StrayDogVO> strayDogList = strayDogMapper.findStrayDogList(map);
        for(StrayDogVO sv:strayDogList){
            sv.setUserId(user.getUserId());
        }
        PageInfo<StrayDogVO> pageInfo=new PageInfo<>(strayDogList);
        return pageInfo;
    }

    /**
     * 详情
     * */
    @Override
    public StrayDogVO findStrayDogById(Integer Id) {
        Map<String,Object> map = new HashMap<>();
        map.put("Id",Id);
        Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
        map.put("langType",type);
        StrayDogVO strayDogVO = strayDogMapper.findStrayDogByIdI18N(map);
        return strayDogVO;
    }

    @Override
    public String checkNo(String no) {
        return strayDogMapper.checkNo(no);
    }

    @Override
    public PageInfo<StrayDogVO> findStrayDogListByDate(String startDate, String endDate, int pageNum, int pageSize, Integer pro, Integer orgId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        UserDO user = ShiroUtils.getUser();
        int type=user.getRoles().get(0).getType();
        if(type==2){
            map.put("operator",user.getUserId());  //userid
        }else{
            List<OrgInfo> orgList=orgInfoDao.orgList();
            List<Integer> res=new ArrayList<>();
            if(StringUtils.isEmpty(orgId)){
                res= OrgUtils.orgReverse(orgList, user.getDeptId().intValue(), res);
            }else {
                res= OrgUtils.orgReverse(orgList, orgId, res);
            }
            map.put("orgList",res);//组织集合
        }
        if (!StringUtils.isEmpty(pro)){
            map.put("operator",pro);//userid
        }
        PageHelper.startPage(pageNum, pageSize);
        List<StrayDogVO> strayDogList = strayDogMapper.findStrayDogList(map);
        PageInfo<StrayDogVO> pageInfo=new PageInfo<>(strayDogList);
        return pageInfo;
    }
}

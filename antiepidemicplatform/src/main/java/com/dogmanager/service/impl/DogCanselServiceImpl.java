/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DogCanselServiceImpl
 * Author:   Administrator
 * Date:     2018/9/28 14:46
 * Description: 犬只注销Service
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dogmanager.service.impl;

import com.dogmanager.VO.DogCanselVO;
import com.dogmanager.dao.TDogCancelMapper;
import com.dogmanager.dao.TDogInfoMapper;
import com.dogmanager.service.DogCanselService;
import com.dogmanager.utils.DogResult;
import com.entities.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.orgmanagement.dao.AreaInfoDao;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.AreaInfoService;
import com.utils.OrgUtils;
import com.utils.ShiroUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈犬只注销Service〉
 *
 * @author Administrator
 * @create 2018/9/28
 * @since 1.0.0
 */
@Service
public class DogCanselServiceImpl implements DogCanselService{
    private org.slf4j.Logger logger = LoggerFactory.getLogger(DogCanselServiceImpl.class);
    @Autowired
    private TDogCancelMapper dogCancelMapper;
    @Autowired
    private OrgInfoDao orgInfoDao;
    @Autowired
    private TDogInfoMapper dogInfoMapper;
    @Autowired
    private AreaInfoService areaInfoService;
    @Autowired
    private AreaInfoDao areaInfoDao;



    @Override
    public DogResult findTSysDict() {
        Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
        Map<String,Object> param = new HashMap<>();
        param.put("langType",type);
//        List<TSysDict> dogCancelReasonList = dogCancelMapper.findTSysDict("dog_cancel_reason");
        param.put("type","dog_cancel_reason");
        List<TSysDict> dogCancelReasonList = dogCancelMapper.findTSysDictI18N(param);
//        List<TSysDict> deviceDealList = dogCancelMapper.findTSysDict("device_deal_way");
        param.put("type","device_deal_way");
        List<TSysDict> deviceDealList = dogCancelMapper.findTSysDictI18N(param);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("dogCancelReason",dogCancelReasonList);
        map.put("deviceDeal",deviceDealList);
        return DogResult.ok(map);
    }

    /**
     * 新增
     * */
    @Override
    @Transactional
    public void save(TDogCancel dogCancel) {
        if(StringUtils.isEmpty(dogCancel.getTraceId())){
            logger.error("【犬只注销】 溯源号为空；traceId:",dogCancel.getTraceId());
            throw new  RuntimeException();
        }
        String traceId = dogCancel.getTraceId().trim();
        //判断犬只是否存在
        TDogInfo dogInfo = dogInfoMapper.selectByPrimaryKey(traceId,dogInfoMapper.findProId(ShiroUtils.getUserId().intValue()));
        if(dogInfo==null){
            logger.error("【犬只注销】 犬只不存在");
            throw new RuntimeException();
        }
        UserDO user = ShiroUtils.getUser();
        dogCancel.setCreateDate(new Date());
        dogCancel.setUpdateDate(new Date());
        dogCancel.setOrgId(user.getDeptId().intValue());
        dogCancel.setOperator(user.getUserId().intValue());
        dogCancel.setTraceId(traceId);
        dogCancelMapper.insert(dogCancel);
        //改犬只状态
        dogInfoMapper.updateDogStatus(dogCancel.getReason(),traceId);
        //改设备状态
        TDeviceRefDog deviceRefDog = dogInfoMapper.selectByTraceId(traceId);
        if(deviceRefDog!=null){
            dogInfoMapper.updateDeviceStatus(dogCancel.getDeviceDealWay(),deviceRefDog.getDeviceNo());
            //删除犬只与设备的关联关系
            dogCancelMapper.deleteDeviceRefDog(traceId);
        }

    }

    /**
     * 列表
     * */
    @Override
    public PageInfo<DogCanselVO> findDogCanselList(HashMap<String,Object> map) {
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
            //如果当前组织不是乡级组织且也没有下级乡级组织,搜索其本身
            if (res.isEmpty() || res.size() == 0){
                res.add(user.getDeptId().intValue());
            }
            map.put("orgList",res);//组织集合
        }
        int pageNum=Integer.parseInt(map.get("pageNum").toString());
        int pageSize=Integer.parseInt(map.get("pageSize").toString());
        PageHelper.startPage(pageNum, pageSize);
        Object langType = ShiroUtils.getSubjct().getSession().getAttribute("type");
        map.put("langType",langType);
        List<DogCanselVO> dogCancelList = dogCancelMapper.findDogCancelList(map);
        List<AreaInfo> AreaInfoList = areaInfoDao.getList();
        for (DogCanselVO dc:dogCancelList){
            if(!StringUtils.isEmpty(dc.getTown())){
                dc.setTown(areaInfoService.findAreas(Integer.parseInt(dc.getTown()),AreaInfoList));
            }
        }
        PageInfo<DogCanselVO> pageInfo = new PageInfo<>(dogCancelList);
        return pageInfo;
    }

    /**
     * 详情
     * */
    @Override
    public DogCanselVO findDogCancelById(Integer Id) {
        Map<String,Object> map = new HashMap<>();
        map.put("Id",Id);
        Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
        map.put("langType",type);
//        DogCanselVO dogCanselVO = dogCancelMapper.findDogCanselVOById(Id);
        DogCanselVO dogCanselVO = dogCancelMapper.findDogCanselVOByIdI18N(map);
        if(!StringUtils.isEmpty(dogCanselVO.getTown())){
            List<AreaInfo> AreaInfoList = areaInfoDao.getList();
            dogCanselVO.setTown(areaInfoService.findAreas(Integer.parseInt(dogCanselVO.getTown()),AreaInfoList));
        }
        return dogCanselVO;
    }

    /**
     * 修改
     * */
    @Override
    public void updateDogCancel(TDogCancel dogCancel) {
         dogCancelMapper.updateByPrimaryKey(dogCancel);
    }

    /**
     * 删除
     * */
    @Override
    public void deleteDogCancel(Integer Id) {
            dogCancelMapper.deleteByPrimaryKey(Id);
    }
}

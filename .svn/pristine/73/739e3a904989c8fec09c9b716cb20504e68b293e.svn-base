package com.statisanalysis.serivce.impl;

import com.entities.AreaInfo;
import com.entities.OrgInfo;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.statisanalysis.dao.DogAmountDao;
import com.statisanalysis.dao.OwnerAmountDao;
import com.statisanalysis.entity.OwnerNumVO;
import com.statisanalysis.serivce.IOwnerAmountService;
import com.statisanalysis.utils.DateUtils;
import com.utils.OrgUtils;
import com.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/4 20:47
 * @Version 1.0
 */
@Service
public class OwnerAmountServiceImpl implements IOwnerAmountService {
    private Logger logger = LoggerFactory.getLogger(OwnerAmountServiceImpl.class);
    @Autowired
    private OwnerAmountDao ownerAmountDao;
    @Autowired
    private DogAmountDao dogAmountDao;
    @Autowired
    private OrgInfoDao orgInfoDao;



    @Override
    public List<OwnerNumVO> countOwnerNum(Map<String, Object> params) throws Exception {
        List<String> times = DateUtils.getTimes(params);
        String startTime = times.get(0);
        String endTime = times.get(1);
        String nextMonth = times.get(2);
        List<String> monthBetween = DateUtils.getMonthBetween(startTime, endTime);
        params.put("nextMonth",nextMonth);
        List<OwnerNumVO> list = new ArrayList<>();
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type == 2) {
            //防疫员
            params.put("operatorId",user.getUserId());
            List<Date> ownerInfo = ownerAmountDao.getOwnerNumCount(params);
            //统计犬主总数、新增犬主
            list = getOwnerNumList(monthBetween, ownerInfo);
        }else{
            //其他角色
//            List<Integer> orgList =new ArrayList<>();
//            orgList.add(20);
//            orgList.add(21);
//            orgList.add(22);
//            params.put("orgList",orgList);
            List<OrgInfo> orgInfos = orgInfoDao.orgList();
            List<Integer> orgList = OrgUtils.orgReverse(orgInfos, Integer.parseInt(user.getDeptId().toString()), new ArrayList<>());
            params.put("orgList",orgList);
            List<Date> ownerInfo = ownerAmountDao.getOwnerNumCount(params);
            //统计犬主总数、新增犬主
            list = getOwnerNumList(monthBetween, ownerInfo);
        }

        return list;
    }

    /**
     * 统计犬主数量趋势
     *
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> countOwnerNumTrend(Map<String, Object> map) throws Exception {
        Map<String,Object> result = new HashMap<>();
        List<String> townList = new ArrayList<>();
        List<String> monthList = new ArrayList<>();
        List<List> numList = new ArrayList<>();
        List<String> times = DateUtils.getTimes(map);
        String startTime = times.get(0);
        String endTime = times.get(1);
        String nextMonth = times.get(2);
        List<String> monthBetween = DateUtils.getMonthBetween(startTime, endTime);
        map.put("nextMonth",nextMonth);
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type == 2) {
            //防疫员
            map.put("operatorId",user.getUserId());
            List<Date> ownerCreateDate1 = ownerAmountDao.getOwnerCreateDate1(map);
            List<Integer> perMonthOwnerNum = getPerMonthOwnerNum(monthBetween, ownerCreateDate1);
            numList.add(perMonthOwnerNum);
        }else if(type == 3 || type == 8) {
            //乡组织
//            map.put("orgId",21);
            map.put("orgId",user.getDeptId());
            List<Date> ownerCreateDate1 = ownerAmountDao.getOwnerCreateDate1(map);
            List<Integer> perMonthOwnerNum = getPerMonthOwnerNum(monthBetween, ownerCreateDate1);
            numList.add(perMonthOwnerNum);
        }else if(type == 5 || type == 9) {
            //县组织
//            map.put("orgId",16);
            map.put("orgId",user.getDeptId());
            List<AreaInfo> areaList = dogAmountDao.getAreaInfoByOrgId(map);
            for (AreaInfo area:
                 areaList) {
                townList.add(area.getName());
                List<Integer> list = new ArrayList<>();
                map.put("areaId",area.getId());
                List<Date> ownerCreateDate2 = ownerAmountDao.getOwnerCreateDate2(map);
                list = getPerMonthOwnerNum(monthBetween, ownerCreateDate2);
                numList.add(list);
            }
        }else if(type == 6 || type == 10) {
            //州组织
//            map.put("orgId",3);
            map.put("orgId",user.getDeptId());
            List<AreaInfo> areaList = dogAmountDao.getAreaInfoByOrgId(map);
            for (AreaInfo area:
                    areaList) {
                townList.add(area.getName());
                List<Integer> list = new ArrayList<>();
                map.put("areaId",area.getId());
                List<Date> ownerCreateDate3 = ownerAmountDao.getOwnerCreateDate3(map);
                list = getPerMonthOwnerNum(monthBetween, ownerCreateDate3);
                numList.add(list);
            }
        }
        result.put("townList",townList);
        result.put("monthList",monthBetween);
        result.put("numList",numList);
        return result;
    }

    //统计到每月为止存在多少犬主
    public List<Integer> getPerMonthOwnerNum(List<String> monthBetween,List<Date> dateList) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        List<Integer> list = new ArrayList<>();
        for (String month:
             monthBetween) {
            int res1 = DateUtils.getNumOfDate(month);
            int num = 0;
            for (Date date :
                dateList) {
                if(date != null){
                    String format = sdf.format(date);
                    int res2 = DateUtils.getNumOfDate(format);
                    if(res2 <= res1) {
                        num++;
                    }
                }
            }
            list.add(num);
        }
        return list;
    }

    //统计犬主总数和新增犬主
    public List<OwnerNumVO> getOwnerNumList(List<String> monthBetween,List<Date> dateList) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        List<OwnerNumVO> list = new ArrayList<>();
        for (String month :
                monthBetween) {
            int res1 = DateUtils.getNumOfDate(month);
            int num1 = 0;
            int num2 = 0;
            for (Date date :
                    dateList) {
                if (date != null) {
                    String format = sdf.format(date);
                    int res2 = DateUtils.getNumOfDate(format);
                    if (res2 <= res1) {
                        num1++;
                    }
                    if(res1 == res2) {
                        num2++;
                    }
                }
            }
            OwnerNumVO ownerNumVO = new OwnerNumVO();
            ownerNumVO.setPerMonth(month);
            ownerNumVO.setTotalNum(num1);
            ownerNumVO.setNewNum(num2);
            list.add(ownerNumVO);
        }
        return list;
    }

}

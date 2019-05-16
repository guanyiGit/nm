package com.statisanalysis.wx.service.impl;

import com.statisanalysis.utils.DateUtils;
import com.statisanalysis.wx.dao.DogAndOwnerDao;
import com.statisanalysis.wx.service.IDogAndOwnerService;
import com.statisanalysis.wx.utils.HandleDataUtils;
import com.statisanalysis.wx.vo.CountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/11/21 14:12
 * @Version 1.0
 */
@Service
public class DogAndOwnerServiceImpl implements IDogAndOwnerService {
    @Autowired
    private DogAndOwnerDao dao;

    /**
     * 统计犬只存栏数量、犬主数量、流浪犬处理数量
     *
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> countInfo(Map<String, Object> params) throws Exception {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        int roleType = Integer.parseInt(params.get("roleType").toString());
        int orgId = Integer.parseInt(params.get("orgId").toString());
        String areaName = params.get("areaName").toString();
        Integer myAreaId = Integer.parseInt(params.get("myAreaId").toString());
        //设置查询日期参数
        Object month = params.get("month");
        String nextMonth = "";
        String searchMonth = "";
        if(month != null && !month.toString().trim().equals("")) {
            searchMonth = month.toString();
            nextMonth = DateUtils.getLastMonth(searchMonth, 0, 1, 0);
        }else {
            //第一次加载默认查找上个月的信息
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            nextMonth = sdf.format(date);   //当前月是上个月的下个月
            searchMonth = DateUtils.getLastMonth(nextMonth, 0, -1, 0);

        }
        map.put("nextMonth",nextMonth);      //指定月份的下一月，没有指定就是上个月的下一月（即系统时间的当前月）
        map.put("searchMonth",searchMonth); //按指定的月份，没有指定默认为上一月
        List<CountVO> dogInfoList = new ArrayList<>();
        List<CountVO> dogOwnerList = new ArrayList<>();
        List<CountVO> strayDogList = new ArrayList<>();

        if(roleType == 3 || roleType == 8 ) {
            //乡级管理员
            int flag = 0;   //0:第一次加载或防疫员选择不限 1：选择一个防疫员
            List<Integer> protectorList = new ArrayList<>();
            Object protectId = params.get("protectId");
            if(protectId != null && !protectId.toString().trim().equals("")) {
                protectorList.add(Integer.parseInt(protectId.toString()));
                flag = 1;
            }else {
                //第一次加载或者不限则查找所有的防疫员
                protectorList = dao.getProtectorIds(orgId);
            }
            map.put("protectorList",protectorList);

           dogInfoList = dao.getDogInfoByVillage(map);
           dogOwnerList = dao.getDogOwnerByVillage(map);
           strayDogList = dao.getStrayByVillage(map);
           //添加所属防疫员为0的记录
            addProtector(dogInfoList,protectorList);
            addProtector(dogOwnerList,protectorList);
            addProtector(strayDogList,protectorList);
            for (CountVO vo:
                    dogInfoList) {
                vo.setVillageName(areaName);    //手动为犬只存栏数量设置所属乡镇
                vo.setVillageId(myAreaId);
            }
            for (CountVO vo:
                    dogOwnerList) {
                vo.setVillageName(areaName);    //手动为犬主数量设置所属乡镇
                vo.setVillageId(myAreaId);
            }
            for (CountVO vo:
                    strayDogList) {
                vo.setVillageName(areaName);    //手动为流浪犬设置所属乡镇
                vo.setVillageId(myAreaId);
            }
            //处理特殊数据
            if(flag == 0) {
                HandleDataUtils.handleData4(dogInfoList);
                HandleDataUtils.handleData4(dogOwnerList);
                map.put("myAreaId",myAreaId);
                Integer count = dao.countStrayDog(map);
                if(count == null) {
                    count = 0;
                }
                HandleDataUtils.handleData5(strayDogList,count);
            }

        }else if(roleType == 5 || roleType == 9) {
            //县级管理员
            int flag = 0;   //0：第一次加载或选择乡为不限 1：选择特定的乡
            List<Integer> areaIdList = new ArrayList<>();
            Object areaId = params.get("areaId");
            if(areaId != null && !areaId.toString().trim().equals("")) {
                int vId = Integer.parseInt(areaId.toString());
                //判断该areaId是否是县级的
                List<Integer> vilAreaIdList = dao.getAreaIdList(orgId);     //查询本县组织下属组织所在的区域
                if(vilAreaIdList != null && vilAreaIdList.contains(vId)) {
                    //不是县区域ID,是查找特定的乡
                    areaIdList.add(vId);
                    flag = 1;
                }else {
                    //乡选择不限
                    areaIdList = dao.getAreaIdList(orgId);
                }
            }else {
                //第一次加载或者不限则查找所有乡
                areaIdList = dao.getAreaIdList(orgId);
            }
            map.put("areaIdList",areaIdList);
            dogInfoList = dao.getDogInfoByCounty(map);
            dogOwnerList = dao.getDogOwnerByCounty(map);
            strayDogList = dao.getStrayByCounty(map);
            //添加所属乡数量为0的记录
            addVillage(dogInfoList,areaIdList);
            addVillage(dogOwnerList,areaIdList);
            addVillage(strayDogList,areaIdList);
            for (CountVO vo:
                 dogInfoList) {
                vo.setCountyName(areaName);
                vo.setCountyId(myAreaId);
            }
            for (CountVO vo:
                 dogOwnerList) {
                vo.setCountyName(areaName);
                vo.setCountyId(myAreaId);
            }
            for (CountVO vo:
                 strayDogList) {
                vo.setCountyName(areaName);
                vo.setCountyId(myAreaId);
            }

            //处理特殊数据
            if(flag == 0) {
                HandleDataUtils.handleData3(dogInfoList);
                HandleDataUtils.handleData3(dogOwnerList);
                HandleDataUtils.handleData3(strayDogList);
            }

        }else if(roleType == 6|| roleType == 10) {
            int flag = 0;   //0:第一次加载或者县选择不限，1：县选择了乡选择不限，2：县和乡都选择了
            //州级管理员
            List<Integer> areaIdList = new ArrayList<>();
            Object areaId = params.get("areaId");
            if(areaId != null && !areaId.toString().trim().equals("")) {
                //判断是否是州级区域
                int aId = Integer.parseInt(areaId.toString());
                if(aId == myAreaId.intValue()) {
                    areaIdList = dao.getAreaIdList2(orgId);
                }else {
                    //判断当前areaId是否为县级区域
                    List<Integer> countyAreaIdList = dao.getCountyAreaIdList();
                    if(countyAreaIdList.contains(aId)){
                        //是县级区域
                        areaIdList = dao.getVillageIdList(aId);
                        flag = 1;
                    }else {
                        //是乡级区域
                        areaIdList.add(aId);
                        flag = 2;
                    }
                }
            }else {
                //第一次加载或县选择不限
                areaIdList = dao.getAreaIdList2(orgId);
            }
            map.put("areaIdList",areaIdList);
            dogInfoList = dao.getDogInfoByState(map);
            dogOwnerList = dao.getDogOwnerByState(map);
            strayDogList = dao.getStrayByState(map);
            //添加所属乡数量为0的记录
            addVillage(dogInfoList,areaIdList);
            addVillage(dogOwnerList,areaIdList);
            addVillage(strayDogList,areaIdList);
            for (CountVO vo:
                 dogInfoList) {
                vo.setStateName(areaName);
                vo.setStateId(myAreaId);
            }
            for (CountVO vo:
                 dogOwnerList) {
                vo.setStateName(areaName);
                vo.setStateId(myAreaId);
            }
            for (CountVO vo:
                 strayDogList) {
                vo.setStateName(areaName);
                vo.setStateId(myAreaId);
            }
            //处理特殊数据
            if(flag == 0) {
                //添加州、县的统计数据
                HandleDataUtils.handleData(dogInfoList);
                HandleDataUtils.handleData(dogOwnerList);
                HandleDataUtils.handleData(strayDogList);
            }else if(flag == 1) {
                //只添加县的统计数据
                HandleDataUtils.handleData1(dogInfoList);
                HandleDataUtils.handleData1(dogOwnerList);
                HandleDataUtils.handleData1(strayDogList);
            }
        }

        for (CountVO vo:
                dogInfoList) {
            if (vo.getProtectorId() != null) {
                vo.setStateName(null);
                vo.setCountyName(null);
                vo.setVillageName(null);
            }else if(vo.getVillageId() != null) {
                vo.setStateName(null);
                vo.setCountyName(null);
            }else if(vo.getCountyId() != null) {
                vo.setStateName(null);
            }
        }
        for (CountVO vo:
                dogOwnerList) {
            if (vo.getProtectorId() != null) {
                vo.setStateName(null);
                vo.setCountyName(null);
                vo.setVillageName(null);
            }else if(vo.getVillageId() != null) {
                vo.setStateName(null);
                vo.setCountyName(null);
            }else if(vo.getCountyId() != null) {
                vo.setStateName(null);
            }
        }
        for (CountVO vo:
                strayDogList) {
            if (vo.getProtectorId() != null) {
                vo.setStateName(null);
                vo.setCountyName(null);
                vo.setVillageName(null);
            }else if(vo.getVillageId() != null) {
                vo.setStateName(null);
                vo.setCountyName(null);
            }else if(vo.getCountyId() != null) {
                vo.setStateName(null);
            }
        }


        result.put("dogList",dogInfoList);     //添加犬只存栏数量
        result.put("dogOwnerList",dogOwnerList);   //添加犬主数量
        result.put("strayDogList",strayDogList);  //添加流浪狗处理数量
        return result;

    }

    public List<CountVO> addVillage(List<CountVO> list,List<Integer> areaIdList) {
        if(list.size() < 1) {
            //没有查询出数据，手动添加所有的乡数据
            for (Integer aid: areaIdList) {
                CountVO areaInfo = dao.getPAreaInfo(aid);
                areaInfo.setCount(0);
                list.add(areaInfo);
            }
        }else{
            //查询出了所有或者部分乡数据，判断是否需要添加
            for (Integer aid: areaIdList) {
                int size = list.stream().filter(item -> item.getVillageId().intValue() == aid.intValue()).collect(Collectors.toList()).size();
                if(size < 1) {
                    //集合没有这个乡的统计信息，手动添加
                    CountVO areaInfo = dao.getPAreaInfo(aid);
                    areaInfo.setCount(0);
                    list.add(areaInfo);
                }
            }
        }
        return list;
    }

    public List<CountVO> addProtector(List<CountVO> list,List<Integer> protectorList) {
        if(list.size() < 1) {
            for (Integer pid:
                 protectorList) {
                CountVO countVO = dao.getProtectorInfo(pid);
                countVO.setCount(0);
                list.add(countVO);
            }
        }else {
            for (Integer pid: protectorList) {
                int size = list.stream().filter(item -> item.getProtectorId().intValue() == pid.intValue()).collect(Collectors.toList()).size();
                if(size < 1) {
                    //集合没有这个防疫员的统计信息，手动添加
                    CountVO countVO = dao.getProtectorInfo(pid);
                    countVO.setCount(0);
                    list.add(countVO);
                }
            }
        }
        return list;
    }
}



package com.statisanalysis.wx.service.impl;

import com.entities.OrgInfo;
import com.orgmanagement.dao.OrgInfoDao;
import com.statisanalysis.dao.HomePageDao;
import com.statisanalysis.utils.DateUtils;
import com.statisanalysis.wx.dao.AntieCountDao;
import com.statisanalysis.wx.dao.DogAndOwnerDao;
import com.statisanalysis.wx.service.IAntieCountService;
import com.statisanalysis.wx.utils.HandleDataUtils;
import com.statisanalysis.wx.vo.CountVO;
import com.utils.OrgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/11/22 16:24
 * @Version 1.0
 */
@Service
public class AntieCountServiceImpl implements IAntieCountService {
    @Autowired
    private AntieCountDao antieCountDao;
    @Autowired
    private DogAndOwnerDao dogAndOwnerDao;
    @Autowired
    private OrgInfoDao sysDeptMapper;
    @Autowired
    private HomePageDao homePageDao;



    @Override
    public Map<String, Object> countInfo(Map<String, Object> params) throws Exception {
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        int roleType = Integer.parseInt(params.get("roleType").toString());
        int orgId = Integer.parseInt(params.get("orgId").toString());
        //查询本组织所在的区域
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
        map.put("myAreaId",myAreaId);       //本组织所在的组织
        List<Integer> myAreaIdList =new ArrayList<>();
        myAreaIdList.add(myAreaId);
        List<CountVO> antieTimesList = new ArrayList<>();   //防疫次数
        List<CountVO> corpseTimesList = new ArrayList<>();  //犬尸处理
        List<CountVO> manureTimesList = new ArrayList<>();  //犬粪处理
        List<CountVO> antigenTimesList = new ArrayList<>(); //犬粪抗原检测
        List<CountVO> anatomyTimesList = new ArrayList<>(); //犬只解剖
        List<CountVO> antibodyTimesList = new ArrayList<>(); //牛羊抗体检测
        List<CountVO> infectionTimesList = new ArrayList<>(); //牛羊脏器处理

        if(roleType == 3 || roleType == 8) {
            //乡级管理员
            int flag = 0;   //0:本乡所有    1：指定防疫员
            List<Integer> protectorList = new ArrayList<>();
            Object protectId = params.get("protectId");
            if(protectId != null && !protectId.toString().trim().equals("")) {
                protectorList.add(Integer.parseInt(protectId.toString()));
                flag = 1;
            }else {
                //第一次加载默认查找所有的防疫员
                protectorList = dogAndOwnerDao.getProtectorIds(orgId);
            }
            map.put("protectorList",protectorList);
            antieTimesList = antieCountDao.getAntieTimesVillage(map);
            corpseTimesList = antieCountDao.getCorpseTimesVillage(map);
            manureTimesList = antieCountDao.getManureTimesVillage(map);

            //防疫员次数为0的手动添加
            addProtector(antieTimesList,protectorList);
            addProtector(corpseTimesList,protectorList);
            addProtector(manureTimesList,protectorList);

            for (CountVO vo:
                 antieTimesList) {
                vo.setVillageName(areaName);
                vo.setVillageId(myAreaId);
            }
            for (CountVO vo:
                 corpseTimesList) {
                vo.setVillageName(areaName);
                vo.setVillageId(myAreaId);
            }
            for (CountVO vo:
                 manureTimesList) {
                vo.setVillageName(areaName);
                vo.setVillageId(myAreaId);
            }
            //处理特殊数据
            if(flag == 0) {
                HandleDataUtils.handleData4(antieTimesList);
                HandleDataUtils.handleData4(corpseTimesList);
                HandleDataUtils.handleData4(manureTimesList);
            }
        }else if(roleType == 5 || roleType == 9){
            //县级管理员
            int flag = 0;   //0：第一次加载或选择乡为不限 1：选择特定的乡
            List<Integer> areaIdList = new ArrayList<>();
            Object areaId = params.get("areaId");
            if(areaId != null && !areaId.toString().trim().equals("")) {
                int vId = Integer.parseInt(areaId.toString());
                //判断该areaId是否是县areaId
                List<Integer> vList = dogAndOwnerDao.getAreaIdList(orgId);
                if(vList != null && vList.contains(vId)) {
                    //查找特定的乡
                    areaIdList.add(vId);
                    flag = 1;
                }else {
                    //乡选择不限
                    areaIdList = dogAndOwnerDao.getAreaIdList(orgId);
                }
            }else {
                //第一次加载或者不限则查找所有乡区域
                areaIdList = dogAndOwnerDao.getAreaIdList(orgId);
            }
            map.put("areaIdList",areaIdList);
            map.put("myAreaId",myAreaId);
            antieTimesList = antieCountDao.getAntieTimesCounty(map);
            corpseTimesList = antieCountDao.getCorpseTimesCounty(map);
            manureTimesList = antieCountDao.getManureTimesCounty(map);
            antigenTimesList = antieCountDao.getAntiegenTimesCounty(map);
            anatomyTimesList = antieCountDao.getAnatomyTimesCounty(map);
            antibodyTimesList = antieCountDao.getAntibodyTimesCounty(map);
            infectionTimesList = antieCountDao.getInfectionTimesCounty(map);
            //防疫次数、犬尸处理、犬粪处理所属乡镇次数为0，手动添加
            addVillage(antieTimesList,areaIdList);
            addVillage(corpseTimesList,areaIdList);
            addVillage(manureTimesList,areaIdList);
            //犬粪抗原、犬只解剖、牛羊抗体、牛羊脏器所属县次数为0，手动添加
            addCounty(antigenTimesList,myAreaIdList);
            addCounty(anatomyTimesList,myAreaIdList);
            addCounty(antibodyTimesList,myAreaIdList);
            addCounty(infectionTimesList,myAreaIdList);


            for (CountVO vo: antieTimesList) {
                vo.setCountyName(areaName);
                vo.setCountyId(myAreaId);
            }
            for (CountVO vo: corpseTimesList) {
                vo.setCountyName(areaName);
                vo.setCountyId(myAreaId);
            }
            for (CountVO vo: manureTimesList) {
                vo.setCountyName(areaName);
                vo.setCountyId(myAreaId);
            }
            for (CountVO vo:antigenTimesList) {
                vo.setCountyName(areaName);
                vo.setCountyId(myAreaId);
            }
            for (CountVO vo:anatomyTimesList) {
                vo.setCountyName(areaName);
                vo.setCountyId(myAreaId);
            }
            for (CountVO vo:antibodyTimesList) {
                vo.setCountyName(areaName);
                vo.setCountyId(myAreaId);
            }
            for (CountVO vo:infectionTimesList) {
                vo.setCountyName(areaName);
                vo.setCountyId(myAreaId);
            }
            if(flag == 0) {
                HandleDataUtils.handleData3(antieTimesList);
                HandleDataUtils.handleData3(corpseTimesList);
                HandleDataUtils.handleData3(manureTimesList);
            }
        }else if(roleType == 6 || roleType == 10) {
            //州级管理员
            int flag = 0;   //0:第一次加载或者县选择不限，1：县选择了乡选择不限，2：县和乡都选择了
            //州级管理员
            List<Integer> areaIdList = new ArrayList<>();   //乡级区域Id
            List<Integer> areaIdList2 = new ArrayList<>();   //县级区域Id
            Integer  antigenStateCount = 0;
            Integer anatomyStateCount = 0;
            Integer antibodyStateCount = 0;
            Integer infectionStateCount = 0;
//            Integer
            Object areaId = params.get("areaId");
            if(areaId != null && !areaId.toString().trim().equals("")) {
                //判断是否是州级区域
                int aId = Integer.parseInt(areaId.toString());
                if(aId == myAreaId.intValue()) {
                    areaIdList = dogAndOwnerDao.getAreaIdList2(orgId);
                    areaIdList2 = dogAndOwnerDao.getMyCountyAreaIdList(orgId);
                    //获取本州犬粪抗原检测次数
                    antigenStateCount = antieCountDao.getMyStateAntiegenTimes(map);
                    anatomyStateCount = antieCountDao.getMyStateAnatomyTimes(map);
                }else {
                    //判断当前areaId是否为县级区域
                    List<Integer> countyAreaIdList = dogAndOwnerDao.getCountyAreaIdList();
                    if(countyAreaIdList.contains(aId)){
                        //是县级区域
                        areaIdList = dogAndOwnerDao.getVillageIdList(aId);
                        areaIdList2.add(aId);
                        flag = 1;
                    }else {
                        //是乡级区域
                        areaIdList.add(aId);
                        Integer pAreaId = dogAndOwnerDao.getPAreaId(aId);
                        areaIdList2.add(pAreaId);
                        flag = 2;
                    }
                }
            }else {
                //第一次加载或县选择不限
                areaIdList = dogAndOwnerDao.getAreaIdList2(orgId);
                areaIdList2 = dogAndOwnerDao.getMyCountyAreaIdList(orgId);
                //获取本州犬粪抗原检测次数
                antigenStateCount = antieCountDao.getMyStateAntiegenTimes(map);
                //获取本州犬只解剖次数
                anatomyStateCount = antieCountDao.getMyStateAnatomyTimes(map);
                //获取本州牛羊抗体检测次数
                antibodyStateCount = antieCountDao.getMyStateAntibodyTimes(map);
                //获取本州牛羊脏器处理次数
                infectionStateCount = antieCountDao.getMyStateInfectionTimes(map);
            }
            map.put("areaIdList",areaIdList);       //乡级areaId
            map.put("areaIdList2",areaIdList2);     //县级areaId
            antieTimesList = antieCountDao.getAntieTimesState(map);
            corpseTimesList = antieCountDao.getCorpseTimesState(map);
            manureTimesList = antieCountDao.getManureTimesState(map);
            antigenTimesList = antieCountDao.getAntiegenTimesState(map);
            anatomyTimesList = antieCountDao.getAnatomyTimesState(map);
            antibodyTimesList = antieCountDao.getAntibodyTimesState(map);
            infectionTimesList = antieCountDao.getInfectionTimesState(map);

            //防疫次数、犬尸处理、犬粪处理所属乡镇次数为0，手动添加
            addVillage(antieTimesList,areaIdList);
            addVillage(corpseTimesList,areaIdList);
            addVillage(manureTimesList,areaIdList);
            //犬粪抗原、犬只解剖、牛羊抗体、牛羊脏器所属县次数为0，手动添加
            addCounty(antigenTimesList,areaIdList2);
            addCounty(anatomyTimesList,areaIdList2);
            addCounty(antibodyTimesList,areaIdList2);
            addCounty(infectionTimesList,areaIdList2);

            for (CountVO vo: antieTimesList) {
                vo.setStateName(areaName);
                vo.setStateId(myAreaId);
            }
            for (CountVO vo: corpseTimesList) {
                vo.setStateName(areaName);
                vo.setStateId(myAreaId);
            }
            for (CountVO vo: manureTimesList) {
                vo.setStateName(areaName);
                vo.setStateId(myAreaId);
            }
            for (CountVO vo: antigenTimesList) {
                vo.setStateName(areaName);
                vo.setStateId(myAreaId);
            }
            for (CountVO vo: anatomyTimesList) {
                vo.setStateName(areaName);
                vo.setStateId(myAreaId);
            }
            for (CountVO vo: antibodyTimesList) {
                vo.setStateName(areaName);
                vo.setStateId(myAreaId);
            }
            for (CountVO vo: infectionTimesList) {
                vo.setStateName(areaName);
                vo.setStateId(myAreaId);
            }

            //处理特殊数据
            if(flag == 0) {
                //添加州、县的统计数据
                HandleDataUtils.handleData(antieTimesList);
                HandleDataUtils.handleData(corpseTimesList);
                HandleDataUtils.handleData(manureTimesList);
                //只添加州的统计数据
                HandleDataUtils.handleData6(antigenTimesList,antigenStateCount);
                HandleDataUtils.handleData6(anatomyTimesList,anatomyStateCount);
                HandleDataUtils.handleData6(antibodyTimesList,antibodyStateCount);
                HandleDataUtils.handleData6(infectionTimesList,infectionStateCount);
            }else if(flag == 1) {
                HandleDataUtils.handleData1(antieTimesList);
                HandleDataUtils.handleData1(corpseTimesList);
                HandleDataUtils.handleData1(manureTimesList);
            }
        }

        //------------
        for (CountVO vo: antieTimesList) {
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
        for (CountVO vo: corpseTimesList) {
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
        for (CountVO vo: manureTimesList) {
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
        for (CountVO vo: antigenTimesList) {
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
        for (CountVO vo: anatomyTimesList) {
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
        for (CountVO vo: antibodyTimesList) {
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
        for (CountVO vo: infectionTimesList) {
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

        //-----------------需求更改-------------------------
        //防疫次数要求查询犬只总数、已防疫数、未防疫数
        //更改方案：在原来数据的基础上，根据查询的areaId分别统计
        Map<String,Object> query = new HashMap<>();
        for (CountVO c: antieTimesList) {
            query.put("searchMonth",searchMonth);
            query.put("nextMonth",nextMonth);
            Integer protectorId = c.getProtectorId();
            if(protectorId != null) {
                query.put("protectorId",protectorId);
            }else {
                Integer villageId = c.getVillageId();
                Integer countyId = c.getCountyId();
                Integer stateId = c.getStateId();
                Integer areaId = 0;
                if(stateId != null) {
                    areaId = stateId;
                }
                if(countyId != null) {
                    areaId = countyId;
                }
                if(villageId != null) {
                    areaId = villageId;
                }
                //通过areaId获取这个区域所有的乡级orgId
                List<Integer> orgList = getOrgList(areaId);
                query.put("orgList",orgList);
            }
            //查找该区域或该防疫员的所有犬只
//            List<TDogInfo> dogList = antieCountDao.getDogList(query);
            List<String> dogList = antieCountDao.getLiveDog(query);
            int total = 0;
            if(dogList == null || dogList.isEmpty()) {
                c.setTotalAmount(0);
                c.setAntiAmount(0);
                c.setUnAntiAmount(0);
                continue;
            }else {
                total = dogList.size();
                c.setTotalAmount(total);
            }
            //查找犬只防疫记录
//            List<String> traceIdList = dogList.stream().map(TDogInfo::getTraceId).collect(Collectors.toList());
            query.put("traceIdList",dogList);
            query.put("searchMonth",searchMonth);
            List<String> maps = homePageDao.counpteAntiAmount(query);
            Integer antiAmount = 0;
            Integer unAntiAmount = 0;
            //计算已防疫和未防疫的犬只数量
            if(maps == null) {
                c.setAntiAmount(0);
                c.setUnAntiAmount(dogList.size());
            }else {
                c.setAntiAmount(maps.size());
                c.setUnAntiAmount(dogList.size() - maps.size());
            }
            query.clear();
        }




        result.put("antieTimesList",antieTimesList);
        result.put("corpseTimesList",corpseTimesList);
        result.put("manureTimesList",manureTimesList);
        result.put("antigenTimesList",antigenTimesList);
        result.put("anatomyTimesList",anatomyTimesList);
        result.put("antibodyTimesList",antibodyTimesList);
        result.put("infectionTimesList",infectionTimesList);
        return result;
    }

    public List<Integer> computeAmounts(List<Map<String, Object>> maps,List<String> traceIdList,String searchMonth) throws ParseException {
        List<Integer> res = new ArrayList<>();
        int antiAmount = 0;
        if(traceIdList == null || traceIdList.isEmpty()) {
            res.add(0); //已防疫数
            res.add(0); //未防疫数
            return res;
        }
        if(maps == null || maps.isEmpty()) {
            res.add(0);
            res.add(traceIdList.size());
            return res;
        }
        for (Map<String, Object> map: maps) {
            String antieDate = map.get("antieDate").toString();
            if(antieDate != null) {
                String antiStr = antieDate.substring(0, 7);
                if(searchMonth.equals(antiStr)) {
                    antiAmount++;
                }
            }
        }
        res.add(antiAmount);
        res.add(traceIdList.size() - antiAmount);   //未防疫犬只数 = 总的犬只数 - 已防疫犬只数
        return res;
    }

    public List<Integer> getOrgList(Integer areaId) {
        List<Integer> orgList = new ArrayList<>();
        if(areaId == null) {
            return orgList;
        }
        List<OrgInfo> orgInfos = sysDeptMapper.orgList();
        List<Integer> orgIdList = antieCountDao.getOrgIdByAreaId(areaId);
        for (Integer orgId:
             orgIdList) {
            //获取每个组织的下属乡级组织
            List<Integer> lists = OrgUtils.orgReverse(orgInfos, Integer.parseInt(orgId.toString()), new ArrayList<>());
            orgList.addAll(lists);
        }
        return orgList;
    }

    public List<CountVO> addCounty(List<CountVO> list,List<Integer> areaIdList) {
        if(list.size() < 1) {
            for (Integer aid:
                 areaIdList) {
                if(aid == null) {
                    continue;
                }
                CountVO countVO = dogAndOwnerDao.getAreaInfo(aid);
                countVO.setCount(0);
                list.add(countVO);
            }
        }else {
            for (Integer aid: areaIdList) {
                int size = list.stream().filter(item -> item.getCountyId().intValue() == aid.intValue()).collect(Collectors.toList()).size();
                if(size < 1) {
                    //集合没有这个县的统计信息，手动添加
                    CountVO areaInfo = dogAndOwnerDao.getAreaInfo(aid);
                    areaInfo.setCount(0);
                    list.add(areaInfo);
                }
            }
        }
        return list;
    }

    public List<CountVO> addVillage(List<CountVO> list,List<Integer> areaIdList) {
        if(list.size() < 1) {
            //没有查询出数据，手动添加所有的乡数据
            for (Integer aid: areaIdList) {
                CountVO areaInfo = dogAndOwnerDao.getPAreaInfo(aid);
                areaInfo.setCount(0);
                list.add(areaInfo);
            }
        }else{
            //查询出了所有或者部分乡数据，判断是否需要添加
            for (Integer aid: areaIdList) {
                int size = list.stream().filter(item -> item.getVillageId().intValue() == aid.intValue()).collect(Collectors.toList()).size();
                if(size < 1) {
                    //集合没有这个乡的统计信息，手动添加
                    CountVO areaInfo = dogAndOwnerDao.getPAreaInfo(aid);
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
                CountVO countVO = dogAndOwnerDao.getProtectorInfo(pid);
                countVO.setCount(0);
                list.add(countVO);
            }
        }else {
            for (Integer pid: protectorList) {
                int size = list.stream().filter(item -> item.getProtectorId().intValue() == pid.intValue()).collect(Collectors.toList()).size();
                if(size < 1) {
                    //集合没有这个防疫员的统计信息，手动添加
                    CountVO countVO = dogAndOwnerDao.getProtectorInfo(pid);
                    countVO.setCount(0);
                    list.add(countVO);
                }
            }
        }
        return list;
    }
}

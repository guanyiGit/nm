package com.statisanalysis.serivce.impl;

import com.entities.AreaInfo;
import com.entities.OrgInfo;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.statisanalysis.dao.AntiepidemicCountDao;
import com.statisanalysis.dao.DogAmountDao;
import com.statisanalysis.entity.DrugNumVO;
import com.statisanalysis.entity.DrugVO;
import com.statisanalysis.serivce.IAntiepidemicCountService;
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
 * @Date: 2018/10/5 7:51
 * @Version 1.0
 */
@Service
public class AntiepidemicCountServiceImpl implements IAntiepidemicCountService {
    private static Logger logger = LoggerFactory.getLogger(AntiepidemicCountServiceImpl.class);
    @Autowired
    private AntiepidemicCountDao antiepidemicCountDao;
    @Autowired
    private DogAmountDao dogAmountDao;
    @Autowired
    private OrgInfoDao orgInfoDao;


    @Override
    public Map<String,Object> countAntiepidemicTimes(Map<String, Object> params) throws Exception {
        Map<String,Object> result = new HashMap<>();
        List<String> townList = new ArrayList<>();
        List<List> numList = new ArrayList<>();
        List<String> times = DateUtils.getTimes(params);

        String startTime = times.get(0);
        String endTime = times.get(1);
        String nextMonth = times.get(2);

        List<String> monthBetween = DateUtils.getMonthBetween(startTime, endTime);
        //手动将月份向后增加一个月方便数据库查询
        params.put("nextMonth",nextMonth);
        logger.info("AntiepidemicCountServiceImpl.countAntiepidemicTimes()|params = {}", params);
//        int role = 3;
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();

        if(type == 2) {
            //防疫员
            params.put("userId",user.getUserId());
            List<Date> infoByEndTime = antiepidemicCountDao.getInfoByEndTime(params);
            List<Integer> list = getPerMonthAntiTimes(monthBetween, infoByEndTime);
            numList.add(list);
        }else if(type == 3 || type == 8){
            //乡级组织
//            List<Integer> orgList = new ArrayList<>();
//            orgList.add(20);
//            orgList.add(21);
            List<OrgInfo> orgInfos =orgInfoDao.orgList();
            List<Integer> orgList = OrgUtils.orgReverse(orgInfos, Integer.parseInt(user.getDeptId().toString()), new ArrayList<>());
            params.put("orgList",orgList);
            List<Date> infoByEndTime = antiepidemicCountDao.getInfoByEndTime(params);
            List<Integer> list = getPerMonthAntiTimes(monthBetween, infoByEndTime);
            numList.add(list);
        }else if(type == 5 || type ==9) {
            //县级
            Map<String,Object> map =new HashMap<>();
//            int orgId = 16;
            int orgId = Integer.parseInt(user.getDeptId().toString());
            map.put("orgId",orgId);
            List<AreaInfo> areaList = dogAmountDao.getAreaInfoByOrgId(map);
            for (AreaInfo area:
                 areaList) {
                townList.add(area.getName());
                params.put("areaId",area.getId());
                List<Date> infoByEndTime2 = antiepidemicCountDao.getInfoByEndTime2(params);
                List<Integer> perMonthAntiTimes = getPerMonthAntiTimes(monthBetween, infoByEndTime2);
                numList.add(perMonthAntiTimes);
            }

        }else if(type == 6 || type == 10){
            //州级
            Map<String,Object> map =new HashMap<>();
//            int orgId = 3;
            int orgId = Integer.parseInt(user.getDeptId().toString());
            map.put("orgId",orgId);
            List<AreaInfo> areaList = dogAmountDao.getAreaInfoByOrgId(map);
            for (AreaInfo area:
                    areaList) {
                townList.add(area.getName());
                params.put("areaId",area.getId());
                List<Date> infoByEndTime2 = antiepidemicCountDao.getInfoByEndTime3(params);
                List<Integer> perMonthAntiTimes = getPerMonthAntiTimes(monthBetween, infoByEndTime2);
                numList.add(perMonthAntiTimes);
            }
        }
        result.put("townList",townList);
        result.put("monthList",monthBetween);
        result.put("numList",numList);
        return result;
    }

    /**
     * 获取使用量前十的药品
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public Map<String,Object> getTopTenDrug(Map<String, Object> map) throws Exception {
        Map<String,Object> result = new HashMap<>();
        List<String> times = DateUtils.getTimes(map);
        String startTime = times.get(0);
        String endTime = times.get(1);
        String nextMonth = times.get(2);
        map.put("nextMonth",nextMonth);
        map.put("startTime",startTime);
        int role = 0;
        List<DrugNumVO> topTenDrug = new ArrayList<>();
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type == 2) {
            //防疫员
            map.put("userId",user.getUserId());
            topTenDrug = antiepidemicCountDao.getTopTenDrug(map);
        }else {
            //其他角色
//            List<Integer> orgList =new ArrayList<>();
//            orgList.add(20);
//            orgList.add(21);
//            orgList.add(22);
            List<OrgInfo> orgInfos = orgInfoDao.orgList();
            if(user.getDeptId() == null) {
                return null;
            }
            List<Integer> orgList = OrgUtils.orgReverse(orgInfos, Integer.parseInt(user.getDeptId().toString()), new ArrayList<>());
            map.put("orgList",orgList);
            topTenDrug = antiepidemicCountDao.getTopTenDrug(map);
        }
        List<String> drugNameList =new ArrayList<>();
        List<Integer> drugNumList = new ArrayList<>();
        for (DrugNumVO drugNumVO:
             topTenDrug) {
            drugNameList.add(drugNumVO.getDrugName());
            drugNumList.add(drugNumVO.getDrugNum());
        }
        result.put("drugNameList",drugNameList);
        result.put("drugNumList",drugNumList);
        return result;
    }

    public List<Integer> getPerMonthAntiTimes(List<String> monthBetween,List<Date> dateList) {
        List<Integer> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        for (String month:
             monthBetween) {
            int res1 = DateUtils.getNumOfDate(month);
            int num = 0;
            for(Date date: dateList) {
                if(date != null) {
                    String format = sdf.format(date);
                    int res2 = DateUtils.getNumOfDate(format);
                    if(res1 == res2) {
                        num++;
                    }
                }
            }
            list.add(num);
        }
        return list;
    }


    /**
     * 药品使用情况统计
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List<List> countDrugUsage(Map<String, Object> params) throws Exception {
        List<String> times = DateUtils.getTimes(params);
        String startTime = times.get(0);
        String endTime = times.get(1);
        String nextMonth = times.get(2);
        List<String> monthBetween = DateUtils.getMonthBetween(startTime, endTime);
        params.put("nextMonth",nextMonth);
        params.put("startTime",startTime);

        List<String> drugNames = antiepidemicCountDao.getAllNames(params);
        List<DrugVO> drugs = new ArrayList<>();
//        int role = 1;
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type == 2) {
            //防疫员
            params.put("userId",user.getUserId());
            drugs = antiepidemicCountDao.getDrugUse(params);
        }else {
            //其他组织角色
//            List<Integer> orgList = new ArrayList<>();
//            orgList.add(20);
//            orgList.add(21);
//            orgList.add(22);
//            orgList.add(23);
            List<OrgInfo> orgInfos = orgInfoDao.orgList();
            List<Integer> orgList = OrgUtils.orgReverse(orgInfos, Integer.parseInt(user.getDeptId().toString()), new ArrayList<>());
            params.put("orgList",orgList);
            drugs = antiepidemicCountDao.getDrugUse(params);
        }

        List<List> result = new ArrayList<>();
        List<String> headList = new ArrayList<>();
        //添加表格头部信息
        headList.add("时间");
        for (String drugName:
             drugNames) {
            headList.add(drugName);
        }
        result.add(headList);

        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM");
        for (String perMonth:monthBetween) {
            List<String> list = new ArrayList<>();
            list.add(perMonth);     //每条数据开始就是其所对应的月份
            int res1 = DateUtils.getNumOfDate(perMonth);
            for(String drugName : drugNames) {
                int num =0;
                for(DrugVO drug : drugs) {
                    String dN = drug.getDrugName();
                    if(dN != null && dN.equals(drugName)) {
                        Date createDate = drug.getCreateDate();
                        String format = sdf.format(createDate);
                        int res2 = DateUtils.getNumOfDate(format);
                        if(res1 == res2) {  //表示该药品的使用属于当前月份
                            num++;
                        }
                    }
                }
                list.add(String.valueOf(num));
            }
            result.add(list);
        }

        return result;
    }
}

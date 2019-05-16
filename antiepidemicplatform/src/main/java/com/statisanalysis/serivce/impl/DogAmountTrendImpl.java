package com.statisanalysis.serivce.impl;

import com.entities.AreaInfo;
import com.entities.OrgInfo;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.statisanalysis.dao.DogAmountDao;
import com.statisanalysis.entity.DogNumVO;
import com.statisanalysis.entity.DogVO;
import com.statisanalysis.serivce.IDogAmountService;
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
 * @Author: linchong
 * @Date: 2018/9/30 10:30
 * @Version 1.0
 */

@Service
public class DogAmountTrendImpl implements IDogAmountService {
    private Logger logger = LoggerFactory.getLogger(DogAmountTrendImpl.class);
    @Autowired
    private DogAmountDao dogAmountDao;
    @Autowired
    private OrgInfoDao orgInfoDao;


    /**
     * 查询犬只数量趋势
     *
     * @param params
     * @return
     */
    @Override
    public Map<String,Object> getDogTrend(Map<String, Object> params) throws Exception {
        Map<String,Object> result = new HashMap<>();
        List<List> lists = new ArrayList<>();
        List<String> townList = new ArrayList<>();
        //初始化查询区间，如果没有指定则查询最近一年
        List<String> times = DateUtils.getTimes(params);
        String startTime = times.get(0);
        String endTime = times.get(1);
        String nextMonth = times.get(2);
        List<String> monthBetween = DateUtils.getMonthBetween(startTime, endTime);
        params.put("nextMonth",nextMonth);

        int type = ShiroUtils.getUser().getRoles().get(0).getType();
        List<DogVO> dogVOList = null;
        if(type == 2) {
            //如果是防疫员
            params.put("userId",ShiroUtils.getUserId());
            dogVOList = dogAmountDao.getDogAmount1(params);
            List<String> perMonthList = getPerMonthLiveDogNum(monthBetween, dogVOList);
            lists.add(perMonthList);
        }else if(type == 3 || type == 8){
            //如果是乡级组织
//            params.put("orgId",20);
            params.put("orgId",ShiroUtils.getUser().getDeptId());
            dogVOList = dogAmountDao.getDogAmount2(params);
            List<String> perMonthList = getPerMonthLiveDogNum(monthBetween, dogVOList);
            lists.add(perMonthList);
        }else if(type == 5 || type == 9){
            //如果是县级组织
            //查询该县组组织下的乡组织所在的区域
            params.put("orgId",ShiroUtils.getUser().getDeptId());
            List<AreaInfo> areaInfoList = dogAmountDao.getAreaInfoByOrgId(params);
            for (AreaInfo areaInfo: areaInfoList) {
                List<String> perMonthList = new ArrayList<>();
                townList.add(areaInfo.getName());   //添加乡镇区域名称
                params.put("areaId",areaInfo.getId());
                List<DogVO> voList = dogAmountDao.getDogAmount3(params);
                perMonthList = getPerMonthLiveDogNum(monthBetween, voList);
                lists.add(perMonthList);        //添加一个乡镇的犬只趋势信息
            }
        }else if(type == 6 || type == 10){
            //如果是州级组织
            params.put("orgId",ShiroUtils.getUser().getDeptId());
            List<AreaInfo> areaInfoList = dogAmountDao.getAreaInfoByOrgId(params);
            for (AreaInfo areaInfo: areaInfoList) {
                List<String> perMonthList = new ArrayList<>();
                townList.add(areaInfo.getName());   //添加乡镇区域名称
                params.put("areaId",areaInfo.getId());
                List<DogVO> voList = dogAmountDao.getDogAmount4(params);
                perMonthList = getPerMonthLiveDogNum(monthBetween, voList);
                lists.add(perMonthList);        //添加一个乡镇的犬只趋势信息
            }
        }
        result.put("townList",townList);
        result.put("monthList",monthBetween);
        result.put("numList",lists);
        return result;
    }

    /**
     * 统计犬只数量（存栏、新增、注销、死亡）
     *
     * @param params
     * @return
     */
    @Override
    public List<DogNumVO> getDogNum(Map<String, Object> params) throws Exception {
        List<DogNumVO> result = new ArrayList<>();
        //初始化查询区间，如果没有指定则查询最近一年
        List<String> times = DateUtils.getTimes(params);
        String startTime = times.get(0);
        String endTime = times.get(1);
        String nextMonth = times.get(2);
        List<String> monthBetween = DateUtils.getMonthBetween(startTime, endTime);
        params.put("nextMonth",nextMonth);
        int role = 1;
        List<String> liveDogNum = new ArrayList<>();
        List<Integer> newDogNum = new ArrayList<>();
        List<Integer> deadDogNum = new ArrayList<>();
        List<Integer> cancelDogNum = new ArrayList<>();
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type == 2) {
            //防疫员
            params.put("userId",user.getUserId());
            //查询犬只存栏数量
            List<DogVO> liveNumber = dogAmountDao.getLiveNumber(params);
            liveDogNum = getPerMonthLiveDogNum(monthBetween, liveNumber);
            //查询犬只新增数量
            params.put("startTime",startTime);
            List<Date> dateList = dogAmountDao.getNewNumber(params);
            newDogNum = getNums(monthBetween, dateList);
            //查询犬只死亡数量
//            params.put("operatorId",1);
            List<Date> deadNumber = dogAmountDao.getDeadNumber(params);
            deadDogNum = getNums(monthBetween, deadNumber);
            //查询犬只注销数量
            List<Date> cancelNumber = dogAmountDao.getCancelNumber(params);
            cancelDogNum = getNums(monthBetween, cancelNumber);
        }else{
            //乡级组织
            List<OrgInfo> orgInfos = orgInfoDao.orgList();
//            Long userId = ShiroUtils.getUserId();
//            String s = ShiroUtils.getUserId().toString();
            List<Integer> lists = OrgUtils.orgReverse(orgInfos, Integer.parseInt(user.getDeptId().toString()), new ArrayList<>());
            params.put("lists",lists);
            //查询犬只存栏数量
            List<DogVO> liveNumber = dogAmountDao.getLiveNumber(params);
            liveDogNum = getPerMonthLiveDogNum(monthBetween, liveNumber);
            //查询犬只新增数量
            params.put("startTime",startTime);
            List<Date> dateList = dogAmountDao.getNewNumber(params);
            newDogNum = getNums(monthBetween, dateList);
            //查询犬只死亡数量
            List<Date> deadNumber = dogAmountDao.getDeadNumber(params);
            deadDogNum = getNums(monthBetween, deadNumber);
            //查询犬只注销数量
            List<Date> cancelNumber = dogAmountDao.getCancelNumber(params);
            cancelDogNum = getNums(monthBetween, cancelNumber);
        }
        //转换
        for (int i = 0; i < monthBetween.size(); i++) {
            DogNumVO dogNumVO = new DogNumVO();
            dogNumVO.setPerMonth(monthBetween.get(i));
            dogNumVO.setLiveNum(Integer.parseInt(liveDogNum.get(i)));
            dogNumVO.setNewNum(newDogNum.get(i));
            dogNumVO.setDeadNum(deadDogNum.get(i));
            dogNumVO.setCancelNum(cancelDogNum.get(i));
            result.add(dogNumVO);
        }
        return result;
    }

    //统计每个月犬只存栏数量
    public List<String> getPerMonthLiveDogNum(List<String> monthBetween,List<DogVO> dogVOList) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        List<String> list = new ArrayList<>();
        for (String month: monthBetween) {
            int res1 = DateUtils.getNumOfDate(month);
            int num = 0;
            for (DogVO dog : dogVOList) {
                if(dog.getCreateDate() == null) {
                    continue;
                }
                String format = sdf.format(dog.getCreateDate());
                int res2 = DateUtils.getNumOfDate(format);
                int res3 = Integer.MAX_VALUE;   //设置狗的死亡时间为Integer的最大值
                if (dog.getDealTime() != null) {
                    //计算狗的死亡时间
                    res3 = DateUtils.getNumOfDate(sdf.format(dog.getDealTime()));
                }
                //统计当前月犬只现存数量
                if (res2 <= res1 && res1 < res3) {
                    num++;
                }
            }
            list.add(String.valueOf(num));
        }
        return list;
    }
    //统计每个月犬只新增、注销、死亡数量
    public List<Integer> getNums(List<String> monthBetween,List<Date> dateList) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        List<Integer> list = new ArrayList<>();
        for (String month:
             monthBetween) {
            int res1 = DateUtils.getNumOfDate(month);
            int num = 0;
            for (Date date:
                 dateList) {
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

}

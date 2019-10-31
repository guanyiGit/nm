package com.statisanalysis.wx.service.impl;

import com.entities.ActivityInfoDO;
import com.entities.OrgInfo;
import com.orgmanagement.dao.OrgInfoDao;
import com.statisanalysis.utils.DateUtils;
import com.statisanalysis.wx.dao.ActiviCountDao;
import com.statisanalysis.wx.service.IActiviCountService;
import com.statisanalysis.wx.vo.Result;
import com.utils.orgtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/11/26 14:53
 * @Version 1.0
 */
@Service
public class ActiviCountServiceImpl implements IActiviCountService {
    @Autowired
    private ActiviCountDao activiCountDao;
    @Autowired
    private OrgInfoDao sysDeptMapper;

    @Override
    public Map<String, Object> getActiviInfo(Map<String, Object> params) throws Exception {
        Map<String,Object> res = new HashMap<>();
        List<Result> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        String searchMonth = "";
        List<Integer> orgList = new ArrayList<>();
        int myOrgId = Integer.parseInt(params.get("myOrgId").toString());
        Object month = params.get("month");
        Object orgId = params.get("orgId");
        if(month != null && !month.toString().trim().equals("")) {
            searchMonth = month.toString();
        }else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String nextMonth = sdf.format(new Date());
            searchMonth = DateUtils.getLastMonth(nextMonth, 0, -1, 0);
        }
        String currentYear = searchMonth.split("-")[0];
        String currentMonth = searchMonth.split("-")[1];
        res.put("currentYear",currentYear);
        res.put("currentMonth",currentMonth);
        if(orgId != null && !orgId.toString().trim().equals("")) {
            orgList.add(Integer.parseInt(params.get("orgId").toString()));
        }else {
            List<OrgInfo> orgInfos = sysDeptMapper.initOrgSelect(new HashMap<>());
            List<OrgInfo> depts = orgtills.getDeptsById(orgInfos, myOrgId, new ArrayList<>());
            orgList = depts.stream().map(OrgInfo::getId).collect(Collectors.toList());
        }
        map.put("searchMonth",searchMonth);
        map.put("orgList",orgList);
        List<ActivityInfoDO> activiCount = activiCountDao.getActiviCount(map);
        int times0 = 0;
        int times1 = 0;
        if(activiCount != null && activiCount.size() > 0) {
            times0 = activiCount.stream().filter(item -> item.getActivityType().equals("0")).collect(Collectors.toList()).size();
            times1 = activiCount.stream().filter(item -> item.getActivityType().equals("1")).collect(Collectors.toList()).size();
        }
        Result r1 = new Result("宣传活动举办次数",times0);
        Result r2 = new Result("培训活动举办次数",times1);
        list.add(r1);
        list.add(r2);
        res.put("list",list);
        return res;
    }
}

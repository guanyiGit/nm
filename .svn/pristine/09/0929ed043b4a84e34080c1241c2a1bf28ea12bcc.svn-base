package com.statisanalysis.wx.service.impl;

import com.entities.OrgInfo;
import com.orgmanagement.dao.OrgInfoDao;
import com.statisanalysis.utils.DateUtils;
import com.statisanalysis.wx.dao.GoodsCountDao;
import com.statisanalysis.wx.service.IGoodsCountService;
import com.statisanalysis.wx.vo.GobalValues;
import com.statisanalysis.wx.vo.Result;
import com.utils.orgtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/11/26 11:25
 * @Version 1.0
 */
@Service
public class GoodsCountServiceImpl implements IGoodsCountService {
    @Autowired
    private GoodsCountDao goodsCountDao;
    @Autowired
    private OrgInfoDao sysDeptMapper;

    @Override
    public Map<String,Object> countGoods(Map<String, Object> params) throws Exception {
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
        List<Result> goodsInfo = goodsCountDao.getGoodsInfo(map);
        Integer deviceDistri = goodsCountDao.getDeviceDistri(map);
        if(goodsInfo != null && goodsInfo.size() > 0) {
            for (Result r:
                    goodsInfo) {
                if(Integer.parseInt(r.getName()) == GobalValues.GOODS_TYPE1) {
                    r.setName("药品发放数量");
                    list.add(r);
                    continue;
                }
                if(Integer.parseInt(r.getName()) == GobalValues.GOODS_TYPE2) {
                    r.setName("实验试剂发放数量");
                    list.add(r);
                    continue;
                }
                if(Integer.parseInt(r.getName()) == GobalValues.GOODS_TYPE3) {
                    r.setName("个人防护用品发放数量");
                    list.add(r);
                    continue;
                }
            }
        }else {
            Result r1 = new Result("药品发放数量",0);
            Result r2 = new Result("实验试剂发放数量",0);
            Result r3 = new Result("个人防护用品发放数量",0);
            list.add(r1);
            list.add(r2);
            list.add(r3);
        }

        Result result = new Result("犬只项圈发放数量",deviceDistri == null ? 0 : deviceDistri);
        list.add(result);
        res.put("list",list);
        return res;
    }
}

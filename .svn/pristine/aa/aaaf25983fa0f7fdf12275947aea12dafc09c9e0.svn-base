package com.statisanalysis.serivce.impl;

import com.entities.OrgInfo;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.statisanalysis.dao.OwnerCountDao;
import com.statisanalysis.entity.Data;
import com.statisanalysis.serivce.IOwnerCountService;
import com.utils.ShiroUtils;
import com.utils.OrgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: linchong
 * @Date: 2018/9/29 13:57
 * @Version 1.0
 */
@Service
public class OwnerCountServiceImpl implements IOwnerCountService {
    private Logger logger = LoggerFactory.getLogger(IOwnerCountService.class);
    @Autowired
    private OwnerCountDao ownerCountDao;
    @Autowired
    private OrgInfoDao orgInfoDao;

    @Override
    public List<Data> getOwnerSex() {
        Map<String,Object> map = new HashMap<>();
        //两种情况，角色为防疫员，其他角色（其他组织角色）
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type == 2) {
            map.put("userId",user.getUserId());
        }else {
            List<OrgInfo> orgInfos = orgInfoDao.orgList();
            Long orgId = user.getDeptId();
            List<Integer> subOrgList = OrgUtils.orgReverse(orgInfos, Integer.parseInt(orgId.toString()), new ArrayList<>());
            map.put("lists",subOrgList);
        }
        List<Data> sexList = ownerCountDao.getOwnerSex(map);
        for (int i = 0; i < sexList.size(); i++) {
            Data data = sexList.get(i);
            if(data.getName().equals("0")) {
                data.setName("男");
            }else {
                data.setName("女");
            }
        }
        return sexList;
    }

    @Override
    public List<Data> getOwnerAge() {
        Map<String,Object> map = new HashMap<>();
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type == 2) {
            map.put("userId",user.getUserId());
        }else {
            List<OrgInfo> orgInfos = orgInfoDao.orgList();
            Long orgId = user.getDeptId();
            List<Integer> subOrgList = OrgUtils.orgReverse(orgInfos, Integer.parseInt(orgId.toString()), new ArrayList<>());
            map.put("lists",subOrgList);
        }
        List<Integer> ownerAge = ownerCountDao.getOwnerAge(map);
        //统计
        int[] arrAge = new int[6];
        for (int i = 0; i < ownerAge.size(); i++) {
            Integer age = ownerAge.get(i);
            if(age == null || age <= 0) {
                continue;
            }
            if(age <= 20) {
                arrAge[0]++;
            }else if(age >= 21 && age <= 30) {
                arrAge[1]++;
            }else if(age >= 31 && age <= 40){
                arrAge[2]++;
            }else if(age >= 41 &&age <= 50){
                arrAge[3]++;
            }else if(age >= 51 &&age <= 60){
                arrAge[4]++;
            }else if(age >= 60){
                arrAge[5]++;
            }
        }
        List<Data> list = new ArrayList<>();
        Object o = ShiroUtils.getSubjct().getSession().getAttribute("type");
        int langType = 1;   //默认是中文
        if(o != null) {
            langType = Integer.parseInt(o.toString());
        }
        //多语言处理
        multilangAge(arrAge,list,langType);
        //如果数量为零，那这个年龄段的数据不显示
//        if(arrAge[0] !=  0){
//            list.add(new Data("20岁以下",arrAge[0]));
//        }
//        if(arrAge[1] !=  0){
//            list.add(new Data("21~30岁",arrAge[1]));
//        }
//        if(arrAge[2] !=  0){
//            list.add(new Data("31~40岁",arrAge[2]));
//        }
//        if(arrAge[3] !=  0){
//            list.add(new Data("41~50岁",arrAge[3]));
//        }
//        if(arrAge[4] !=  0){
//            list.add(new Data("51~60岁",arrAge[4]));
//        }
//        if(arrAge[5] !=  0){
//            list.add(new Data("60岁以上",arrAge[5]));
//        }
        return list;
    }

    public void multilangAge(int[] arrAge,List<Data> list,int type) {
        //如果数量为零，那这个年龄段的数据不显示
        if( type == 1) {
            if(arrAge[0] !=  0){
                list.add(new Data("20岁以下",arrAge[0]));
            }
            if(arrAge[1] !=  0){
                list.add(new Data("21~30岁",arrAge[1]));
            }
            if(arrAge[2] !=  0){
                list.add(new Data("31~40岁",arrAge[2]));
            }
            if(arrAge[3] !=  0){
                list.add(new Data("41~50岁",arrAge[3]));
            }
            if(arrAge[4] !=  0){
                list.add(new Data("51~60岁",arrAge[4]));
            }
            if(arrAge[5] !=  0){
                list.add(new Data("60岁以上",arrAge[5]));
            }
        } else if(type == 2) {
            //藏语
            if(arrAge[0] !=  0){
                list.add(new Data("ལོ་༢༠ཡི་མན། ",arrAge[0]));
            }
            if(arrAge[1] !=  0){
                list.add(new Data("ལོ་༢༡ནས་༣༠བར། ",arrAge[1]));
            }
            if(arrAge[2] !=  0){
                list.add(new Data("ལོ་༣༡ནས་༤༠བར། ",arrAge[2]));
            }
            if(arrAge[3] !=  0){
                list.add(new Data("ལོ་༤༡ནས་༥༠བར། ",arrAge[3]));
            }
            if(arrAge[4] !=  0){
                list.add(new Data("ལོ་༥༡ནས་༦༠བར། ",arrAge[4]));
            }
            if(arrAge[5] !=  0){
                list.add(new Data("ལོ་༦༠ཡི་ཡན། ",arrAge[5]));
            }
        } else if (type == 3) {
            //蒙语还未翻译，先赋值为中文
            if(arrAge[0] !=  0){
                list.add(new Data("20岁以下",arrAge[0]));
            }
            if(arrAge[1] !=  0){
                list.add(new Data("21~30岁",arrAge[1]));
            }
            if(arrAge[2] !=  0){
                list.add(new Data("31~40岁",arrAge[2]));
            }
            if(arrAge[3] !=  0){
                list.add(new Data("41~50岁",arrAge[3]));
            }
            if(arrAge[4] !=  0){
                list.add(new Data("51~60岁",arrAge[4]));
            }
            if(arrAge[5] !=  0){
                list.add(new Data("60岁以上",arrAge[5]));
            }
        }

    }

    @Override
    public Map<String,List<String>> getOwnerAreaInfo(Map<String,Object> map) {
        //两种角色：县级组织，州级组织
        //县级组织
//        map.put("orgId",16);
        //州级组织
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        List<OrgInfo> orgInfos = orgInfoDao.orgList();
        Long orgId = user.getDeptId();
        List<Integer> subOrgList = OrgUtils.orgReverse(orgInfos, Integer.parseInt(orgId.toString()), new ArrayList<>());
        List<Data> ownerAreaInfo1 = new ArrayList<>();
        if(type == 5 || type == 9) {
            map.put("orgId",orgId);
            ownerAreaInfo1 = ownerCountDao.getOwnerAreaInfo1(map);
        }else if(type == 6 || type == 10){
            map.put("lists",subOrgList);
            ownerAreaInfo1 = ownerCountDao.getOwnerAreaInfo2(map);
        }else {
            //其他角色返回null
            return null;
        }
        Map<String,List<String>> result = new HashMap<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (Data data:
             ownerAreaInfo1) {
            list1.add(data.getName());
            list2.add(data.getValue().toString());
        }
        result.put("areaInfo",list1);
        result.put("numInfo",list2);
        return result;
    }
}

package com.statisanalysis.wx.controller;

import com.orgmanagement.domain.UserDO;
import com.statisanalysis.wx.service.IDogAndOwnerService;
import com.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 犬只犬主统计
 * @Description
 * @Author: linchong
 * @Date: 2018/11/21 14:11
 * @Version 1.0
 */
@Controller
@RequestMapping("/biz/dogAndOwner")
public class DogAndOwnerController {
    @Autowired
    private IDogAndOwnerService service;

    /**
     * 统计犬只存栏数量、犬主数量、流浪犬处理数量
     * @param map
     * @return
     */
    @RequestMapping("/countInfo")
    @ResponseBody
    public Map<String,Object> countInfo(@RequestParam Map<String,Object> map) {
        Map<String,Object> res = new HashMap<>();
        UserDO user = ShiroUtils.getUser();
        map.put("roleType", user.getRoles().get(0).getType());
        map.put("orgId",user.getDeptId());
        map.put("myAreaId",user.getAreaId());
        map.put("areaName",user.getAreaName());
        try {
            res = service.countInfo(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}

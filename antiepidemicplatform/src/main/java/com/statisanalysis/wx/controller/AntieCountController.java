package com.statisanalysis.wx.controller;

import com.orgmanagement.domain.UserDO;
import com.statisanalysis.wx.service.IAntieCountService;
import com.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/11/22 16:22
 * @Version 1.0
 */
@Controller
@RequestMapping("/biz/antieCount")
public class AntieCountController {
    @Autowired
    private IAntieCountService antieCountService;

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
            res = antieCountService.countInfo(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}

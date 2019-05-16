package com.statisanalysis.wx.controller;

import com.orgmanagement.domain.UserDO;
import com.statisanalysis.wx.service.IActiviCountService;
import com.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/11/26 14:53
 * @Version 1.0
 */
@Controller
@RequestMapping("/biz/activiCount")
public class ActiviCountController {
    @Autowired
    private IActiviCountService activiCountService;

    @RequestMapping("/countInfo")
    @ResponseBody
    public Map<String,Object> countInfo(@RequestParam Map<String,Object> map) {
        UserDO user = ShiroUtils.getUser();
        map.put("myOrgId",user.getDeptId());
        Map<String, Object> res = null;
        try {
            res = activiCountService.getActiviInfo(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  res;
    }
}

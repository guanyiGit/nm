package com.statisanalysis.controller;

import com.orgmanagement.domain.UserDO;
import com.statisanalysis.entity.Data;
import com.statisanalysis.serivce.IOwnerCountService;
import com.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: linchong
 * @Date: 2018/9/29 13:59
 * @Version 1.0
 */
@Controller
@RequestMapping("/biz/ownerCount")
public class OwnerCountController {
    @Autowired
    private IOwnerCountService ownerCountService;

    @GetMapping()
    public String ownerCount(Model model) {
        UserDO user = ShiroUtils.getUser();
        model.addAttribute("user",user);
        return "statisanalysis/ownerCount";
    }

    /**
     * 统计犬主性别分布
     * @return
     */
    @RequestMapping("/getOwnerSex")
//    @RequiresPermissions("biz:ownerCount:getOwnerSex")
    @ResponseBody
    public List<Data> getOwnerSex(){
        return ownerCountService.getOwnerSex();
    }

    /**
     * 统计犬主年龄分布
     * @return
     */
    @RequestMapping("/getOwnerAge")
//    @RequiresPermissions("biz:ownerCount:getOwnerAge")
    @ResponseBody
    public List<Data> getOwnerAge() {
        List<Data> ownerAge = ownerCountService.getOwnerAge();
        return ownerAge;
    }

    @RequestMapping("/getOwnerAreaInfo")
//    @RequiresPermissions("biz:ownerCount:getOwnerAreaInfo")
    @ResponseBody
    public Map<String, List<String>> getOwnerAreaInfo(@RequestParam Map<String,Object> map) {
        Map<String, List<String>> ownerAreaInfo = ownerCountService.getOwnerAreaInfo(map);
        return ownerAreaInfo;
    }
}

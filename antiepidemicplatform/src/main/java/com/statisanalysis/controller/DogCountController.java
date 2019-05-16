package com.statisanalysis.controller;

import com.entities.OrgInfo;
import com.orgmanagement.domain.RoleDO;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.OrgInfoService;
import com.statisanalysis.entity.Data;
import com.statisanalysis.serivce.IDogCountService;
import com.sys.controller.BaseController;
import com.utils.ShiroUtils;
import com.utils.OrgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: linchong
 * @Date: 2018/9/28 14:02
 * @Version 1.0
 */
@Controller
@RequestMapping("/biz/dogCount")
public class DogCountController  extends BaseController {
    @Autowired
    private IDogCountService dogCountService;
    @Autowired
    private OrgInfoService orgInfoService;

    @RequestMapping()
//    @RequiresPermissions("biz:dogCount")
    public String dogCount(Model model) {
        UserDO user = ShiroUtils.getUser();
        model.addAttribute("user",user);
        return "statisanalysis/dogCount";
    }

    /**
     *
     * 查询犬种分布
     * @return
     */
    @RequestMapping("/getDogBreed")
    @ResponseBody
//    @RequiresPermissions("biz:dogCount:getDogBreed")
    public List<Data> getDogBreed(Model model) {
        Map<String,Object> map = new HashMap<>();
        UserDO user = ShiroUtils.getUser();
        RoleDO roleDO = user.getRoles().get(0);
        if(roleDO.getType() == 2) {
            //防疫员
            map.put("userId",user.getUserId());
        }else {
            //其他角色
            Long deptId = user.getDeptId();
            List<OrgInfo> orgInfos = orgInfoService.orgList();
            List<Integer> subOrgList = OrgUtils.orgReverse(orgInfos, Integer.parseInt(String.valueOf(deptId)), new ArrayList<>());
            map.put("lists",subOrgList);
        }
        Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
        map.put("langType",type);
        List<Data> dogBreed = dogCountService.getDogBreed(map);
        return dogBreed;
    }

    /**
     * 查询犬只性别分布
     * @return
     */
    @RequestMapping("/getDogSex")
    @ResponseBody
//    @RequiresPermissions("biz:dogCount:getDogSex")
    public List<Data> getDogSex() {
        Map<String,Object> map = new HashMap<>();
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type == 2) {
            //防疫员角色
            map.put("userId",user.getUserId());
        }else {
            //其他角色
            Long deptId = user.getDeptId();
            List<OrgInfo> orgInfos = orgInfoService.orgList();
            List<Integer> subOrgList = OrgUtils.orgReverse(orgInfos, Integer.parseInt(String.valueOf(deptId)), new ArrayList<>());
            map.put("lists",subOrgList);
        }
        List<Data> dogSex = dogCountService.getDogSex(map);
        return dogSex;
    }

    /**
     * 查询犬只年龄分布
     * @return
     */
    @RequestMapping("/getDogAge")
    @ResponseBody
//    @RequiresPermissions("biz:dogCount:getDogAge")
    public List<Data> getDogAge() {
        Map<String,Object> map = new HashMap<>();
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type == 2) {
            //防疫员角色
            map.put("userId",user.getUserId());
        }else {
            //其他角色
            Long deptId = user.getDeptId();
            List<OrgInfo> orgInfos = orgInfoService.orgList();
            List<Integer> subOrgList = OrgUtils.orgReverse(orgInfos, Integer.parseInt(String.valueOf(deptId)), new ArrayList<>());
            map.put("lists",subOrgList);
        }
        return dogCountService.getDogAge(map);
    }

    /**
     * 查询犬只区域分布
     * @param map
     * @return
     */
    @GetMapping("/getDogAreaInfo")
    @ResponseBody
//    @RequiresPermissions("biz:dogCount:getDogAreaInfo")
    public List<Data> getDogAreaInfo(@RequestParam Map<String,Object> map) {
        List<Data> dogAreaInfo = dogCountService.getDogAreaInfo(map);
        return dogAreaInfo;
    }
}

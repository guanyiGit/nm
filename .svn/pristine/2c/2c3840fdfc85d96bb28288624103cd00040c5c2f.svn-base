/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DogCanselController
 * Author:   Administrator
 * Date:     2018/9/28 14:50
 * Description: 犬只注销controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dogmanager.controller;

import com.dogmanager.VO.DogCanselVO;
import com.dogmanager.service.DogCanselService;
import com.dogmanager.service.DogInfoService;
import com.dogmanager.utils.DogResult;
import com.entities.TDogCancel;
import com.entities.TDogInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈犬只注销controller〉
 *
 * @author Administrator
 * @create 2018/9/28
 * @since 1.0.0
 */
@Controller
@RequestMapping("/biz/dogCancel")
public class DogCancelController {

    @Autowired
    public DogCanselService dogCanselService;
    @Autowired
    private DogInfoService dogInfoService;

    @GetMapping("/dogCancelFile")
    public String dogCancelFile() {
        return "dogmanager/dogCancelFile";
    }

    @GetMapping("/addDogCancel")
    public String addDogCancel() {
        return "dogmanager/addDogCancel";
    }


    @RequestMapping("/findTSysDict")
    @ResponseBody
    public DogResult findTSysDict() {
        try {
            DogResult dogResult = dogCanselService.findTSysDict();
            return dogResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DogResult.build(100,"操作失败");
    }



    //新增注销犬只
    @RequestMapping("/save")
    @ResponseBody
   // @RequiresPermissions("/biz/dogCancel/save")
    public DogResult save( TDogCancel dogCancel) {
        try {
            dogCanselService.save(dogCancel);
            return DogResult.ok(dogCancel.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DogResult.build(100,"操作失败");
    }

    //详情
    @RequestMapping("/findOne")
    // @RequiresPermissions("/biz/dogCancel/findOne")
    public String findOne(Integer Id, Model model) {
        DogCanselVO dogCansel = dogCanselService.findDogCancelById(Id);
        List<HashMap<String, Object>> picList = dogInfoService.findPicList(Id, 2);

        model.addAttribute("dogCancel", dogCansel);
        model.addAttribute("picList", picList);
        return "dogmanager/dogCancelDetail";
    }

    //详情
    @RequestMapping("/wxfindOne")
    @ResponseBody
    // @RequiresPermissions("/biz/dogCancel/findOne")
    public DogResult findOne(Integer Id) {
        DogCanselVO dogCansel = dogCanselService.findDogCancelById(Id);
        List<HashMap<String, Object>> picList = dogInfoService.findPicList(Id, 2);
        Map<String,Object> map = new HashMap<>();
        map.put("dogCancel",dogCansel);
        map.put("picList",picList);
        return DogResult.ok(map);
    }

    //删除
    @RequestMapping("/delete")
    @ResponseBody
    //@RequiresPermissions("/biz/dogCancel/delete")
    public DogResult delete(Integer Id) {
        try {
            dogCanselService.deleteDogCancel(Id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DogResult.ok();
    }

    //修改
    @RequestMapping("/update")
    public String update(TDogCancel dogCancel) {
    		dogCanselService.updateDogCancel(dogCancel);
        return "";
    }

    //列表
    @RequestMapping("/findAll")
    @ResponseBody
    public DogResult findAll(@RequestParam HashMap<String,Object> map) {
        try {
            PageInfo<DogCanselVO> pageInfo = dogCanselService.findDogCanselList(map);
            return DogResult.ok(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return DogResult.build(400, "请求无数据");
        }
    }


    //根据朔源号查狗
    @RequestMapping("/findDogByTraceId")
    @ResponseBody
    public DogResult findDogByTraceId(String traceId) {
        TDogInfo dogInfo = dogInfoService.selectByPrimaryKey(traceId);
        if (dogInfo == null) {
            return DogResult.build(400, "犬只不存在");
        }
        return DogResult.ok(dogInfo);
    }
}
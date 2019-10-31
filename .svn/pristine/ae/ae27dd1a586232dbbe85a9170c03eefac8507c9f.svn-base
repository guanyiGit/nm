/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: StrayDogController
 * Author:   Administrator
 * Date:     2018/9/28 14:32
 * Description: 流浪犬管理controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dogmanager.controller;

import com.dogmanager.VO.StrayDogVO;
import com.dogmanager.service.DogInfoService;
import com.dogmanager.service.StrayDogService;
import com.dogmanager.utils.DogResult;
import com.entities.TStrayDog;
import com.github.pagehelper.PageInfo;
import com.sys.controller.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈流浪犬管理controller〉
 *
 * @author Administrator
 * @create 2018/9/28
 * @since 1.0.0
 */
@Controller
@RequestMapping("/biz/strayDog")
public class StrayDogController extends BaseController {

    @Autowired
    private StrayDogService strayDogService;
    @Autowired
    private DogInfoService dogInfoService;


    @GetMapping("/StrayDogFile")
    public String StrayDogFile(){
        return "dogmanager/StrayDogFile";
    }

    @GetMapping("/addStrayDog")
    public String addStrayDog(){
        return "dogmanager/addStrayDog";
    }

    @GetMapping("/editDog")
    public String editDog(Integer strayId,Model model){
        model.addAttribute("strayId",strayId);
        return "dogmanager/editStrayDog";
    }

    /*
 * 表单提交日期绑定
 */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    //新增流浪犬
    @RequestMapping("/save")
    @ResponseBody
    public DogResult save(TStrayDog strayDog){
        try {
            strayDogService.saveStrayDog(strayDog);
            return DogResult.ok(strayDog.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DogResult.build(300,"操作失败");
    }
    //流浪犬列表
    @RequestMapping("/findAll")
    @ResponseBody
    public DogResult findAll(@RequestParam HashMap<String,Object> map){
        try {
            PageInfo<StrayDogVO>  strayDogList = strayDogService.findStrayDogList(map);
            return DogResult.ok(strayDogList);
        } catch (Exception e) {
            e.printStackTrace();
            return   DogResult.build(400,"请求无数据");
        }
    }

    //流浪犬列表
    @RequestMapping("/wxfindAll")
    @ResponseBody
    public DogResult findAll(String startDate, String endDate,int pageNum, int pageSize,Integer pro,Integer orgId){
        try {
            PageInfo<StrayDogVO>  strayDogList = strayDogService.findStrayDogListByDate(startDate,endDate, pageNum, pageSize,pro,orgId);
            return DogResult.ok(strayDogList);
        } catch (Exception e) {
            e.printStackTrace();
            return   DogResult.build(400,"请求无数据");
        }
    }

    //流浪犬详情
    @RequestMapping("findOne")
    //@RequiresPermissions("/biz/strayDog/findOne")
    public String findOne(Integer Id,Model model){
        TStrayDog strayDog = strayDogService.findStrayDogById(Id);
        List<HashMap<String, Object>> picList = dogInfoService.findPicList(Id,1);
        model.addAttribute("strayDog",strayDog);
        model.addAttribute("picList",picList);
        return "dogmanager/strayDogDetail";
    }

    //流浪犬详情
    @RequestMapping("wxfindOne")
    //@RequiresPermissions("/biz/strayDog/findOne")
    @ResponseBody
    public DogResult wxfindOne(Integer Id){
        TStrayDog strayDog = strayDogService.findStrayDogById(Id);
        List<HashMap<String, Object>> picList = dogInfoService.findPicList(Id,1);
        Map<String,Object> map = new HashMap<>();
        map.put("strayDog",strayDog);
        map.put("picList",picList);
        return DogResult.ok(map);
    }

    //检查流浪犬编号是否存在
    @RequestMapping("/checkNo")
    @ResponseBody
    public DogResult checkNo(String no){
       String msg= strayDogService.checkNo(no);
       if(!StringUtils.isEmpty(msg)){
           return DogResult.build(400,"编号已存在");
       }
       return DogResult.ok();
    }

    //删除
    @RequestMapping("/delete")
    @ResponseBody
    public DogResult delete(Integer Id){
        strayDogService.delete(Id);
        return DogResult.ok();
    }
    //修改
    @RequestMapping("/update")
    @ResponseBody
    public DogResult update(TStrayDog strayDog){
        strayDogService.update(strayDog);
        return DogResult.ok();
    }

}


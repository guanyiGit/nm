/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DogInfoController
 * Author:   Administrator
 * Date:     2018/9/27 15:23
 * Description: 犬只管理Controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.dogmanager.controller;

import com.dogmanager.VO.DogInfoVO;
import com.dogmanager.VO.NectletChangeVO;
import com.dogmanager.VO.OwnerChangeVO;
import com.dogmanager.VO.TProtectorVO;
import com.dogmanager.service.DogInfoService;
import com.dogmanager.service.NectletChangeService;
import com.dogmanager.service.OwnerChangeService;
import com.dogmanager.utils.DogResult;
import com.entities.*;
import com.github.pagehelper.PageInfo;
import com.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈犬只管理Controller〉
 *
 * @author Administrator
 * @create 2018/9/27
 * @since 1.0.0
 */
@Controller
@RequestMapping("/biz/dogInfo")
public class DogInfoController{


    private Logger logger = LoggerFactory.getLogger(DogInfoController.class);


    @Autowired
    private DogInfoService dogInfoService;
    @Autowired
    private OwnerChangeService ownerChangeService;
    @Autowired
    private NectletChangeService nectletChangeService;



    //犬只页面
    @GetMapping("/dogMasterFile")
    public String showDogMasterFile(){
        return "dogmanager/dogMasterFile";
    }
   //新增犬只
    @GetMapping("/addDog")
    //@RequiresPermissions("/biz/dogInfo/save")
    public String addDog(){
        return "dogmanager/addDog";
    }
    //犬只页面2
    @GetMapping("/dogMaster2File")
    public String dogMaster2File(){
        return "dogmanager/dogMaster2File";
    }
    //犬主更换列表
    @GetMapping("/ownerChangeFile")
    public String ownerChangeFile(){
        return "dogmanager/ownerChangeFile";
    }
    //犬主更换
    @GetMapping("/addOwnerChange")
   // @RequiresPermissions("/biz/dogInfo/addOwnerChange")
    public String addownerChange(){
        return "dogmanager/addOwnerChange";
    }
    //项圈更换列表
    @GetMapping("/neckletChangeFile")
    public String nectletChangeFile(){
        return "dogmanager/neckletChangeFile";
    }
    //项圈更换
    @GetMapping("/addNeckletChange/{traceId}")
    //@RequiresPermissions("/biz/dogInfo/addNeckletChange")
    public String addNeckletChange(@PathVariable("traceId") String traceId, Model model){
        if(StringUtils.isEmpty(traceId)){
            logger.error("【项圈更换】 溯源号为空");
            throw new RuntimeException();
        }
        TDeviceRefDog deviceRefDog = dogInfoService.selectByTraceId(traceId);
        if(deviceRefDog==null){
            logger.error("【项圈更换】溯源号查设备号出错了");
            throw new RuntimeException();
        }
        model.addAttribute("deviceRefDog",deviceRefDog);
        return "dogmanager/addNeckletChange";
    }

    //检查犬只是否绑定设备
    @RequestMapping("/checkDogDevice")
    @ResponseBody
    public DogResult checkDogDevice(String traceId){
        TDeviceRefDog deviceRefDog = dogInfoService.selectByTraceId(traceId);
        if(deviceRefDog==null){
            return DogResult.build(9000,"未绑定设备");
        }
        return DogResult.build(9001,"已绑定设备");
    }

    //犬只编辑
    @GetMapping("/editDog")
   // @RequiresPermissions("/biz/dogInfo/editDog")
    public String editDog(String traceId,Model model){
        if(StringUtils.isEmpty(traceId)){
            logger.error("【犬只编辑】 溯源号为空");
            throw new RuntimeException();
        }
        model.addAttribute("traceId",traceId);
        return "dogmanager/editDog";
    }


    //检查设备是否已被绑定(true==已绑定  false==未绑定)
    @RequestMapping("/checkDevice")
    @ResponseBody
    public DogResult checkDevice(String deviceNo){
        Boolean  isBinding =  dogInfoService.checkDevice(deviceNo);
        return  DogResult.ok(isBinding);
    }

    //新增犬只
    @RequestMapping("/save")
    @ResponseBody
    public DogResult saveDogInfo(DogInfoVO dogInfo){
        try {
            int dogId = dogInfoService.saveDogInfo(dogInfo);
            return DogResult.ok(dogId);
        } catch (Exception e) {
            e.printStackTrace();
            return DogResult.build(400,"请求无数据");
        }
    }
    //(修改查看详情)
    @RequestMapping("/findOne2")
    @ResponseBody
    //@RequiresPermissions("/biz/dogInfo/findOne2")
    public DogResult findOne2(String traceId){
        HashMap<String, Object> map = dogInfoService.findOne(traceId);
        Object dogInfo = map.get("dogInfo");
        if(!StringUtils.isEmpty(dogInfo)){
            return DogResult.ok(dogInfo);
        }
        return DogResult.build(400,"请求无数据");
    }

    //查看犬只详情
    @RequestMapping("/findOne")
 //   @RequiresPermissions("/biz/dogInfo/findOne")
    public String findOne(String traceId, Model model){
        HashMap<String, Object> map = dogInfoService.findOne(traceId);
        model.addAttribute("dogInfoVO",map);
        return "dogmanager/dogDetailInfo";
    }

    //wx查看犬只详情
    @RequestMapping("/findOne3")
    @ResponseBody
    //@RequiresPermissions("/biz/dogInfo/findOne3")
    public DogResult findOne3(String traceId,String deviceNo){
        try {
            DogResult wXdogInfo = dogInfoService.WXfindOne(traceId,deviceNo);
            return wXdogInfo;
        } catch (Exception e) {
            return DogResult.build(400,"error");
        }
    }

    //修改犬只
    @RequestMapping("/update")
    @ResponseBody
    public DogResult updateDogInfo(TDogInfo dogInfo){
        try {
            dogInfoService.updateDogInfo(dogInfo);
            return DogResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DogResult.build(400,"修改不成功");
    }

    //删除犬只
    @RequestMapping("/delete")
    @ResponseBody
    //@RequiresPermissions("/biz/dogInfo/delete")
    public DogResult deleteDogInfo(Integer DogId){
        try {
            dogInfoService.deleteDogInfo(DogId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DogResult.ok();
    }

    //查看犬只列表
    @RequestMapping("/findAll")
    @ResponseBody
    public DogResult findAll(@RequestParam HashMap<String,Object> map){
        try {
            PageInfo<DogInfoVO> pageInfo  = dogInfoService.findDogInfoList(map);
            return DogResult.ok(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
          return   DogResult.build(400,"请求无数据");
        }
    }


    //wx查看犬只列表
    @RequestMapping("/wxfindAll")
    @ResponseBody
    public DogResult wxfindAll(@RequestParam HashMap<String,Object> map){
        try {
            PageInfo<DogInfoVO> pageInfo  = dogInfoService.wxfindDogInfoList(map);
            return DogResult.ok(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return   DogResult.build(400,"请求无数据");
        }
    }


    //wx查看犬只是否防疫列表
    @RequestMapping("/wxfindAllIsAnt")
    @ResponseBody
    public DogResult wxfindDogInfoListIsAnt(@RequestParam HashMap<String,Object> map){
        try {
            PageInfo<DogInfoVO> pageInfo  = dogInfoService.wxfindDogInfoListIsAnt(map);
            return DogResult.ok(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return   DogResult.build(400,"请求无数据");
        }
    }

    //查看已绑定项圈犬只列表
    @RequestMapping("/findBindAll")
    @ResponseBody
    public DogResult findBindAll(@RequestParam HashMap<String,Object> map){
        try {
            PageInfo<DogInfoVO> pageInfo  = dogInfoService.findBindDogInfoList(map);
            return DogResult.ok(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return   DogResult.build(400,"请求无数据");
        }
    }

    //犬主更换
    @RequestMapping("/ownerChange/save")
    @ResponseBody
    //@RequiresPermissions("/biz/dogInfo/ownerChange/save")
    public DogResult saveownerChange(@RequestBody  TOwnerChange ownerChange){
        try {
            dogInfoService.saveOwnerChange(ownerChange);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DogResult.ok();
    }

    //犬主更换列表
    @RequestMapping("/ownerChange/findAll")
    @ResponseBody
    public DogResult findOwnerChangeList(@RequestParam HashMap<String,Object> map){
        try {
            PageInfo<OwnerChangeVO> ownerChangeVOList = ownerChangeService.findOwnerChangeVOList(map);
            return DogResult.ok(ownerChangeVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return   DogResult.build(400,"请求无数据");
        }
    }

    //犬主更换详情
    @RequestMapping("/ownerChange/findOne")
    //@RequiresPermissions("/biz/dogInfo/ownerChange/findOne")
    public  String findOwnerChange(Integer Id,Model model){
        TOwnerChange tOwnerChange = ownerChangeService.findOwnerChangeVOById(Id);
        model.addAttribute("ownerChange",tOwnerChange);
        return "dogmanager/ownerChangeDetail";
    }

    //项圈更换列表
    @RequestMapping("/nectletChange/findAll")
    @ResponseBody
    public  DogResult findnectletChangeList(@RequestParam HashMap<String,Object> map){
        try {
            PageInfo<NectletChangeVO> pageInfo = nectletChangeService.findTNeckletChangeList(map);
            return DogResult.ok(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return   DogResult.build(400,"请求无数据");
        }

    }

    //项圈更换详情
    @RequestMapping("/neckletChange/findOne")
    // @RequiresPermissions("/biz/dogInfo/neckletChange/findOne")
    public String findneckletChange(Integer Id,Model model){
        NectletChangeVO neckletChange = nectletChangeService.findOne(Id);
        model.addAttribute("neckletChange",neckletChange);
        return "dogmanager/neckletChangeDetail";
    }

    //项圈更换详情
    @RequestMapping("/wxneckletChange/findOne")
    @ResponseBody
    // @RequiresPermissions("/biz/dogInfo/neckletChange/findOne")
    public DogResult wxfindneckletChange(Integer Id){
        NectletChangeVO neckletChange = nectletChangeService.findOne(Id);
        return DogResult.ok(neckletChange);
    }

    //项圈变更
    @RequestMapping("/nectletChange/save")
    @ResponseBody
   // @RequiresPermissions("/biz/dogInfo/neckletChange/save")
    public DogResult savenectletChange(TNeckletChange neckletChange){
        try {
            dogInfoService.saveNectletChange(neckletChange);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DogResult.ok();
    }

    //根据防疫员查犬主
    @RequestMapping("/getDogOwner")
    @ResponseBody
    public DogResult getDogOwner(Integer Id){
        try {
            List<HashMap<String, Object>> list = dogInfoService.findDogOwner(Id);
            if (list.size()>0){
                return DogResult.ok(list);
            }else {
                return DogResult.build(300,"请求无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DogResult.build(400,"服务器异常");
        }
    }
    //查犬种
    @RequestMapping("/getDogBreed")
    @ResponseBody
    public DogResult getDogBreed(){
        try {
            List<HashMap<String, Object>> list = dogInfoService.findBreed();
            if (list.size()>0){
                return DogResult.ok(list);
            }else {
                return DogResult.build(300,"请求无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DogResult.build(400,"服务器异常");
        }
    }

    //查电子围栏
    @RequestMapping("/getFence")
    @ResponseBody
    public DogResult getFence(Integer Id){
        try {
            List<HashMap<String, Object>> list = dogInfoService.findFence(Id);
            if (list.size()>0){
                return DogResult.ok(list);
            }else {
                return DogResult.build(300,"请求无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DogResult.build(400,"服务器异常");
        }
    }

    //查设备
    @RequestMapping("/getDevice")
    @ResponseBody
    public DogResult getDevice(){
        try {
            List<HashMap<String, Object>> list = dogInfoService.findDevice();
            if (list.size()>0){
                return DogResult.ok(list);
            }else {
                return DogResult.build(300,"请求无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DogResult.build(400,"服务器异常");
        }
    }

    @RequestMapping("/findDeviceIdByDeviceNo")
    @ResponseBody
    public DogResult getDeviceId(String deviceNo){
        Integer deviceId = dogInfoService.findDeviceIdByDeviceNo(deviceNo);
        if(deviceId!=null){
            return DogResult.ok(deviceId);
        }
        return DogResult.build(400,"请求无数据");
    }

    /**
     * 检查设备是否可用
     * @param deviceNo
     * @return
     */
    @RequestMapping("/checkDeviceIsUse")
    @ResponseBody
    public R checkDeviceIsUse(String deviceNo) {
        DeviceInfo deviceInfo = dogInfoService.findDeviceByNo(deviceNo);
        if(deviceInfo == null) {
            return R.error(9000,"该设备不存在");
        }
        switch (deviceInfo.getStatus()){
            case 1:return R.error(9002,"该设备已被绑定");
            case 2:return R.error(9003,"该设备已丢失");
            case 3: return R.error(9004,"该设备已损坏");
        }
        return R.ok();
    }

    @RequestMapping("/findProtector3")
    @ResponseBody
    // @RequiresPermissions("/biz/dogInfo/findProtector")
    public List<HashMap<String,Object>> findProtector3(){
        List<HashMap<String,Object>> list = dogInfoService.findProtector3();
        return list;
    }

    @RequestMapping("/findProtector2")
    @ResponseBody
    // @RequiresPermissions("/biz/dogInfo/findProtector")
    public List<TProtectorVO> findProtector2(){
        List<TProtectorVO> protector = dogInfoService.findProtector2();
        return protector;
    }

    //绑定设备
    @RequestMapping("/insertDogDeviceNo")
    @ResponseBody
    // @RequiresPermissions("/biz/dogInfo/insertDogDeviceNo")
    public DogResult insertDogDeviceNo(TDeviceRefDog deviceRefDog){
        try {
            dogInfoService.insertDogDeviceNo(deviceRefDog);
            return DogResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return  DogResult.build(400,"绑定失败");
        }
    }
    //删除图片
    @RequestMapping("/deleteFile")
    @ResponseBody
    public DogResult deleteFile(Integer fid) {
        try {
            dogInfoService.deletePic(fid);
             return DogResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return  DogResult.build(400,"删除失败");
        }
    }

}



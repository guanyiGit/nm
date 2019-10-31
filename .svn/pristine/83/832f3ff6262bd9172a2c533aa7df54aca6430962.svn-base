package com.statisanalysis.controller;

import com.entities.OrgInfo;
import com.entities.TMsg;
import com.orgmanagement.domain.UserDO;
import com.statisanalysis.serivce.IHomePageService;
import com.sys.controller.BaseController;
import com.utils.PageUtils;
import com.utils.Query;
import com.utils.R;
import com.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/5 17:26
 * @Version 1.0
 */
@Controller
@RequestMapping("/biz/homePage")
public class HomePageController extends BaseController {
    @Autowired
    private IHomePageService homePageService;

    @GetMapping()
//    @RequiresPermissions("biz:homePage")
    public String homePage(Model model) {
        Map<String,Object> map = new HashMap<>();
        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        map.put("type",type);
        map.put("orgId",user.getDeptId());
        map.put("userId",user.getUserId());
        Map<String, Object> res = null;
        try {
            res = homePageService.countAntiAmount(map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("res",res);
        return "statisanalysis/homePage";
    }

    @RequestMapping("/toDogPage")
    public String toDogPage(String type,Model model) {
        if("0".equals(type)) {
            model.addAttribute("title","犬只总数");
        }
        if("1".equals(type)) {
            model.addAttribute("title","已防疫犬只");
        }
        if("2".equals(type)) {
            model.addAttribute("title","未防疫犬只");
        }
        model.addAttribute("type",type);
        return "statisanalysis/dogMasterFile";
    }

    @GetMapping("/myMessage")
//    @RequiresPermissions("biz:homePage")
    public String myMessage() {
        //TODO 查询用户所有的已读、未读消息，放在model
        return "statisanalysis/myMessage";
    }

    /**
     * 已防疫犬只、未防疫犬只详情
     * @param map
     * @return
     */
    @RequestMapping("/dogList")
    @ResponseBody
    public PageUtils dogList(@RequestParam Map<String,Object> map) {
        UserDO user = ShiroUtils.getUser();
        map.put("myOrgId",user.getDeptId());
        map.put("userId",user.getUserId());
        map.put("role",user.getRoles().get(0).getType());
        PageUtils pageUtils = null;
        try {
            pageUtils = homePageService.dogList(map);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return pageUtils;
    }

    @GetMapping("/read/{id}/{status}")
//    @RequiresPermissions("biz:homePage")
    public String readMsg(@PathVariable("id") String id,@PathVariable("status")int status, Model model) {
        TMsg msgInfo = homePageService.findMsgById(id);
        //设置为已读
        if(status != 1) {
            homePageService.updateMsgStatus(id);
        }
        model.addAttribute("msgInfo",msgInfo);
        return "statisanalysis/myMsgRead";
    }

    @GetMapping("/wxRead")
//    @RequiresPermissions("biz:homePage")
    @ResponseBody
    public R wxReadMsg(String id, Integer status) {
        Map<String,Object> map = new HashMap<>();
        TMsg msgInfo = homePageService.findMsgById(id);
        //设置为已读
        if(status != 1) {
            homePageService.updateMsgStatus(id);
        }
        map.put("msgInfo",msgInfo);
        return R.ok(map);
    }


    /**
     * 获取未读消息条数
     * @param model
     * @return
     */
//    @GetMapping("/checkUnreadMsg")
//    public R checkUnreadMsg(Model model) {
//        int userId = 1;
//        Map<String,Object> map = new HashMap<>();
//        map.put("id",userId);
//        Integer total = homePageService.getUnreadedTotal(map);
//        model.addAttribute("total",total);
//        return R.ok();
//    }


    /**
     * 查询我的所有消息
     * @param map
     * @return
     */
    @RequestMapping("/getAllMsgs")
//    @RequiresPermissions("biz:homePage")
    @ResponseBody
    public List<TMsg> getAllMsgs(@RequestParam Map<String,Object> map){
        map.put("id",ShiroUtils.getUserId());
        List<TMsg> msgs = homePageService.getAllMsgs(map);
        return msgs;
    }

    /**
     * 查询上月免疫次数
     * @param map
     * @return
     */
    @RequestMapping("/getAntiepidemicTimes")
//    @RequiresPermissions("biz:homePage")
    @ResponseBody
    public Integer getAntiepidemicTimes(@RequestParam Map<String,Object> map) {
        Integer times = null;
        try {
            times = homePageService.getAntiepidemicTimes(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }

    /**
     * 获取上月新增犬只
     * @param map
     * @return
     */
    @RequestMapping("/getAddedDogNum")
//    @RequiresPermissions("biz:homePage")
    @ResponseBody
    public Integer getAddeddogNum(@RequestParam Map<String,Object> map) {
        Integer addedDogs = null;
        try {
            addedDogs = homePageService.getAddedDogs(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addedDogs;
    }

    @RequestMapping("/getDogNums")
    @ResponseBody
    public Map<String,Object> getDogNums(@RequestParam Map<String,Object> map) {
        UserDO user = ShiroUtils.getUser();
        map.put("type",user.getRoles().get(0).getType());
        map.put("userId",user.getUserId());
        map.put("orgId",user.getDeptId());
        Map<String, Object> res = null;
        try {
            res = homePageService.countDogNumber(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 上月使用次数前十的药品
     * @param params
     * @return
     */
    @RequestMapping("/getTopTen")
//    @RequiresPermissions("biz:homePage")
    @ResponseBody
    public Map<String,List<Object>> getTopTen(@RequestParam Map<String,Object> params) {
        Map<String,List<Object>> map = new HashMap<>();
        try {
            map = homePageService.getToptenDrug(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 防疫次数趋势
     * @param map
     * @return
     */
    @RequestMapping("/getPerMonthTimes")
//    @RequiresPermissions("biz:homePage")
    @ResponseBody
    public Map<String,List<String>> getPerMonthTimes(@RequestParam Map<String,Object> map) {
        Map<String,List<String>> list = null;
        try {
            list = homePageService.getPerMonthTimes(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 管理犬只数量趋势
     * @param map
     * @return
     */
    @RequestMapping("/getChargeDogsTrend")
//    @RequiresPermissions("biz:homePage")
    @ResponseBody
    public Map<String,List<String>> getChargeDogsTrend(@RequestParam Map<String,Object> map) {
        Map<String,List<String>> list = null;
        try {
            list = homePageService.getChargeDogs(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    @RequestMapping("/getUnreadMsgs")
//    @RequiresPermissions("biz:homePage")
    @ResponseBody
    public PageUtils getUnreadMsgs(@RequestParam Map<String,Object> map) {
//        map.put("id",1);        //设置userId
        Long userId = ShiroUtils.getUserId();
        map.put("id",userId);
        Query query = new Query(map);
        Integer total = homePageService.getUnreadedTotal(query);
        List<TMsg> msgList = homePageService.getUnreadedMsgs(query);
        return new  PageUtils(msgList,total);
    }

    @RequestMapping("/getReadedMsgs")
//    @RequiresPermissions("biz:homePage")
    @ResponseBody
    public PageUtils getReadedMsgs(@RequestParam Map<String,Object> map) {
//        map.put("id",1);        //设置userId
        Long userId = ShiroUtils.getUserId();
        map.put("id",userId);
        Query query = new Query(map);
        Integer total = homePageService.getReadedTotal(query);
        List<TMsg> msgList = homePageService.getReadedMsgs(query);
        return new  PageUtils(msgList,total);
    }

    @RequestMapping("/getAllOrg")
//    @RequiresPermissions("biz:homePage:getAllOrg")
    @ResponseBody
    public List<OrgInfo> getAllOrg() {
        return homePageService.getAllOrgInfo();
    }

    /**
     * 首页消息（最多显示9条）
     * @return
     */
    @RequestMapping("/getHomePageUnreadedMsg")
//    @RequiresPermissions("biz:homePage:getHomePageUnreadedMsg")
    @ResponseBody
    public List<TMsg> getHomePageUnreadedMsg() {
        Map<String,Object> map = new HashMap<>();
//        map.put("userId",1);
        map.put("userId",ShiroUtils.getUserId());
        map.put("offset",0);
        map.put("limit",9);
        List<TMsg> homePageUnreadedMsg = homePageService.getHomePageUnreadedMsg(map);
        return homePageUnreadedMsg;
    }

    /**
     * 查询我的所有消息
     * @param map
     * @return
     */
    @RequestMapping("/getMsgList")
//    @RequiresPermissions("biz:homePage:getMsgList")
    @ResponseBody
    public List<TMsg> getMsgList(@RequestParam Map<String,Object> map) {
        Long userId = ShiroUtils.getUserId();
        map.put("id",userId);
        Query query = new Query(map);
        List<TMsg> allMsgs = homePageService.getMsgList(query);
        return allMsgs;
    }

    /**防疫统计：
     * 统计犬只总数、未防疫犬只、已防疫犬只
     * @param map
     * @return
     */
//    @RequestMapping("/getAmounts")
//    @ResponseBody
//    public Map<String,Object> getAmounts(@RequestParam Map<String,Object> map) {
//        UserDO user = ShiroUtils.getUser();
//        int type = user.getRoles().get(0).getType();
//        map.put("type",type);
//        map.put("orgId",user.getDeptId());
//        map.put("userId",user.getUserId());
//        Map<String, Object> res = null;
//        try {
//            res = homePageService.countAntiAmount(map);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return res;
//    }



}

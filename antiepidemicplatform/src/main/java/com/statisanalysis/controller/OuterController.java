package com.statisanalysis.controller;

import com.orgmanagement.domain.UserDO;
import com.statisanalysis.entity.AllNumVO;
import com.statisanalysis.serivce.IOuterService;
import com.statisanalysis.utils.DateUtils;
import com.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/12 17:24
 * @Version 1.0
 */
@Controller
@RequestMapping("/biz/outer")
public class OuterController {
    @Autowired
    private IOuterService outerService;

    @RequestMapping("/getAllNum")
//    @RequiresPermissions("biz:outer:getAllNum")
    @ResponseBody
    public AllNumVO getAllNum(@RequestParam Map<String,Object> params) {
        //param --> protectorId、month
        UserDO user = ShiroUtils.getUser();
        params.put("userId",user.getUserId());
        Object searMonth = params.get("searMonth");
        if(searMonth == null || searMonth.toString().trim().equals("")) {
            //前端没有传入了日期搜索条件，设置默认查找当前月
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String nowMonth = sdf.format(new Date());
            params.put("searMonth",nowMonth);
        }
        AllNumVO vo = new AllNumVO();
        try {
            String month = params.get("searMonth").toString();
            String nextMonth = DateUtils.getLastMonth(month, 0, 1, 0);
            params.put("nextMonth",nextMonth);
            //犬只存栏数量
            Integer dogLiveNum = outerService.getDogLiveNum(params);
            //犬只新增數量
            Integer dogNewNum = outerService.getDogNewNum(params);
            //註銷犬只數量
            Integer cancelDogNum = outerService.getCancelDogNum(params);
            //新增犬主數量
            Integer ownerNewNum = outerService.getOwnerNewNum(params);
            //防疫次數
            Integer antiedemicTimes = outerService.getAntiedemicTimes(params);
            //犬尸處理數量
            Integer dogCorpseNum = outerService.getDogCorpseNum(params);
            //糞便處理次數
            Integer manureDealTimes = outerService.getManureDealTimes(params);
            //流浪狗處理數量
            Integer strayDogTimes = outerService.getStrayDogTimes(params);
            //犬主更換次數
            //項圈更換次數
            Integer neckTimes = outerService.getNeckTimes(params);
            vo.setDogLiveNum(dogLiveNum);
            vo.setDogNewNum(dogNewNum);
            vo.setDogCancelNum(cancelDogNum);
            vo.setOwnerNewNum(ownerNewNum);
            vo.setAntiepidemicTimes(antiedemicTimes);
            vo.setDogCorpseNum(dogCorpseNum);
            vo.setManureNum(manureDealTimes);
            vo.setLlqNum(strayDogTimes);
            vo.setXqghTimes(neckTimes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }
}

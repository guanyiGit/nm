package com.statisanalysis.controller;

import com.orgmanagement.service.AreaInfoService;
import com.orgmanagement.service.OrgInfoService;
import com.statisanalysis.entity.CompreReportVO;
import com.statisanalysis.serivce.IComprehensiveReportService;
import com.utils.PageUtils;
import com.utils.Query;
import com.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/11 9:55
 * @Version 1.0
 */
@Controller
@RequestMapping("/biz/comprehensiveReport")
public class ComprehensiveReportController {
    private static Logger logger = LoggerFactory.getLogger(ComprehensiveReportController.class);
    @Autowired
    private IComprehensiveReportService reportService;
    @Autowired
    private AreaInfoService areaInfoService;
    @Autowired
    private OrgInfoService orgInfoService;
    //跳转到综合报表（州级）
    @RequestMapping()
//    @RequiresPermissions("biz:comprehensiveReport")
    public String comprehensiveReport() {
        return "statisanalysis/comprehensiveReport";
    }

    @RequestMapping("/monthList")
    @ResponseBody
//    @RequiresPermissions("biz:comprehensiveReport:monthList")
    public PageUtils getMonth(@RequestParam Map<String,Object> params) {
        //默认设置year为当前年份
        Query query = new Query(params);
        List<Map<String,Object>> list = reportService.getMonth(query);
        int total = reportService.getMonthTotal(query);
        PageUtils pageUtils = new PageUtils(list, total);
        return pageUtils;
    }

    //跳转到“综合报表-详情”
    @RequestMapping("/detail/{perMonth}")
//    @RequiresPermissions("biz:comprehensiveReport:detail")
    public String toDetail(@PathVariable String perMonth, Model model) {
        if(perMonth == null) {
            return "statisanalysis/comprehensiveReport";
        }
        String[] split = perMonth.split("-");
        String year = split[0];
        String month = convertMonth(split[1]);

        Map<String,Object> map = new HashMap<>();
        map.put("perMonth",perMonth);
//        map.put("orgId",3);     //手动设置本州的orgId
        map.put("orgId", ShiroUtils.getUser().getDeptId());
        List<CompreReportVO> list = null;
        try {
            list = reportService.getAntiReport(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("compreReportList",list);
        model.addAttribute("year",year);
        model.addAttribute("month",month);
        return "statisanalysis/comprehensiveReportDetail";
    }

    public String convertMonth(String month){
        if("01".equals(month)) {
            return "一月";
        }else if("02".equals(month)){
            return "二月";
        }else if("03".equals(month)){
            return "三月";
        }else if("04".equals(month)){
            return "四月";
        }else if("05".equals(month)){
            return "五月";
        }else if("06".equals(month)){
            return "六月";
        }else if("07".equals(month)){
            return "七月";
        }else if("08".equals(month)){
            return "八月";
        }else if("09".equals(month)){
            return "九月";
        }else if("10".equals(month)){
            return "十月";
        }else if("11".equals(month)){
            return "十一月";
        }else{
            return "十二月";
        }
    }
}

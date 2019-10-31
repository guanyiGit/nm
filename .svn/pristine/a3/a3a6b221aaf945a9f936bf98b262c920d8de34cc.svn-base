package com.statisanalysis.controller;

import com.statisanalysis.serivce.IAntiepidemicCountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/10/5 7:55
 * @Version 1.0
 */
@Controller
@RequestMapping("/biz/antiepidemicCount")
public class AntiepidemicCountController {
    @Autowired
    private IAntiepidemicCountService antiepidemicService;
    private Logger logger = LoggerFactory.getLogger(AntiepidemicCountController.class);

    @GetMapping()
//    @RequiresPermissions("biz:antiepidemicCount:antiepidemicCount")
    public String antiepidemicCount() {
        return "statisanalysis/antiepidemicCount";
    }

    /**
     * 统计防疫次数趋势
     * @param params
     * @return
     */
    @RequestMapping("/getAntipicdemicTrend")
    @ResponseBody
//    @RequiresPermissions("biz:antiepidemicCount:getAntipicdemicTrend")
    public Map<String,Object> getAntipicdemicTrend(@RequestParam Map<String,Object> params) {
        Map<String,Object> list = null;
        logger.info("AntiepidemicCountDao.getAntipicdemicTrend|params={}",params);
        try {
           list = antiepidemicService.countAntiepidemicTimes(params);
        }catch (Exception e) {
            logger.error("AntiepidemicCountController.getAntipicdemicTrend|{}",e.getMessage());
        }

        return list;
    }


    /**
     * 统计使用量前十的药品
     * @param params
     * @return
     */
    @RequestMapping("/getTopTenDrug")
    @ResponseBody
//    @RequiresPermissions("biz:antiepidemicCount:getTopTenDrug")
    public Map<String,Object> getTopTenDrug(@RequestParam Map<String,Object> params) {
        logger.info("AntiepidemicCountDao.getTopTenDrug|params={}",params);
        Map<String, Object> topTenDrug = null;
        try {
            topTenDrug = antiepidemicService.getTopTenDrug(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topTenDrug;
    }

    /**
     * 药品使用情况统计
     * @param params
     * @return
     */
    @RequestMapping("/getDrugUsage")
    @ResponseBody
//    @RequiresPermissions("biz:antiepidemicCount:getDrugUsage")
    public List<List> getDrugUsage(@RequestParam Map<String,Object> params){
        List<List> result = null;
        try {
            result = antiepidemicService.countDrugUsage(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

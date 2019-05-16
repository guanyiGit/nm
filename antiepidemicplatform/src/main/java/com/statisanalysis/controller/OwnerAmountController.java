package com.statisanalysis.controller;

import com.statisanalysis.entity.OwnerNumVO;
import com.statisanalysis.serivce.IOwnerAmountService;
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
 * @Date: 2018/10/4 20:58
 * @Version 1.0
 */
@Controller
@RequestMapping("/biz/ownerAmount")
public class OwnerAmountController {
    @Autowired
    private IOwnerAmountService ownerAmountService;
    private Logger logger = LoggerFactory.getLogger(OwnerAmountController.class);

    @GetMapping()
//    @RequiresPermissions("biz:ownerAmount")
    public String ownerAmount() {
        return "statisanalysis/ownerAmount";
    }

    /**
     * 统计犬主数量
     * @param map
     * @return
     */
    @RequestMapping("/getOwnerAmount")
//    @RequiresPermissions("biz:ownerAmount:getOwnerAmount")
    @ResponseBody
    public List<OwnerNumVO> getOwnerAmount(@RequestParam Map<String,Object> map) {
        List<OwnerNumVO> result = null;
        try{
            result = ownerAmountService.countOwnerNum(map);
        }catch (Exception e) {
            logger.error("OwnerAmountController.getOwnerAmount()|{}",e.getMessage());
        }
        return result;
    }

    /**
     * 统计犬主数量趋势
     * @param params
     * @return
     */
    @GetMapping("/getOwnerNumTrend")
//    @RequiresPermissions("biz:ownerAmount:getOwnerNumTrend")
    @ResponseBody
    public Map<String,Object> getOwnerNumTrend(@RequestParam Map<String,Object> params) {
        Map<String, Object> result = null;
        try {
            result = ownerAmountService.countOwnerNumTrend(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

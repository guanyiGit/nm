package com.statisanalysis.controller;

import com.statisanalysis.entity.DogNumVO;
import com.statisanalysis.serivce.IDogAmountService;
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
 * @Author: linchong
 * @Date: 2018/9/30 10:33
 * @Version 1.0
 */
@Controller
@RequestMapping("/biz/dogAmount")
public class DogAmountController {

    @Autowired
    private IDogAmountService dogAmountService;
    private Logger logger = LoggerFactory.getLogger(DogAmountController.class);

    @GetMapping()
//    @RequiresPermissions("biz:dogAmount")
    public String dogAmount() {
        return "statisanalysis/dogAmount";
    }

    /**
     * 获取犬只数量趋势
     * @param params
     * @return
     */
    @GetMapping("/getDogTrend")
//    @RequiresPermissions("biz:dogAmount:getDogTrend")
    @ResponseBody
    public Map<String,Object> getDogTrend(@RequestParam Map<String,Object> params){
        Map<String, Object> dogList = null;
        try {
            dogList = dogAmountService.getDogTrend(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dogList;
    }

    /**
     * 犬只数量统计
     * @param params
     * @return
     */
    @GetMapping("/getDogNum")
//    @RequiresPermissions("biz:dogAmount:getDogNum")
    @ResponseBody
    public List<DogNumVO> getDogNum(@RequestParam Map<String,Object> params) {
        List<DogNumVO> dogNum = null;
        try {
            dogNum = dogAmountService.getDogNum(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dogNum;
    }

}

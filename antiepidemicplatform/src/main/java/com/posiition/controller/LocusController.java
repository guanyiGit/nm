package com.posiition.controller;//package com.posiition.controller;

import com.posiition.service.LocusService;
import com.utils.GlobalResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 轨迹相关
 */
@Controller
@RequestMapping(value = "/biz/locus")
public class LocusController {

    @Autowired
    private LocusService locusService;

    @GetMapping()
    public String locus() {
        return "devicemanagement/locusList";
    }

    /**
     * 查询犬的轨迹信息
     *
     * @param dogId 犬id
     * @param day   相隔时间  默认近一天的轨迹
     */
    @GetMapping(value = "/{dogId}")
    @ResponseBody
    public String get(@PathVariable("dogId") Integer dogId,
                      @RequestParam(value = "day", defaultValue = "1") Integer day) {

        return new GlobalResult().objResultJSON(locusService.getLocusByDogId(dogId, day));

    }


}

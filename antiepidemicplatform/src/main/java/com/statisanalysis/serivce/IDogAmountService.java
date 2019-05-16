package com.statisanalysis.serivce;

import com.statisanalysis.entity.DogNumVO;

import java.util.List;
import java.util.Map;

/**
 * @Author: linchong
 * @Date: 2018/9/30 10:27
 * @Version 1.0
 */
public interface IDogAmountService {
    //获取犬只在某一时间段的数量趋势
//    List<DogNumVO> getDogAmountTrend(@RequestParam Map<String, Object> params) throws Exception;

    /**
     * 查询犬只数量趋势
     * @param map
     * @return
     */
    Map<String,Object> getDogTrend(Map<String,Object> map) throws Exception;

    /**
     * 统计犬只数量（存栏、新增、注销、死亡）
     * @param params
     * @return
     */
    List<DogNumVO> getDogNum(Map<String,Object> params) throws Exception;
}

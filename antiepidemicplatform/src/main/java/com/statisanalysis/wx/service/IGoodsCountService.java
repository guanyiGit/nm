package com.statisanalysis.wx.service;

import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/11/26 11:25
 * @Version 1.0
 */
public interface IGoodsCountService {
    Map<String,Object> countGoods(Map<String,Object> params) throws Exception;
}

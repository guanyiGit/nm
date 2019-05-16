package com.statisanalysis.wx.service;

import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/11/22 16:23
 * @Version 1.0
 */
public interface IAntieCountService {
    Map<String,Object> countInfo(Map<String,Object> params) throws Exception;
}

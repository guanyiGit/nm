package com.dogmanager.service;

import com.dogmanager.VO.NectletChangeVO;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/9/28.
 */
public interface NectletChangeService {
    //列表
    PageInfo<NectletChangeVO> findTNeckletChangeList(HashMap<String,Object> map);
    //详情
    NectletChangeVO findOne(Integer Id);
}

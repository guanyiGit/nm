package com.statisanalysis.wx.dao;

import com.statisanalysis.wx.vo.Result;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: linchong
 * @Date: 2018/11/26 11:26
 * @Version 1.0
 */
@Mapper
public interface GoodsCountDao {
    //药品发放数量、实验试剂、个人防护用品统计
    List<Result> getGoodsInfo(Map<String,Object> map);
    //犬只项圈发放数量
    Integer getDeviceDistri(Map<String,Object> map);
}

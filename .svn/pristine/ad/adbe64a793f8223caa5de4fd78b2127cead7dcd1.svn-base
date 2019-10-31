package com.dogmanager.service;

import com.dogmanager.VO.StrayDogVO;
import com.entities.TStrayDog;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/9/28.
 */
public interface StrayDogService {
    //新增
    void saveStrayDog(TStrayDog strayDog);
    //列表
    PageInfo<StrayDogVO> findStrayDogList(HashMap<String,Object> map);
    //详情
    StrayDogVO findStrayDogById(Integer Id);

    String checkNo(String no);

    //列表
    PageInfo<StrayDogVO> findStrayDogListByDate(String startDate, String endDate, int pageNum, int pageSize,Integer pro,Integer orgId);

    //删除
    void delete(Integer Id);
    //修改
    void update(TStrayDog strayDog);
}

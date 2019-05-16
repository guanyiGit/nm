package com.dogmanager.service;

import com.dogmanager.VO.DogCanselVO;
import com.dogmanager.utils.DogResult;
import com.entities.TDogCancel;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/9/28.
 */
public interface DogCanselService {
    //新增
    void save(TDogCancel dogCancel);
    //查看
    DogCanselVO findDogCancelById(Integer Id);
    //修改
    void updateDogCancel(TDogCancel dogCancel);
    //删除
    void deleteDogCancel(Integer Id);
    //列表
    PageInfo<DogCanselVO> findDogCanselList(HashMap<String,Object> map);

    DogResult findTSysDict();

}

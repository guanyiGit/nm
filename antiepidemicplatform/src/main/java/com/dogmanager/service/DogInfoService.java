package com.dogmanager.service;

import com.dogmanager.VO.DogInfoVO;
import com.dogmanager.VO.TProtectorVO;
import com.dogmanager.utils.DogResult;
import com.entities.*;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/9/27.
 */
public interface DogInfoService {
    //主要用于判断犬只是否存在
    TDogInfo selectByPrimaryKey(String traceId);
    //删除图片
    int deletePic(Integer fid);
    //查图片
    TMediaInfo findPicOne(Integer fid);
    //查图片集合
    List<HashMap<String,Object>> findPicList(Integer id,Integer type);
    //查视频集合
    List<HashMap<String,Object>> findVideoList(Integer id,Integer type);

    //绑定设备
    void  insertDogDeviceNo(TDeviceRefDog deviceRefDog);

    //查设备绑定情况
    TDeviceRefDog selectByTraceId(String traceId);

    //检查设备是否已被绑定
    Boolean checkDevice(String deviceNo);

    //新增犬只
    int saveDogInfo(DogInfoVO dogInfo);

    //查看犬只
    HashMap<String,Object> findOne(String traceId);

    //WX查看犬只
    DogResult WXfindOne(String traceId,String deviceNo);


    //修改犬只
    void updateDogInfo(TDogInfo dogInfo);

    //删除犬只
    void deleteDogInfo(Integer DogId);

    //查看犬只列表
    PageInfo<DogInfoVO> findDogInfoList(HashMap<String,Object> map);
    //wx查看犬只列表
    PageInfo<DogInfoVO> wxfindDogInfoList(HashMap<String,Object> map);
    //wx是否防疫列表
    PageInfo<DogInfoVO> wxfindDogInfoListIsAnt(HashMap<String,Object> map) throws Exception;

    //查看犬只列表
    PageInfo<DogInfoVO> findBindDogInfoList(HashMap<String,Object> map);

    //犬主更换
    void saveOwnerChange(TOwnerChange ownerChange);

    //项圈更换
    void saveNectletChange(TNeckletChange neckletChange);

    //查询犬主(根据防疫员)
    List<HashMap<String,Object>> findDogOwner(Integer protector);

    //查询品种
    List<HashMap<String,Object>> findBreed();

    //查询电子围栏
    List<HashMap<String,Object>> findFence(Integer protector);

    //查设备
    List<HashMap<String,Object>> findDevice();

    //设备编号查设备id
    Integer findDeviceIdByDeviceNo(String deviceNo);
    //流浪犬专用
    List<HashMap<String,Object>> findProtector3();
    //组织机构查防疫员（增加“全部”）
    List<TProtectorVO> findProtector2();
    //设备编号查询设备信息
    DeviceInfo findDeviceByNo(String deviceNo);
    //检查该设备是否重复绑定
    boolean checkRepeated(String deviceNo);
    //根据deviceNo获取犬主名称和犬名称
    Map<String,Object> getOwnerNameAndDogName(String traceId);
    //根据犬名或者犬主名查询traceId
    List<Map<String,Object>>  getTraceIdByOwnerNameOrDogName(String string,Integer protectorId);



}

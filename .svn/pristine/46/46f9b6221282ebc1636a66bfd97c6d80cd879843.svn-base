package com.posiition.service;

import com.posiition.VO.DogRecordInfo;
import com.posiition.VO.DogRefDeviceRecord;
import com.posiition.VO.DogRefDeviceRecordMax;
import com.posiition.VO.postionVO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定位相关
 */
public interface PositionService {

    /**
     * 查询犬只定位信息
     *
     * @param dogIds 犬id集合
     * @return 犬id, 设备数据
     */
    List<DogRefDeviceRecord> pistionList(List<Integer> dogIds);

    /**
     * 防疫员查询我的犬只定位集合
     * @param protectorId
     * @return
     */
    List<DogRefDeviceRecordMax>  findDeviceDataByProtectorId(Integer protectorId);

    /**
     * 根据DeviceNo获取犬只定位数据
     * @param traceId
     * @return
     */
    DogRefDeviceRecordMax findDeviceDataByTraceId(String traceId);


    /**
     * 查询犬的轨迹信息
     * @param traceId
     * @param date
     * @return
     */
    List<DogRefDeviceRecordMax> getContrailByTraceIdAndDate(String traceId, Date date);

    //定位信息列表
    List<postionVO> findDogsPosition(HashMap<String,Object> map);

    //查询某范围内的所有犬只坐标

    List<DogRecordInfo> selectDogInfosByRange(Map<String,Object> map);





}

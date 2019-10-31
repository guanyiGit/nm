package com.posiition.mapper;

import com.posiition.VO.DogRefDeviceRecord;
import com.posiition.VO.DogRefDeviceRecordMax;
import com.posiition.VO.postionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface TDeviceRecordMapper {

    /**
     * 查询犬只最近的一条定位信息
     *
     * @param dogIds 犬id集合
     * @return
     */
    List<DogRefDeviceRecord> findDeviceDataGroubyDogId(List<Integer> dogIds);


    /**
     * 查询防疫员下的犬的定位信息
     *
     * @param protectorId
     * @return findDeviceDataByProtectorId
     */
    List<DogRefDeviceRecordMax> findDeviceDataByProtectorId(Integer protectorId);
//    List<DogRefDeviceRecord> findDeviceDataByUserId(Integer userId);

    /**
     * 根据DeviceNo获取犬只定位数据
     * @param traceId
     * @return
     */
    DogRefDeviceRecordMax findDeviceDataByTraceId(@Param("traceId") String traceId);
    /**
     * 查询犬的轨迹信息
     *
     * @param dogId 犬id
     * @param day   相隔时间 天
     */
    List<DogRefDeviceRecord> getLocusByDogId(@Param("dogId") Integer dogId, @Param("day") Integer day);

    /**
     * 查询犬的轨迹信息
     * @param traceId
     * @param date
     * @return
     */
    List<DogRefDeviceRecordMax> getContrailByTraceIdAndDate(@Param("traceId")String traceId,@Param("date") Date date);


    //定位信息列表
    List<postionVO> findDogsPosition(HashMap<String,Object> map);


}

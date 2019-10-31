package com.dogmanager.dao;

import com.dogmanager.VO.Antiepidemic;
import com.dogmanager.VO.DogInfoVO;
import com.dogmanager.VO.TProtectorVO;
import com.entities.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface TDogInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TDogInfo record);

    TDogInfo selectByPrimaryKey(@Param("traceId") String traceId,@Param("protector") Integer protector);

    int updateByPrimaryKey(TDogInfo record);

    //更改犬只状态
    int updateDogStatus(@Param("status") Integer status,@Param("traceId") String traceId);

    //删除图片
    int deletePic(Integer fid);
    //查找图片
    TMediaInfo findPicOne(Integer fid);

    //查图片集合
    List<HashMap<String,Object>> findPicList(@Param("id") Integer id,@Param("type") Integer type);
    //查视频集合
    List<HashMap<String,Object>> findVideoList(@Param("id") Integer id,@Param("type") Integer type);

    //犬只列表
    List<DogInfoVO> findDogInfoList(HashMap<String,Object> map);


    //wx犬只列表
    List<DogInfoVO> wxfindDogInfoList(HashMap<String,Object> map);

    //wx是否防疫列表
    List<DogInfoVO> wxfindDogInfoListIsAnt(HashMap<String,Object> map);


    //绑定设备的犬只列表
    List<DogInfoVO> findBindDogInfoList(HashMap<String,Object> map);
    //犬只详情
    DogInfoVO findDogInfoByDogId(String traceId);
    //犬只详情（多语言）
    DogInfoVO findDogInfoByDogIdI18N(Map<String,Object> map);
    //防疫
    List<Antiepidemic> findAntiepidemicList(String traceId);
    //粪便
    List<HashMap<String,Object>> findManureList(String traceId);
    //绑定设备
    int  insertDogDeviceNo(TDeviceRefDog deviceRefDog);
    //查设备绑定情况
    TDeviceRefDog selectByTraceId(String traceId);

    //修改设备状态
    void updateDeviceStatus(@Param("status") Integer status, @Param("deviceNo") String deviceNo);
    //查询犬主(根据防疫员)
    List<HashMap<String,Object>> findDogOwner(Integer protector);
    //查询品种
    List<HashMap<String,Object>> findBreed(Map<String, Object>map);
    //查询电子围栏
    List<HashMap<String,Object>> findFence(Integer protector);
    //查询设备
    List<HashMap<String,Object>> findDevice();
    //设备编号查设备id
    Integer findDeviceIdByDeviceNo(String deviceNo);
    //流浪犬专用
    List<HashMap<String,Object>> findProtector3(Integer orgId);
    //组织机构查防疫员
    List<TProtectorVO> findProtector2(Integer orgId);
    //用户id查防疫员id
    Integer findProId(Integer userId);

    //查犬主
    java.lang.String findDogOwnerById(Integer ownerId);

    //检查设备是否已被绑定
    TDeviceRefDog checkDevice(String deviceNo);

    //设备编号查该设备
    DeviceInfo findDeviceByNo(String deviceNo);

    //根据traceId查询犬名或者犬主名
    Map<String,Object> getOwnerNameAndDogName(String traceId);

    //根据犬名或者犬主名查询deviceId
    List<Map<String,Object>> getTraceIdByOwnerNameOrDogName(@Param("string") String string,@Param("protectorId")Integer protectorId);



    /**
     * 犬只轨迹中的犬只列表
     * @param map
     * @return
     */
	List<DogInfoVO> findDogList(Map<String, Object> map);
	
	/**
	 * 轨迹列表中犬只记录总数
	 * @param map
	 * @return
	 */
	Integer findDogCount(Map<String, Object> map);
	
	/**
	 * 跟用防疫员userId查询犬只
	 */
	int judgeTraceId(@Param("userId")Integer userId,@Param("traceId")String traceId);
}
package com.posiition.service.impl;

import com.devicemanagement.dao.DeviceRecordDao;
import com.dogmanager.dao.TDogInfoMapper;
import com.entities.DeviceRecord;
import com.entities.OrgInfo;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.OrgInfoService;
import com.posiition.VO.*;
import com.posiition.mapper.TDeviceRecordMapper;
import com.posiition.service.BizService;
import com.posiition.service.PositionService;
import com.utils.*;
import com.utils.pojo.GPS;
import com.utils.pojo.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {



    @Autowired
    private TDeviceRecordMapper tDeviceRecordMapper;

    @Autowired
    private BizService bizService;

    @Autowired
    private TDogInfoMapper dogInfoMapper;

    @Autowired
    private OrgInfoService orgInfoService;
    @Autowired
    private DeviceRecordDao deviceRecordDao;



    private Logger logger = LoggerFactory.getLogger(PositionServiceImpl.class);


    @Override
    public List<postionVO> findDogsPosition(HashMap<String, Object> params) {
        //查询列表数据
        UserDO user = ShiroUtils.getUser();
        if(user.getRoles().get(0).getType()==2){
            Integer proId = dogInfoMapper.findProId(user.getUserId().intValue());
            params.put("protector",proId);
        }else{
            List<OrgInfo> orgList=orgInfoService.orgList();
            List<Integer> res=new ArrayList<>();
            if(StringUtils.isEmpty(params.get("orgId"))) {
                res= OrgUtils.orgReverse(orgList, user.getDeptId().intValue(), res);
            }else{
                res= OrgUtils.orgReverse(orgList, Integer.parseInt(params.get("orgId").toString()), res);
            }
            params.put("orgList",res);
        }
        List<postionVO> dogsPosition = tDeviceRecordMapper.findDogsPosition(params);
        if(dogsPosition.size()>0){
          for (postionVO pv:dogsPosition){
              GPS gps = GPSConverterUtils.gps84_To_Gcj02(pv.getLat(), pv.getLng());
              pv.setLat(gps.getLat());
              pv.setLng(gps.getLon());
          }
        }
        return dogsPosition;
    }

    @Override
    public List<DogRecordInfo> selectDogInfosByRange(Map<String, Object> map) {
//        String currDate = DateUtils.format(new Date());
//        //时间
//        map.put("dateTime",currDate);
        List<DogRecordInfo> records = deviceRecordDao.selectDogInfosByRange(map);
        double lng = Double.valueOf(map.get("lng").toString());
        double lat = Double.valueOf(map.get("lat").toString());
        int range = 50000;
        //开始过滤 50000米内的数据
        //坐标转换
        records.stream().forEach(record ->{
            Double lng1 = record.getLng();
            Double lat1 = record.getLat();

            if(lng1!=null){
                Map<String, Double> lngLat = GaoDeUtils.coordinateSwitch(Double.valueOf(lng1), Double.valueOf(lat1));
                Double lg = lngLat.get("lng");
                Double lt = lngLat.get("lat");
                record.setLng(lg);
                record.setLat(lt);
            }
        });
        List<DogRecordInfo> list = records.stream().filter(record -> {
            Double lg = record.getLng();
            Double lt = record.getLat();
            double disance = GaoDeUtils.GetDistance(Double.valueOf(lg), Double.valueOf(lt), lng, lat);
            return disance < range;
        }).collect(Collectors.toList());
        return list;
    }
        /*if (dogsPosition == null || dogsPosition.size()<=0) {
            logger.warn("没有定位信息");
            return dogsPosition;
        }
        List<Point> list = new ArrayList<>();
        for (postionVO p:dogsPosition){
            Point point = new Point(p.getLng(),p.getLat());
            list.add(point);
        }
        //转换后的设备坐标信息
        List<Point> points = new ArrayList<>();
        try {
            points = Converter.WGS_84GCJ_02(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (points == null || points.size() != dogsPosition.size()) {
            logger.warn("坐标地址转换失败");
            throw new RuntimeException();
        }
        Iterator<Point> it = points.iterator();
        //将设备坐标重新赋值为转换后的坐标值
        dogsPosition.stream()
                .forEach(n -> {
                    if (it.hasNext()) {
                        Point point = it.next();
                        n.setLng(point.getLongitude());
                        n.setLat(point.getLatitude());
                    }
                });*/




    @Override
    public List<DogRefDeviceRecord> pistionList(List<Integer> dogIds) {
        List<DogRefDeviceRecord> dogRefDeviceRecords = tDeviceRecordMapper.findDeviceDataGroubyDogId(dogIds);
        if (dogRefDeviceRecords != null && dogRefDeviceRecords.size() > 0) {
            // 坐标处理
            dogRefDeviceRecords.stream()
                    .forEach(x -> {
                        //每一个设备的数据
                        List<DeviceInfoRefDeviceRecord> deviceInfoRefDeviceRecords = x.getDeviceInfoRefDeviceRecords();
                        deviceInfoRefDeviceRecords.stream()
                                .forEach(z -> {
                                    List<DeviceRecord> deviceRecords = z.getDeviceRecords();
                                    //提取每个设备的坐标信息
                                    List<Point> points = deviceRecords.stream()
                                            .map(m -> {
                                                Double lat = m.getLat();
                                                Double lng = m.getLng();
                                                Point point = new Point();
                                                point.setLatitude(lat);
                                                point.setLongitude(lng);
                                                return point;
                                            })
                                            .collect(Collectors.toList());

                                    //转换后的设备坐标信息
                                    try {
                                        points = Converter.WGS_84GCJ_02(points);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (points == null || points.size() != deviceRecords.size()) {
                                        logger.warn("坐标地址转换失败");
                                        throw new RuntimeException();
                                    }

                                    Iterator<Point> it = points.iterator();
                                    //将设备坐标重新赋值为转换后的坐标值
                                    deviceRecords.stream()
                                            .forEach(n -> {
                                                if (it.hasNext()) {
                                                    Point point = it.next();
                                                    n.setLng(point.getLongitude());
                                                    n.setLat(point.getLatitude());
                                                }
                                            });

                                });
                    });

        }
        return dogRefDeviceRecords;
    }

//    @Override
//    public List<DogRefDeviceRecord> findDeviceDataByUserId(Integer userId) {
//        if(bizService.isOrg(userId)){
//            return tDeviceRecordMapper.findDeviceDataByUserId(userId);
//        }
//        logger.info("当前用户不是防疫员");
//        return null;
//    }


    @Override
    public List<DogRefDeviceRecordMax> getContrailByTraceIdAndDate(String traceId, Date date) {
        List<DogRefDeviceRecordMax> dogContrails = tDeviceRecordMapper.getContrailByTraceIdAndDate(traceId, date);
        return dogContrails;
    }

    @Override
    public List<DogRefDeviceRecordMax> findDeviceDataByProtectorId(Integer protectorId) {
        List<DogRefDeviceRecordMax> dogRefDeviceRecordMaxs = tDeviceRecordMapper.findDeviceDataByProtectorId(protectorId);
        return this.DeviceRecordUtils(dogRefDeviceRecordMaxs);
    }

    @Override
    public DogRefDeviceRecordMax findDeviceDataByTraceId(String traceId) {
        DogRefDeviceRecordMax dogRefDeviceRecordMax = tDeviceRecordMapper.findDeviceDataByTraceId(traceId);
        return this.toGCJ_02(dogRefDeviceRecordMax);
    }



    public List<DogRefDeviceRecordMax> DeviceRecordUtils(List<DogRefDeviceRecordMax> dogRefDeviceRecordMaxs) {
        List<DogRefDeviceRecordMax> dogRefDeviceRecordMaxArray = new ArrayList<>();
        for (DogRefDeviceRecordMax dogRefDeviceRecord : dogRefDeviceRecordMaxs) {
            dogRefDeviceRecord = this.toGCJ_02(dogRefDeviceRecord);
            dogRefDeviceRecordMaxArray.add(dogRefDeviceRecord);
        }
        return dogRefDeviceRecordMaxArray;
    }

    public DogRefDeviceRecordMax toGCJ_02(DogRefDeviceRecordMax dogRefDeviceRecordMax) {
        try {
            //将坐标转成GCJ-02坐标
            List<Point> points = new ArrayList<Point>();
            //获取point坐标
            Point point = new Point(dogRefDeviceRecordMax.getLng(), dogRefDeviceRecordMax.getLat());
            points.add(point);
            List<Point> gcj_02_points = Converter.WGS_84GCJ_02(points);
            for (Point point2 : gcj_02_points) {
                dogRefDeviceRecordMax.setLat(point2.getLatitude());
                dogRefDeviceRecordMax.setLng(point2.getLongitude());
            }
            return dogRefDeviceRecordMax;
        } catch (Exception e) {
            return null;
        }
    }


    


}

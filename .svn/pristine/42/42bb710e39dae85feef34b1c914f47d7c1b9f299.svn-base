package com.devicemanagement.service.impl;

import com.basicInfo.dao.TProtectorMapper;
import com.devicemanagement.dao.DeviceRecordDao;
import com.devicemanagement.service.PathService;
import com.dogmanager.VO.DogInfoVO;
import com.dogmanager.dao.TDogInfoMapper;
import com.entities.*;
import com.entities.TProtectorExample.Criteria;
import com.orgmanagement.dao.AreaInfoDao;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.UserDO;
import com.utils.*;
import com.utils.pojo.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PathServiceImpl implements PathService {
	
	@Autowired
	private TDogInfoMapper dogMapper;
	@Autowired
	private TProtectorMapper protectorMapper;
	@Autowired
	private DeviceRecordDao deviceRecordDao;
	@Autowired
	private AreaInfoDao areaInfoDao;
	
	@Autowired
	private OrgInfoDao orgDao;
	@Override
	public PageUtils findDogList(Integer index, Integer pageSize, String searchKey,String selectedDate) {
		//当前登录用户信息
		UserDO user = ShiroUtils.getUser();
		//获取当前登录用户的角色类型
		int type = user.getRoles().get(0).getType();
		
		//获取当前登录人的组织id
		int orgId = user.getDeptId().intValue();
		Map<String, Object>map=new HashMap<>();
			map.put("index", index);
			map.put("pageSize", pageSize);
			map.put("searchKey", searchKey);
			map.put("selectedDate", selectedDate);
		//当前角色是防疫员
		if(type==2) 
		{
			int userId = user.getUserId().intValue();
				TProtectorExample example=new TProtectorExample();
					Criteria criteria = example.createCriteria();
						criteria.andUserIdEqualTo(userId);
				List<TProtector> protectorList = protectorMapper.selectByExample(example);
					Integer protectorId = protectorList.get(0).getId();
					map.put("protectorId", protectorId);
		
		}
		//当前角色非防疫员
		else 
		{
			List<OrgInfo> orgList=orgDao.orgList();
            List<Integer>res=new ArrayList<>();
            res= OrgUtils.orgReverse(orgList, orgId, res);
            map.put("res", res);
		}
		//轨迹犬只列表
		List<DogInfoVO>list=dogMapper.findDogList(map);
			for(DogInfoVO dog:list) {
				Integer areaId = dog.getAreaId();
				List<AreaInfo> lists=new ArrayList<>();
				lists=areaUtils.areaR(areaInfoDao.getList(), areaId, lists);
				dog.setTownList(lists);
			}
		//轨迹犬只总记录数
		Integer total= dogMapper.findDogCount(map);
		return new PageUtils(list, total);
	}
	
	
	/**
	 * 获得犬只活动轨迹
	 * @param traceId:溯源id
	 * @param selectedDate:选中的日期
	 */
	@Override
	public List<DeviceRecord> getDogPath(String traceId, String selectedDate) {
		Map<String, Object>map=new HashMap<>();
			map.put("traceId", traceId);
			map.put("selectedDate", selectedDate);
			//犬只活动轨迹集合
			List<DeviceRecord>recordList=deviceRecordDao.getDogPath(map);
			//转换坐标后的list
			List<DeviceRecord> newDevicedataList = new ArrayList<>();
			for(DeviceRecord deviceData:recordList) {
				if(deviceData.getLat()== null || deviceData.getLat() ==0 || deviceData.getLng()== null || deviceData.getLng() ==0 ){
					continue;
				}
				DeviceRecord newDevicedata = this.toGCJ_02(deviceData);
				newDevicedataList.add(newDevicedata);
			}
			
		return newDevicedataList;
	}
	
	private DeviceRecord toGCJ_02(DeviceRecord deviceData){
		try {
			//将坐标转成GCJ-02坐标
			List<Point> points = new ArrayList<Point>();
			//获取point坐标
			Point point = new Point(deviceData.getLng().doubleValue(),deviceData.getLat().doubleValue());
			points.add(point);
			List<Point>gcj_02_points=Converter.WGS_84GCJ_02(points);
			for (Point point2 : gcj_02_points) {
				deviceData.setLng(point2.getLongitude());
				deviceData.setLat(point2.getLatitude());
			}
			return deviceData;
		} catch (Exception e) {
			return null;
		}
	}

}

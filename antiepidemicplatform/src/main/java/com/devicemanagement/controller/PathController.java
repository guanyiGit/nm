package com.devicemanagement.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devicemanagement.service.PathService;
import com.dogmanager.dao.TDogInfoMapper;
import com.entities.DeviceRecord;
import com.entities.TDogInfo;
import com.posiition.VO.fenceDetailVO;
import com.posiition.service.DeviceFenceService;
import com.utils.PageUtils;

/**
 * 犬只活动轨迹
 * @author ZhongZhong
 *
 */
@Controller
@RequestMapping("/biz/path")
public class PathController{
	private static Logger logger = LoggerFactory.getLogger(PathController.class);
   @Autowired
   private PathService pathService;
   
   @Autowired
   private DeviceFenceService fenService;
   
   @Autowired 
   private TDogInfoMapper dogMapper;
   
   /**
    * 跳转到犬只轨迹页面
    */
   @RequestMapping("/toPath_List")
   @RequiresPermissions("/biz/path/toPath_List")
   public String toPath_List() {
	   
	   return "devicemanagement/path/path_List";
   }
   
   
   
   
   
	/**
	 * 查询轨迹犬只列表
	 * @param index:偏移量
	 * @param pageSize:页面大小
	 * searchKey:(溯源id,犬主姓名,手机号码,身份证号)
	 * selectedDate:选择日期
	 */
   	@ResponseBody
	@GetMapping("/findDogList")
	@RequiresPermissions("/biz/path/toPath_List")
	public PageUtils findDogList(Integer index,Integer pageSize,String searchKey,String selectedDate) {
		PageUtils pageUtils=null;
		try {
			pageUtils=pathService.findDogList(index,pageSize,searchKey,selectedDate);
		} catch (Exception e) {
			logger.warn("查看轨迹列表失败!");
		}
		return pageUtils;
	}
	
	/**
	 * 获得犬只活动轨迹
	 * @param deviceNo:溯源id
	 * @param selectedDate:选中的日期
	 */
	@RequestMapping("/getDogPath")
	@RequiresPermissions("/biz/path/getDogPath")
	public String getDogPath(String traceId,String selectedDate,Integer id,Model model) {
		List<DeviceRecord>recordList=null;
		fenceDetailVO fence =null;
		Map<String, Object> ownerNameAndDogName =null;
		try {
			recordList=pathService.getDogPath(traceId,selectedDate);
			 fence = fenService.findFenceByDogId(id);
			 ownerNameAndDogName = dogMapper.getOwnerNameAndDogName(traceId);
		} catch (Exception e) {
			logger.warn("查看轨迹失败!");
		}
		model.addAttribute("record", recordList);
		model.addAttribute("fence", fence);
		model.addAttribute("selectedDate",selectedDate);
		model.addAttribute("ownerNameAndDogName", ownerNameAndDogName);
		return "devicemanagement/path/path_Detail";
	}
	
	
	
	
}

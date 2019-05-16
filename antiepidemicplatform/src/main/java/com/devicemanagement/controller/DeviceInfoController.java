package com.devicemanagement.controller;

import com.devicemanagement.dao.DeviceInfoDao;
import com.devicemanagement.service.DeviceInfoService;
import com.entities.DeviceInfo;
import com.entities.OrgInfo;
import com.orgmanagement.domain.RoleDO;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.OrgInfoService;
import com.sys.controller.BaseController;
import com.utils.*;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-30 14:01:54
 */
 
@Controller
@RequestMapping("/biz/deviceInfo")
public class DeviceInfoController extends BaseController {
	@Autowired
	private DeviceInfoService deviceInfoService;
	
	@Autowired
	private DeviceInfoDao deviceInfoDao;
	@Autowired
    private OrgInfoService orgInfoService;

	@GetMapping()
//	@RequiresPermissions("system:deviceInfo:deviceInfo")
	String DeviceInfo(Model model){
		model.addAttribute("user", getUser());
	    return "devicemanagement/deviceManage";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("system:deviceInfo:deviceInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){

		UserDO user = getUser();
		Long userId = user.getUserId();
//		int type = role.getType();
//		params.put("type",type);
		params.put("userId",userId);


        //查询列表数据
        Query query = new Query(params);

		List<Map<String,Object>> deviceInfoList = deviceInfoService.list(query);
		int total = deviceInfoService.count(query);
		PageUtils pageUtils = new PageUtils(deviceInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("system:deviceInfo:add")
	String add(){
	    return "system/deviceInfo/add";
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("system:deviceInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		Map<String,String> deviceInfo = deviceInfoService.get(id);
		model.addAttribute("deviceInfo", deviceInfo);
	    return "system/deviceInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("system:deviceInfo:add")
	public R save(DeviceInfo deviceInfo){
		if(deviceInfoService.save(deviceInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("system:deviceInfo:edit")
	public R update( DeviceInfo deviceInfo){
		deviceInfoService.update(deviceInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("system:deviceInfo:remove")
	public R remove( Integer id){
		if(deviceInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("system:deviceInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		deviceInfoService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping("/byDeviceNo")
	@ResponseBody
	public List<Map<String,Object>>  selectByDeviceNo(@RequestParam Map<String, Object> params ){
		List<Map<String,Object>>  json = deviceInfoService.selectByDeviceNo(params);
		return json;
	}
	
	
	

}

package com.devicemanagement.controller;

import com.devicemanagement.service.DeviceRecordService;
import com.dogmanager.service.DogInfoService;
import com.entities.DeviceRecord;
import com.entities.OrgInfo;
import com.orgmanagement.domain.RoleDO;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.OrgInfoService;
import com.orgmanagement.service.UserInfoService;
import com.sys.controller.BaseController;
import com.utils.OrgUtils;
import com.utils.PageUtils;
import com.utils.Query;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-30 14:41:44
 */
 
@Controller
@RequestMapping("/biz/deviceRecord")
public class DeviceRecordController extends BaseController {
	@Autowired
	private DeviceRecordService deviceRecordService;
	@Autowired
	private DogInfoService dogInfoService;
	@Autowired
    private OrgInfoService orgInfoService;
	@Autowired
    private UserInfoService userInfoService;


	@GetMapping()
//	@RequiresPermissions("system:deviceRecord:deviceRecord")
	String DeviceRecord(){
	    return "devicemanagement/monitoringData";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("system:deviceRecord:deviceRecord")
	public PageUtils list(@RequestParam Map<String, Object> params){

        UserDO user = getUser();
        Long userId = user.getUserId();
        RoleDO role = user.getRoles().get(0);
        int type = role.getType();

        Long deptId = user.getDeptId();
        List<OrgInfo> orgInfos = orgInfoService.orgList();
        List<Integer>  orgIds = OrgUtils.orgReverse(orgInfos, Integer.parseInt(deptId.toString()), new ArrayList<>());
        params.put("orgIds",orgIds);
        params.put("type",type);
        params.put("userId",userId);
		//查询列表数据
        Query query = new Query(params);
		List<Map<String,Object>> deviceRecordList = deviceRecordService.list(query);
		int total = deviceRecordService.count(query);
		PageUtils pageUtils = new PageUtils(deviceRecordList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("system:deviceRecord:add")
	String add(){
	    return "system/deviceRecord/add";
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("system:deviceRecord:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		Map<String,String> deviceRecord = deviceRecordService.get(id);
		model.addAttribute("deviceRecord", deviceRecord);
	    return "system/deviceRecord/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("system:deviceRecord:add")
	public R save(DeviceRecord deviceRecord){
		if(deviceRecordService.save(deviceRecord)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("system:deviceRecord:edit")
	public R update( DeviceRecord deviceRecord){
		deviceRecordService.update(deviceRecord);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("system:deviceRecord:remove")
	public R remove( Integer id){
		if(deviceRecordService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("system:deviceRecord:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		deviceRecordService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping("byDeviceNo")
	public void selectByDeviceNo(@RequestParam Map<String, Object> params){
		deviceRecordService.selectByDeviceNo(params);
	}
	@GetMapping("siteByDeviceNo")
	public void selectSiteByDeviceNo(@RequestParam Map<String, Object> params){
		deviceRecordService.selectSiteByDeviceNo(params);
	}
	@GetMapping("trailByDeviceNoAndDate")
	public void selectTrailByDeviceNoAndDate(@RequestParam Map<String, Object> params){
		deviceRecordService.selectTrailByDeviceNoAndDate(params);
	}

	@ResponseBody
	@GetMapping("/initFenceSelctByCreateId")
	public List<HashMap<String, Object>> initFenceSelctByCreateId(@RequestParam Map<String, Object> params){
		Long userId = getUserId();
		List<HashMap<String, Object>> fences = dogInfoService.findFence(Integer.parseInt(userId.toString()));
		return  fences;
	}


	
}

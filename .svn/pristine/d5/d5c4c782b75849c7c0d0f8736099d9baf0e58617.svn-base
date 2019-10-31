package com.goodsmanagement.controller;

import com.entities.DeviceDistributionDO;
import com.entities.OrgInfo;
import com.goodsmanagement.service.DeviceDistributionService;
import com.goodsmanagement.vo.DeviceDistributionVO;
import com.orgmanagement.service.OrgInfoService;
import com.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-05 15:17:22
 */
 
@Controller
@RequestMapping("/biz/deviceDistribution")
public class DeviceDistributionController {
	@Autowired
	private DeviceDistributionService deviceDistributionService;
	@Autowired
    private OrgInfoService orgInfoService;
	private static final Logger logger = LoggerFactory.getLogger(DeviceDistributionController.class);


	@GetMapping()
//	@RequiresPermissions("system:deviceDistribution:deviceDistribution")
	String DeviceDistribution(Model model){
        model.addAttribute("user",ShiroUtils.getUser());
	    return "goodsmanagement/deviceDistribution";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("system:deviceDistribution:deviceDistribution")
	public PageUtils list(@RequestParam Map<String, Object> params){
        Object oj = params.get("orgId");
        Long deptId = ShiroUtils.getUser().getDeptId();
        Long orgId = null;
        if(oj != null && oj.toString().trim() != "") {
            orgId = Long.parseLong(oj.toString());
        }
        List<Integer> deptIdList = new ArrayList<>();
        if(orgId == null || orgId == 9999) {
            //查询当前组织下全部组织的物资发放记录
            List<OrgInfo> orgInfos = orgInfoService.orgList();
            deptIdList = orgtills.getDeptsById(orgInfos, deptId.intValue(), new ArrayList<>()).stream().map(org -> org.getId()).collect(Collectors.toList());
        }else {
            //指定查询的组织
            deptIdList.add(orgId.intValue());
        }
        params.put("deptIdList",deptIdList);
		//查询列表数据
        Query query = new Query(params);
		List<DeviceDistributionVO> deviceDistributionList = deviceDistributionService.list(query);
        //用于前端判断“修改”“删除”权限
        if(deviceDistributionList != null && deviceDistributionList.size() > 0) {
            Integer userId = ShiroUtils.getUserId().intValue();
            for (DeviceDistributionVO vo:
                    deviceDistributionList) {
                vo.setUserId(userId);
            }
        }
		int total = deviceDistributionService.count(query);
		PageUtils pageUtils = new PageUtils(deviceDistributionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("system:deviceDistribution:add")
	String add(){
        int roleType = ShiroUtils.getUser().getRoles().get(0).getType();
        if(roleType == 3 || roleType == 8){
            //乡级管理员
            return "goodsmanagement/deviceDistributionAdd1";
        }else if(roleType == 5 || roleType == 6 || roleType == 9 || roleType == 10){
            //其他组织角色
            return "goodsmanagement/deviceDistributionAdd2";
        }else {
        	logger.error("DeviceDistributionController.add|角色不合法！ roleType = {}",roleType);
        	return "";
		}
	}

    @RequestMapping("/detail/{id}")
    String detail(@PathVariable("id") Integer id,Model model) {
        DeviceDistributionVO deviceDistribution = deviceDistributionService.get(id);
        model.addAttribute("deviceDistribution", deviceDistribution);
        Integer receiveOrg = deviceDistribution.getReceiveOrg();
        if(receiveOrg != null) {
            //查看发放给县级、乡级的项圈设备
            return "goodsmanagement/deviceDistributionDetail2";
        }else {
            //查看发放给防疫员的项圈设备
            return "goodsmanagement/deviceDistributionDetail1";
        }
    }

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("system:deviceDistribution:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		DeviceDistributionVO deviceDistribution = deviceDistributionService.get(id);
		model.addAttribute("deviceDistribution", deviceDistribution);
        Integer receiveOrg = deviceDistribution.getReceiveOrg();
        if(receiveOrg != null) {
            //编辑发放给县级、乡级的项圈设备
            return "goodsmanagement/deviceDistributionEdit2";
        }else {
            //编辑发放给防疫员的项圈设备
            return "goodsmanagement/deviceDistributionEdit1";
        }
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("system:deviceDistribution:add")
	public R save(DeviceDistributionDO deviceDistribution){
	    deviceDistribution.setDistributorOrgId(ShiroUtils.getUser().getDeptId().intValue());
	    deviceDistribution.setDistributeDate(new Date());
	    deviceDistribution.setCreateDate(new Date());
	    deviceDistribution.setOperatorId(ShiroUtils.getUserId().intValue());
		if(deviceDistributionService.save(deviceDistribution)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("system:deviceDistribution:edit")
	public R update( DeviceDistributionDO deviceDistribution){
        int row = deviceDistributionService.update(deviceDistribution);
        if(row > 0) {
            return R.ok();
        }else {
            return R.error();
        }
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("system:deviceDistribution:remove")
	public R remove( Integer id){
		if(deviceDistributionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("system:deviceDistribution:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		deviceDistributionService.batchRemove(ids);
		return R.ok();
	}
	
}

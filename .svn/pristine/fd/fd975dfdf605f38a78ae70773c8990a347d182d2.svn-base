package com.orgmanagement.controller;

import com.annotation.Log;
import com.orgmanagement.domain.RoleDO;
import com.orgmanagement.service.RoleInfoService;
import com.sys.config.Constant;
import com.sys.controller.BaseController;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 16:21:13
 */
 
@Controller
@RequestMapping("/biz/roleInfo")
public class RoleInfoController  extends BaseController {
//	@Autowired
//	private RoleInfoService roleInfoService;
//
//	@GetMapping()
//	@RequiresPermissions("system:roleInfo:roleInfo")
//	String RoleInfo(){
//	    return "orgmanagement/roleManagement";
//	}
//
//	@ResponseBody
//	@GetMapping("/list")
//	@RequiresPermissions("system:roleInfo:roleInfo")
//	public PageUtils list(@RequestParam Map<String, Object> params){
//		//查询列表数据
//        Query query = new Query(params);
//		List<RoleInfo> roleInfoList = roleInfoService.list(query);
//		int total = roleInfoService.count(query);
//		PageUtils pageUtils = new PageUtils(roleInfoList, total);
//		return pageUtils;
//	}
//
//	@GetMapping("/add")
//	@RequiresPermissions("system:roleInfo:add")
//	String add(){
//	    return "system/roleInfo/add";
//	}
//
//	@GetMapping("/edit/{id}")
//	@RequiresPermissions("system:roleInfo:edit")
//	String edit(@PathVariable("id") Integer id,Model model){
//		RoleInfo roleInfo = roleInfoService.get(id);
//		model.addAttribute("roleInfo", roleInfo);
//	    return "system/roleInfo/edit";
//	}
//
//	/**
//	 * 保存
//	 */
//	@ResponseBody
//	@PostMapping("/save")
//	@RequiresPermissions("system:roleInfo:add")
//	public R save(RoleInfo roleInfo){
//		if(roleInfoService.save(roleInfo)>0){
//			return R.ok();
//		}
//		return R.error();
//	}
//	/**
//	 * 修改
//	 */
//	@ResponseBody
//	@RequestMapping("/update")
//	@RequiresPermissions("system:roleInfo:edit")
//	public R update( RoleInfo roleInfo){
//		roleInfoService.update(roleInfo);
//		return R.ok();
//	}
//
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/remove")
//	@ResponseBody
//	@RequiresPermissions("system:roleInfo:remove")
//	public R remove( Integer id){
//		if(roleInfoService.remove(id)>0){
//		return R.ok();
//		}
//		return R.error();
//	}
//
//	/**
//	 * 删除
//	 */
//	@PostMapping( "/batchRemove")
//	@ResponseBody
//	@RequiresPermissions("system:roleInfo:batchRemove")
//	public R remove(@RequestParam("ids[]") Integer[] ids){
//		roleInfoService.batchRemove(ids);
//		return R.ok();
//	}
//
//	/**
//	 * 根据角色名称查询 roleName
//	 */
//	public void selectByRoleName(@RequestParam Map<String, Object> params){
//        RoleInfo roleInfo = roleInfoService.selectByRoleName(params);
//    }

	String prefix = "orgmanagement";
	@Autowired
	RoleInfoService roleService;

	//	@RequiresPermissions("sys:role:role")
	@GetMapping()
	String role() {
		return prefix + "/roleManagement";
	}

	//	@RequiresPermissions("sys:role:role")
	@GetMapping("/list")
	@ResponseBody()
	List<RoleDO> list() {
		List<RoleDO> roles = roleService.list();
		return roles;
	}

	@Log("添加角色")
//	@RequiresPermissions("sys:role:add")
	@GetMapping("/add")
	String add() {
		return prefix + "/add";
	}

	@Log("编辑角色")
//	@RequiresPermissions("sys:role:edit")
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		RoleDO roleDO = roleService.get(id);
		model.addAttribute("role", roleDO);
		return prefix + "/edit";
	}

	@Log("保存角色")
//	@RequiresPermissions("sys:role:add")
	@PostMapping("/save")
	@ResponseBody()
	R save(RoleDO role) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (roleService.save(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@Log("更新角色")
//	@RequiresPermissions("sys:role:edit")
	@PostMapping("/update")
	@ResponseBody()
	R update(RoleDO role) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (roleService.update(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@Log("删除角色")
//	@RequiresPermissions("sys:role:remove")
	@PostMapping("/remove")
	@ResponseBody()
	R save(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (roleService.remove(id) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}

	//	@RequiresPermissions("sys:role:batchRemove")
	@Log("批量删除角色")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] ids) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = roleService.batchremove(ids);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
}

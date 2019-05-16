package com.orgmanagement.controller;

import com.entities.OrgRefRole;
import com.orgmanagement.service.OrgRefRoleService;
import com.utils.PageUtils;
import com.utils.Query;
import com.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 17:09:34
 */
 
@Controller
@RequestMapping("/system/orgRefRole")
public class OrgRefRoleController {
	@Autowired
	private OrgRefRoleService orgRefRoleService;
	
	@GetMapping()
	@RequiresPermissions("system:orgRefRole:orgRefRole")
	String OrgRefRole(){
	    return "system/orgRefRole/orgRefRole";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:orgRefRole:orgRefRole")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OrgRefRole> orgRefRoleList = orgRefRoleService.list(query);
		int total = orgRefRoleService.count(query);
		PageUtils pageUtils = new PageUtils(orgRefRoleList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:orgRefRole:add")
	String add(){
	    return "system/orgRefRole/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:orgRefRole:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OrgRefRole orgRefRole = orgRefRoleService.get(id);
		model.addAttribute("orgRefRole", orgRefRole);
	    return "system/orgRefRole/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:orgRefRole:add")
	public R save(OrgRefRole orgRefRole){
		if(orgRefRoleService.save(orgRefRole)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:orgRefRole:edit")
	public R update( OrgRefRole orgRefRole){
		orgRefRoleService.update(orgRefRole);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:orgRefRole:remove")
	public R remove( Integer id){
		if(orgRefRoleService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:orgRefRole:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		orgRefRoleService.batchRemove(ids);
		return R.ok();
	}
	
}

package com.orgmanagement.controller;

import com.entities.ResourceInfo;
import com.entities.Tree;
import com.orgmanagement.service.ResourceInfoService;
import com.sys.controller.BaseController;
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
 * @date 2018-09-29 16:39:06
 */
 
@Controller
@RequestMapping("/biz/menu")
public class ResourceInfoController extends BaseController {
	@Autowired
	private ResourceInfoService resourceInfoService;
	
	@GetMapping()
	@RequiresPermissions("sys:menu:menu")
	String ResourceInfo(){
	    return "orgmanagement/menu/menu";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:menu:menu")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ResourceInfo> resourceInfoList = resourceInfoService.list(query);
		int total = resourceInfoService.count(query);
		PageUtils pageUtils = new PageUtils(resourceInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:resourceInfo:add")
	String add(){
	    return "system/resourceInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:resourceInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ResourceInfo resourceInfo = resourceInfoService.get(id);
		model.addAttribute("resourceInfo", resourceInfo);
	    return "system/resourceInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:resourceInfo:add")
	public R save(ResourceInfo resourceInfo){
		if(resourceInfoService.save(resourceInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:resourceInfo:edit")
	public R update( ResourceInfo resourceInfo){
		resourceInfoService.update(resourceInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:resourceInfo:remove")
	public R remove( Integer id){
		if(resourceInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:resourceInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		resourceInfoService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 根据 menuid 查询所有的子菜单
	 *
	 */

	@ResponseBody
	@GetMapping("/getMenusById/{id}")
	public List<Tree<ResourceInfo>> getMenusById(@PathVariable("id") String id, Model model){
		Long userId = getUserId();
		List<Tree<ResourceInfo>> childs = resourceInfoService.getMenusById(Integer.parseInt(userId.toString()), id);
		model.addAttribute("trees", childs);
		return childs;
	}

	/**
	 * 获取主菜单
	 */
	@ResponseBody
	@GetMapping("/getMainMenu")
	public List<Tree<ResourceInfo>> getMainMenu(){
		Long userId = getUserId();
			List<Tree<ResourceInfo>> menus = resourceInfoService.listMenuTree(Integer.parseInt(userId.toString()));
		return menus;
	}
}

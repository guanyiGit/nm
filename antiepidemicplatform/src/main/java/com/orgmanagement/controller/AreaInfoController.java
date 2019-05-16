package com.orgmanagement.controller;

import com.entities.AreaInfo;
import com.entities.Tree;
import com.orgmanagement.service.AreaInfoService;
import com.utils.PageUtils;
import com.utils.Query;
import com.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @date 2018-09-29 13:41:07
 */
 
@Controller
@RequestMapping("/biz/areaInfo")
public class AreaInfoController {
	
	private static Logger logger = LoggerFactory.getLogger(AreaInfoController.class);
	@Autowired
	private AreaInfoService areaInfoService;
	
	@GetMapping()
//	@RequiresPermissions("system:areaInfo:areaInfo")
	String AreaInfo(){
	    return "system/areaInfo/areaInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("system:areaInfo:areaInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AreaInfo> areaInfoList = areaInfoService.list(query);
		int total = areaInfoService.count(query);
		PageUtils pageUtils = new PageUtils(areaInfoList, total);
		return pageUtils;
	}

//	@RequestMapping("/getList")
//	@ResponseBody
//	public List<AreaInfo> getList() {
//		return areaInfoService.getList();
//	}
	
	@GetMapping("/add")
//	@RequiresPermissions("system:areaInfo:add")
	String add(){
	    return "system/areaInfo/add";
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("system:areaInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		AreaInfo areaInfo = areaInfoService.get(id);
		model.addAttribute("areaInfo", areaInfo);
	    return "system/areaInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("system:areaInfo:add")
	public R save(AreaInfo areaInfo){
		if(areaInfoService.save(areaInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("system:areaInfo:edit")
	public R update( AreaInfo areaInfo){
		areaInfoService.update(areaInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("system:areaInfo:remove")
	public R remove( Integer id){
		if(areaInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("system:areaInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		areaInfoService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 初始化区域下拉框
	 */

	@GetMapping( "/initAreaSelect")
	@ResponseBody
//	@RequiresPermissions("system:areaInfo:batchRemove")
	public    List<Tree<AreaInfo>> initAreaSelect(@RequestParam Map<String, Object> params){
        List<Tree<AreaInfo>> areaInfos = areaInfoService.initAreaSelect(params);
        return areaInfos;
	}
	
	
	
	
	/**
	 * @param level 区域等级
	 * 查询城市列表
	 */
	@RequestMapping("/findCityList")
	@ResponseBody
	public List<AreaInfo>findCityList(Integer level){
		List<AreaInfo>cityList=null;
			try {
				cityList=areaInfoService.findCityList(level);
			} catch (Exception e) {
				logger.warn("查询城市列表!");
			}
		return cityList;
	}
	
	/**
	 * 根据当前区域id查找直接子级区域
	 */
	@RequestMapping("/findChildrenList")
	@ResponseBody
	public List<AreaInfo>findChildrenList(Integer pId){
		List<AreaInfo>childrenList=null;
			try {
				childrenList=areaInfoService.getChildrenList(pId);
			} catch (Exception e) {
				logger.warn("根据父级id查找直接下级区域失败!");
			}
		return childrenList;
	}
	
}

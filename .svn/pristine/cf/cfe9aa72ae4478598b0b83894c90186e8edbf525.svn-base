package com.basicInfo.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.basicInfo.service.ProtectorService;
import com.basicInfo.service.VeterinarianService;
import com.basicInfo.vo.VeterinarianVO;
import com.entities.TSysDict;
import com.entities.TVeterinarian;
import com.utils.PageUtils;
import com.utils.R;

@Controller
@RequestMapping("/biz/veterinarian")
public class VeterinarianController {
	private static Logger logger = LoggerFactory.getLogger(VeterinarianController.class);
	
	@Autowired 
	private VeterinarianService veterinarianService;
	
	@Autowired 
	private ProtectorService protectorService;
	/**
	 * 跳转新增兽医
	 */
	@RequestMapping("/veterinarian_Add")
	@RequiresPermissions("/biz/veterinarian/veterinarian_Add")
	public String veterinarian_Add() {
		return "basicInfo/veterinarian/veterinarian_Add";
	}
	/**
	 * 新增兽医
	 */
	@RequestMapping("/addVeterinarian")
	@RequiresPermissions("/biz/veterinarian/veterinarian_Add")
	@ResponseBody
	public R addVeterinarian(TVeterinarian veterinarian) {
 		Integer	id = veterinarianService.addVeterinarian(veterinarian);
			if(id!=0){
				Map<String, Object>map=new HashMap<>();
				map.put("id", id);
				return R.ok(map);
			}
			return R.error();
		
	}
	
	/**
	 * 跳转到兽医列表页面
	 * @return
	 */
	@RequestMapping("/veterinarian_List")
	@RequiresPermissions("/biz/veterinarian/veterinarian_List")
	String Veterinarian() {
		return "basicInfo/veterinarian/veterinarian_List";
	}
	
	/**
	 * 兽医列表
	 * @param  pageNumber:页码
	 * 		   pageSize:页面大小
	 * 		   orgId:当前登录人部门id
	 */
	@ResponseBody
	@GetMapping("/findVeterinarianList")
	@RequiresPermissions("/biz/veterinarian/veterinarian_List")
	public PageUtils findVeterinarianList(Integer index,Integer pageSize,Integer orgId,String searchKey){
		PageUtils pageUtils=null;
		try {
			pageUtils=veterinarianService.findVeterinarianList(index,pageSize,orgId,searchKey);
		} catch (Exception e) {
			logger.warn("查看兽医列表失败!");
		}
		return pageUtils;
	}
	
	
	/**
	 * 兽医详情
	 * @param  id 兽医id
	 */
	@GetMapping("/findVeterinarianDetail")
	@RequiresPermissions("/biz/veterinarian/findVeterinarianDetail")
	public String findVeterinarianDetail(Integer id,Model model){
			VeterinarianVO	veterinarian=veterinarianService.findVeterinarianDetail(id);
			model.addAttribute("veterinarian", veterinarian);
		return "basicInfo/veterinarian/veterinarian_Detail";
	}
	
	
	/**
	 * 删除兽医
	 */
	@RequestMapping("/deleteVeterinarian")
	@RequiresPermissions("/biz/veterinarian/deleteVeterinarian")
	@ResponseBody
	public R deleteVeterinarian(Integer id) {
		if(veterinarianService.deleteVeterinarian(id)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 跳转到修改兽医页面
	 */
	@RequestMapping("/toUpdatePage")
	@RequiresPermissions("/biz/veterinarian/toUpdatePage")
	public String toUpdatePage(Integer id,Model model) {
		VeterinarianVO	veterinarian=veterinarianService.findVeterinarianDetail(id);
		List<TSysDict> nationList = protectorService.findTotalNation();
		for(TSysDict n:nationList) {
			if(n.getName().equals(veterinarian.getNation())) {
				n.setRemarks("checked");
			}
		}
			model.addAttribute("veterinarian", veterinarian);
			model.addAttribute("nationList", nationList);
		return "basicInfo/veterinarian/veterinarian_Update";
	}
	
	
	
	
	
	
	/**
	 * 修改兽医
	 */
	@RequestMapping("/updateVeterinarian")
	@RequiresPermissions("/biz/veterinarian/toUpdatePage")
	@ResponseBody
	public R updateVeterinarian(TVeterinarian veterinarian) {
		if(veterinarianService.updateVeterinarian(veterinarian)>0){
			return R.ok();
		}
		return R.error();
	}
}

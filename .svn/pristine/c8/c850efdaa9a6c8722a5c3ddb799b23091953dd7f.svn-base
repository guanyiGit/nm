package com.basicInfo.controller;

import java.util.List;

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

import com.basicInfo.service.CooperationService;
import com.entities.TCooperation;
import com.utils.PageUtils;
import com.utils.R;

@Controller
@RequestMapping("/biz/cooperation")
public class CooperationController {
	private static Logger logger = LoggerFactory.getLogger(CooperationController.class);
	@Autowired
	private CooperationService cooperationService;
	@GetMapping("/cooperation_List")
	@RequiresPermissions("/biz/cooperation/cooperation_List")
	String cooperationList() {
		return "basicInfo/cooperation/cooperation_List";
	}
	
	/**
	 * 跳转到新增合作社页面
	 * @return
	 */
	@RequestMapping("/cooperation_Add")
	@RequiresPermissions("/biz/cooperation/cooperation_Add")
	String cooperatio_Add() {
		return "basicInfo/cooperation/cooperation_Add";
	}
	/**
	 * 新增合作社
	 */
	@RequestMapping("/addCooperation")
	@RequiresPermissions("/biz/cooperation/cooperation_Add")
	@ResponseBody
	public R addCooperation(TCooperation cooperation) {
		if(cooperationService.addCooperation(cooperation)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 合作社列表
	 * @param  pageNumber:页码
	 * 		   pageSize:页面大小
	 * 		   orgId:当前登录人部门id
	 */
	@ResponseBody
	@GetMapping("/findCooperationList")
	@RequiresPermissions("/biz/cooperation/cooperation_List")
	public PageUtils findCooperationList(Integer index,Integer pageSize,Integer orgId,String searchKey){
		PageUtils pageUtils=null;
		try {
			pageUtils=cooperationService.findCooperationList(index,pageSize,orgId,searchKey);
		} catch (Exception e) {
			logger.warn("查看合作社列表失败!");
		}
		return pageUtils;
	}
	
	
	/**
	 * 合作社详情
	 * @param id:合作社id
	 */
	@GetMapping("/findCooperationDetail")
	@RequiresPermissions("/biz/cooperation/findCooperationDetail")
	public String findCooperationDetail(Integer id,Model model){
		TCooperation cooperation=null;
		try {
			cooperation=cooperationService.findCooperationDetail(id);
		} catch (Exception e) {
			logger.warn("查看合作社详情失败");
		}
		model.addAttribute("cooperation", cooperation);
		return "basicInfo/cooperation/cooperation_Detail";
	}
	
	
	/**
	 * 删除合作社
	 */
	@RequestMapping("/deleteCooperation")
	@RequiresPermissions("/biz/cooperation/deleteCooperation")
	@ResponseBody
	public R deleteCooperation(Integer id) {
		if(cooperationService.deleteCooperation(id)>0){
			return R.ok();
		}
		return R.error();
	}
	
	
	/**
	 * 跳转到修改合作社页面
	 * @param id  合作社id
	 * @param model
	 * @return
	 */
	@RequestMapping("/cooperation_Update")
	@RequiresPermissions("/biz/cooperation/cooperation_Update")
	public String toUpdate(Integer id,Model model) {
		TCooperation	cooperation=cooperationService.findCooperationDetail(id);
		model.addAttribute("cooperation", cooperation);
		return "basicInfo/cooperation/cooperation_Update";
	}
	
	
	
	/**
	 * 修改合作社
	 */
	@RequestMapping("/updateCooperation")
	@RequiresPermissions("/biz/cooperation/cooperation_Update")
	@ResponseBody
	public R updateCooperation(TCooperation cooperation) {
		if(cooperationService.updateCooperation(cooperation)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 根据当前登录人的区域id查找所属的合作社
	 */
	@RequestMapping("/findCooperationByAreaId")
	@ResponseBody
	public List<TCooperation> findCooperationByAreaId(Integer areaId) {
		 List<TCooperation> cooperationList=null;
		try {
			cooperationList=cooperationService.findCooperationByAreaId(areaId);
		} catch (Exception e) {
			logger.warn("查看当前区域下的所有合作社");
		}
		return cooperationList;
	}
}

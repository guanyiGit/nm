package com.basicInfo.controller;

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

import com.basicInfo.service.PadService;
import com.entities.TPadInfo;
import com.utils.PageUtils;
import com.utils.R;

@Controller
@RequestMapping("/biz/pad")
public class PadController {
	
	private static Logger logger = LoggerFactory.getLogger(PadController.class);
	@Autowired 
	private PadService padService;
	
	/**
	 * 跳转到添加pad页面
	 * @return
	 */
	@RequestMapping("/pad_Add")
	@RequiresPermissions("/biz/pad/pad_Add")
	String pad_Add() {
		
		return "basicInfo/pad/pad_Add";
	}
	/**
	 * 新增Pad
	 */
	@RequestMapping("/addPad")
	@RequiresPermissions("/biz/pad/pad_Add")
	@ResponseBody
	public R addDrug(@RequestBody TPadInfo pad) {
		if(padService.addPad(pad)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 跳转到pad列表
	 * @return
	 */
	@RequestMapping("/pad_List")
	@RequiresPermissions("/biz/pad/pad_List")
	String padList() {
		return "basicInfo/pad/pad_List";
	}
	/**
	 * Pad列表
	 * @param  pageNumber:页码
	 * 		   pageSize:页面大小
	 * 		   orgId:部门id
	 */
	@ResponseBody
	@GetMapping("/findPadList")
	@RequiresPermissions("/biz/pad/pad_List")
	public PageUtils findPadList(Integer index,Integer pageSize,Integer orgId,String searchKey){
		PageUtils pageInfo=null;
		try {
			 pageInfo=padService.findPadList(index,pageSize,orgId,searchKey);
		} catch (Exception e) {
			logger.warn("查找pad列表失败");
		}
		return pageInfo;
	}
	
	
	/**
	 * pad详情
	 * @param  id 药品id
	 */
	@ResponseBody
	@GetMapping("/findPadDetail")
	@RequiresPermissions("/biz/pad/findPadDetail")
	public TPadInfo findPadDetail(Integer id){
		
		TPadInfo pad=null;
		try {
			pad=padService.findPadDetail(id);
		} catch (Exception e) {
			logger.warn("查看pad详情失败");
		}
		return pad;
	}
	
	
	/**
	 * 删除Pad
	 */
	@RequestMapping("/deletePad")
	@RequiresPermissions("/biz/pad/deletePad")
	@ResponseBody
	public R deletePad(Integer id) {
		if(padService.deletePad(id)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 跳转到修改pad页面
	 */
	@RequestMapping("/toUpdatePage")
	@RequiresPermissions("/biz/pad/toUpdatePage")
	public String toUpdatePage(Integer id,Model model) {
	TPadInfo pad=padService.findPadDetail(id);
		model.addAttribute("pad",pad);
		return "basicInfo/pad/pad_Update";
	}
	
	/**
	 * 修改pad
	 */
	@RequestMapping("/updatePad")
	@RequiresPermissions("/biz/pad/toUpdatePage")
	@ResponseBody
	public R updatePad(TPadInfo padInfo) {
		if(padService.updatePad(padInfo)>0){
			return R.ok();
		}
		return R.error();
	}
}

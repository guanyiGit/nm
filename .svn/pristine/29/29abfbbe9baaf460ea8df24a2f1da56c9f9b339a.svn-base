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

import com.basicInfo.dao.TForageInfoMapper;
import com.basicInfo.service.ForageService;
import com.entities.TForageInfo;
import com.entities.TForageInfoExample;
import com.utils.PageUtils;
import com.utils.R;

@Controller
@RequestMapping("/biz/forage")
public class ForageController {

private static Logger logger = LoggerFactory.getLogger(ForageController.class);
	
	@Autowired 
	private ForageService forageService;
	@Autowired
	private TForageInfoMapper forageMapper;
	/**
	 * 跳转到添加饲料页面
	 * @return
	 */
	@RequestMapping("/forage_Add")
	@RequiresPermissions("/biz/forage/forage_Add")
	String forage_Add() {
		
		return "basicInfo/forage/forage_Add";
	}
	
	/**
	 * 新增饲草
	 */
	@RequestMapping("/addForage")
	@RequiresPermissions("/biz/forage/forage_Add")
	@ResponseBody
	public R addForage(@RequestBody TForageInfo forage) {
		if(forageService.addForage(forage)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 跳转到饲料列表
	 * @return
	 */
	@RequestMapping("/forage_List")
	@RequiresPermissions("/biz/forage/forage_List")
	String forageList() {
		return "basicInfo/forage/forage_List";
	}
	/**
	 * 饲草列表
	 * @param  pageNumber:页码
	 * 		   pageSize:页面大小
	 * 		   orgId:当前登录人部门id
	 */
	@ResponseBody
	@GetMapping("/findForageList")
	@RequiresPermissions("/biz/forage/forage_List")
	public PageUtils findForageList(Integer index,Integer pageSize,String searchKey){
		PageUtils pageUtils=null;
		try {
			pageUtils=forageService.findForageList(index,pageSize,searchKey);
		} catch (Exception e) {
			logger.warn("查看饲草列表失败!");
		}
		return pageUtils;
	}
	
	
	/**
	 * 饲草详情
	 * @param  id 兽医id
	 */
//	@GetMapping("/findForageDetail")
//	@RequiresPermissions("/biz/forage/findForageDetail")
//	public String findForageDetail(Integer id,Model model){
//		TForageInfo	forage=forageService.findForageDetail(id);
//			model.addAttribute("forage", forage);
//		return "/basicInfo/forage/forage_Detail";
//	}
	
	
	/**
	 * 删除饲草
	 */
	@RequestMapping("/deleteForage")
	@RequiresPermissions("/biz/forage/deleteForage")
	@ResponseBody
	public R deleteVeterinarian(Integer id) {
		if(forageService.deleteForage(id)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 跳转到修改饲草页面
	 */
	@RequestMapping("/toUpdateForage")
	@RequiresPermissions("/biz/forage/toUpdateForage")
	public String toUpdateForage(Integer id,Model model) {
		//查看饲草详情 
		TForageInfo forage = forageService.findForageDetail(id);
		model.addAttribute("forage", forage);
		return "basicInfo/forage/forage_Update";
	}
	
	/**
	 * 修改饲草
	 */
	@RequestMapping("/updateForage")
	@RequiresPermissions("/biz/forage/toUpdateForage")
	@ResponseBody
	public R updateForage(TForageInfo forageInfo) {
		if(forageService.updateForage(forageInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	
	
	/**
	 * 查看所有饲草
	 */
	@RequestMapping("/findTotalForage")
	@ResponseBody
	public List<TForageInfo> findTotalForage() {
		List<TForageInfo>list=null;
		try {
			TForageInfoExample example=new TForageInfoExample();
			list=forageMapper.selectByExample(example);
		} catch (Exception e) {
			logger.warn("查看所有饲草失败!");
		}
		
		return list;
	}
}

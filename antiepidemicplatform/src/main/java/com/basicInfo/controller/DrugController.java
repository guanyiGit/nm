package com.basicInfo.controller;

import com.basicInfo.service.DrugService;
import com.entities.TDrugInfo;
import com.utils.PageUtils;
import com.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/biz/drug")
public class DrugController {

private static Logger logger = LoggerFactory.getLogger(DrugController.class);
	
	@Autowired 
	private DrugService drugService;
	
	/**
	 * 跳转到防疫药品页面
	 * @return
	 */
	@GetMapping("/antiepidemic_Drug_List")
	@RequiresPermissions("/biz/drug/findDrugList")
	String antiepidemic() {
		return "basicInfo/drug/antiepidemic_Drug_List";
	}
	
	/**
	 * 跳转到治疗药品页面
	 * @return
	 */
	@GetMapping("/therapeutical_Drug_List")
	@RequiresPermissions("/biz/drug/findDrugList")
	String therapeutical() {
		return "basicInfo/drug/therapeutical_Drug_List";
	}
	
	/**
	 * 跳转到添加防疫药品页面
	 * @return
	 */
	@RequestMapping("/antiepidemic_Drug_Add")
	@RequiresPermissions("/biz/drug/addDrug")
	String forage_Add() {
		
		return "basicInfo/drug/antiepidemic_Drug_Add";
	}
	
	
	/**
	 * 跳转到添加治疗药品页面
	 * @return
	 */
	@RequestMapping("/therapeutical_Drug_Add")
	@RequiresPermissions("/biz/drug/addDrug")
	String therapeutical_Drug_Add() {
		return "basicInfo/drug/therapeutical_Drug_Add";
	}
	
	/**
	 * 新增Drug
	 */
	@RequestMapping("/addDrug")
	@RequiresPermissions("/biz/drug/addDrug")
	@ResponseBody
	public R addDrug(@RequestBody TDrugInfo drug) {
		if(drugService.addDrug(drug)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 药品列表
	 * @param  index:起始索引
	 * 		   pageSize:页面大小
	 * 		   type:药品类型
	 */
	@ResponseBody
	@GetMapping("/findDrugList")
	@RequiresPermissions("/biz/drug/findDrugList")
	public PageUtils findDrugList(Integer index,Integer pageSize,Integer type,String searchKey){
		PageUtils pageUtils=null;
		try {
			pageUtils=drugService.findDrugList(index,pageSize,type,searchKey);
		} catch (Exception e) {
			logger.warn("查看药品列表失败!");
		}
		return pageUtils;
	}
	
	
	/**
	 * 药品详情
	 * @param  id 药品id
	 */
	@ResponseBody
	@GetMapping("/findDrugDetail")
	@RequiresPermissions("/biz/drug/findDrugDetail")
	public TDrugInfo findDrugDetail(Integer id){
		TDrugInfo drug=null;
		try {
			drug=drugService.findDrugDetail(id);
		} catch (Exception e) {
			logger.warn("查看药品详情失败");
		}
		return drug;
	}
	
	
	/**
	 * 删除药品
	 */
	@RequestMapping("/deleteDrug")
	@RequiresPermissions("/biz/drug/deleteDrug")
	@ResponseBody
	public R deleteDrug(Integer id) {
		if(drugService.deleteDrug(id)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 跳转到修改药品页面
	 */
	@RequestMapping("/toUpdatePage")
	@RequiresPermissions("/biz/drug/toUpdatePage")
	public String updateDrug(Integer id,Model model) {
		TDrugInfo	drug=drugService.findDrugDetail(id);
			model.addAttribute("drug",drug);
			if(drug.getType()==0) {
				return "basicInfo/drug/antiepidemic_Drug_Update";
			}
			else {
				return "basicInfo/drug/therapeutical_Drug_Update";
			}
		
	}

	
	/**
	 * 修改药品
	 */
	@RequestMapping("/updateDrug")
	@RequiresPermissions("/biz/drug/toUpdatePage")
	@ResponseBody
	public R updateForage(TDrugInfo drugInfo) {
		if(drugService.updateDrug(drugInfo)>0){
			String type = drugInfo.getType().toString();
			return R.ok(type);
		}
		return R.error();
	}

	/**
	 * 初始话药品下拉框
	 */
	@ResponseBody
	@GetMapping("/initSelect")
	public List<TDrugInfo> initDrugSelect(@RequestParam HashMap<String, Object> params, Model model, HttpServletRequest request){
		List<TDrugInfo> drugs = drugService.initDrugSelect(params);
		return drugs;
	}
}

package com.basicInfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.basicInfo.dao.TForageInfoMapper;
import com.basicInfo.dao.TPastoralCommitteeMapper;
import com.basicInfo.service.CooperationService;
import com.basicInfo.service.PastoralCommitteeService;
import com.basicInfo.vo.PastoralVO;
import com.entities.TCooperation;
import com.entities.TForageInfo;
import com.entities.TForageInfoExample;
import com.entities.TPastoralCommittee;
import com.entities.TPastoralCommitteeExample;
import com.entities.TPastoralCommitteeExample.Criteria;
import com.epmanagement.service.ManureDisposalService;
import com.orgmanagement.domain.UserDO;
import com.sys.controller.BaseController;
import com.utils.FastDfsUtils;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.UploadResult;

@Controller
@RequestMapping("/biz/pastoralCommittee")
public class PastoralCommitteeController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(PastoralCommitteeController.class);
	@Autowired
	private PastoralCommitteeService pasoralCommitteeService;
	
	/**
	 * 跳转到牧委会列表页面
	 * @return
	 */
	@GetMapping("/pastoral_List")
	@RequiresPermissions("/biz/pastoralCommittee/pastoral_List")
	String pastoralList() {
		return "basicInfo/pastoral/pastoral_List";
	}
	
	
	/**
	 * 跳转到新增牧委会页面
	 * @return
	 */
	@GetMapping("/pastoral_Add")
	@RequiresPermissions("/biz/pastoralCommittee/pastoral_Add")
	String pastoral_Add() {
		return "basicInfo/pastoral/pastoral_Add";
	}
	/**
	 * 新增牧委会
	 */
	@RequestMapping("/addPastoralCommittee")
	@RequiresPermissions("/biz/pastoralCommittee/pastoral_Add")
	@ResponseBody
	public R addPastoralCommittee(TPastoralCommittee committee) {
		int id=pasoralCommitteeService.addPastoralCommittee(committee);
		if(id>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 牧委会列表
	 * @param  index:索引
	 * 		   pageSize:页面大小
	 * 		   orgId:当前登录人部门id
	 */
	@ResponseBody
	@GetMapping("/findPasoralCommitteeList")
	@RequiresPermissions("/biz/pastoralCommittee/pastoral_List")
	public PageUtils findPastoralCommitteeList(Integer index,Integer pageSize,String searchKey){
		PageUtils pageUtils=null;
		try {
			 pageUtils=pasoralCommitteeService.findPastoralCommitteeList(index,pageSize,searchKey);
		} catch (Exception e) {
			logger.warn("牧委会列表失败");
		}
		
		return pageUtils;
	}
	
	
	/**
	 * 牧委会详情
	 */
	@GetMapping("/findPastoralCommitteeDetail")
	@RequiresPermissions("/biz/pastoralCommittee/findPastoralCommitteeDetail")
	public String findPastoralCommitteeDetail(Integer id,Model model){
		TPastoralCommittee pastoral=pasoralCommitteeService.findPastoralCommitteeDetail(id);
			model.addAttribute("pastoral", pastoral);
		return "basicInfo/pastoral/pastoral_Detail";
	}
	
	
	/**
	 * 删除牧委会
	 */
	@RequestMapping("/deletePastoralCommittee")
	@RequiresPermissions("/biz/pastoralCommittee/deletePastoralCommittee")
	@ResponseBody
	public R deletePastoralCommittee(Integer id) {
		if(pasoralCommitteeService.deletePastoralCommittee(id)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 跳转到修改牧委会页面
	 * @param id  牧委会id
	 * @param model
	 * @return
	 */
	@RequestMapping("/pastoral_Update")
	@RequiresPermissions("/biz/pastoral/pastoral_Update")
	public String toUpdate(Integer id,Model model) {
		TPastoralCommittee	pastoral=pasoralCommitteeService.findPastoralCommitteeDetail(id);
		model.addAttribute("pastoral", pastoral);
		return "basicInfo/pastoral/pastoral_Update";
	}
	
	/**
	 * 修改牧委会
	 */
	@RequestMapping("/updatePastoralCommittee")
	@RequiresPermissions("/biz/pastoral/pastoral_Update")
	@ResponseBody
	public R updateCooperation(TPastoralCommittee committee) {
		if(pasoralCommitteeService.updatePastoralCommittee(committee)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 根据区域id查找下属牧委会
	 */
	@RequestMapping("/findPastoralByAreaId")
	@ResponseBody
	public List<TPastoralCommittee> findPastoralByAreaId() {
		
		List<TPastoralCommittee> list=pasoralCommitteeService.findPastoralByAreaId();
		try {
			list=pasoralCommitteeService.findPastoralByAreaId();
		} catch (Exception e) {
			logger.warn("根据区域id查找下属牧委会失败");
		}
		return list;
	}
	
}

package com.goodsmanagement.controller;

import com.entities.GoodsInfoDO;
import com.entities.OrgInfo;
import com.entities.Tree;
import com.epmanagement.service.ManureDisposalService;
import com.goodsmanagement.service.GoodsInfoService;
import com.goodsmanagement.vo.GoodsInfoVO;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.OrgInfoService;
import com.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-05 15:17:41
 */
 
@Controller
@RequestMapping("/biz/goodsInfo")
public class GoodsInfoController {
	@Autowired
	private GoodsInfoService goodsInfoService;
	@Autowired
	private OrgInfoService orgInfoService;
	@Autowired
	private ManureDisposalService manureDisposalService;
	private static final Logger logger = LoggerFactory.getLogger(GoodsInfoController.class);
	
	@GetMapping()
//	@RequiresPermissions("system:goodsInfo:goodsInfo")
	String GoodsInfo(Model model){
		model.addAttribute("user",ShiroUtils.getUser());
	    return "goodsmanagement/goodsDistribution";
	}

	@RequestMapping("/redirect2Add")
	String redirect2Add() {
		int roleType = ShiroUtils.getUser().getRoles().get(0).getType();
		if(roleType == 3 || roleType ==8) {
			//乡级管理员
			return "goodsmanagement/goodsDistributionAdd1";
		}else if(roleType == 5 || roleType == 6 || roleType == 9 || roleType == 10){
			//县级、州级管理员
			return "goodsmanagement/goodsDistributionAdd2";
		}else {
			//不合法的角色
			logger.error("GoodsInfoController.redirect2Add|用户角色不合法！ roleType = {}",roleType);
			return "";
		}
	}

	@GetMapping("/detail/{id}")
	String redirect2Detail(@PathVariable("id") Integer id,Model model) {
		GoodsInfoVO goodsInfoVO = goodsInfoService.get(id);
		model.addAttribute("goodsInfoVO",goodsInfoVO);
		//根据接收组织是否为空判断跳转到哪个详情页
		Integer receiveOrg = goodsInfoVO.getReceiveOrg();
		if(receiveOrg != null) {
			//跳转到州县添加的物资发放详情
			return "goodsmanagement/goodsDistributionDetail2";
		}else {
			//跳转到乡添加的物资发放详情
			return "goodsmanagement/goodsDistributionDetail1";
		}
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("system:goodsInfo:goodsInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		Object oj = params.get("orgId");
		Long deptId = ShiroUtils.getUser().getDeptId();
		Long orgId = null;
		if(oj != null && oj.toString().trim() != "") {
			orgId = Long.parseLong(oj.toString());
		}
		List<Integer> deptIdList = new ArrayList<>();
		if(orgId == null || orgId == 9999) {
			//查询当前组织下全部组织的物资发放记录
			List<OrgInfo> orgInfos = orgInfoService.orgList();
			deptIdList = orgtills.getDeptsById(orgInfos, deptId.intValue(), new ArrayList<>()).stream().map(org -> org.getId()).collect(Collectors.toList());
		}else {
			//指定查询的组织
			deptIdList.add(orgId.intValue());
		}
		params.put("deptIdList",deptIdList);
		Query query = new Query(params);
		List<GoodsInfoVO> goodsInfoList = goodsInfoService.list(query);
		//用于前端判断“修改”“删除”权限
		if(goodsInfoList != null && goodsInfoList.size() > 0) {
			Integer userId = ShiroUtils.getUserId().intValue();
			for (GoodsInfoVO goodsInfoVO:
				 goodsInfoList) {
				goodsInfoVO.setUserId(userId);
			}
		}
		int total = goodsInfoService.count(query);
		PageUtils pageUtils = new PageUtils(goodsInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("system:goodsInfo:add")
	String add(){
	    return "system/goodsInfo/add";
	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("system:goodsInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		GoodsInfoVO goodsInfo = goodsInfoService.get(id);
        String goodsType = goodsInfo.getGoodsType();
        model.addAttribute("goodsInfo", goodsInfo);
//		Map<String,Object> map = new HashMap<>();
//		map.put("type","goods_type");
//        List<SelectVO> selectVOS = manureDisposalService.initSelData(map);
//        if(selectVOS != null && selectVOS.size() > 0) {
//            for (SelectVO selectVO: selectVOS) {
//                String value = selectVO.getValue();
//                if(value.equals(goodsType)) {
//                    selectVO.setIsCheck("");
//                }
//            }
//        }
        int roleType = ShiroUtils.getUser().getRoles().get(0).getType();
		if(roleType == 3 || roleType == 8) {	//乡级管理员
			return "goodsmanagement/goodsDistributionEdit1";
		}else if(roleType == 5 || roleType == 6 || roleType == 9 || roleType == 10){
			return "goodsmanagement/goodsDistributionEdit2";
		}else {
			logger.error("GoodsInfoController.edit|角色不合法！ roleType = {}",roleType);
			return "";
		}
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("system:goodsInfo:add")
	public R save( GoodsInfoDO goodsInfo){
		goodsInfo.setDistributeDate(new Date());
		goodsInfo.setCreateDate(new Date());
		goodsInfo.setOperatorId(ShiroUtils.getUserId().intValue());
		goodsInfo.setDistributorOrgId(ShiroUtils.getUser().getDeptId().intValue());
		if(goodsInfoService.save(goodsInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("system:goodsInfo:edit")
	public R update(GoodsInfoDO goodsInfo){
		goodsInfoService.update(goodsInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("system:goodsInfo:remove")
	public R remove( Integer id){
		if(goodsInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("system:goodsInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		goodsInfoService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping("/initOrgSelect")
	@ResponseBody
	public List<Tree<OrgInfo>> initOrgSelect (@RequestParam Map<String, Object> params) {
		UserDO user = ShiroUtils.getUser();
		params.put("orgId",user.getDeptId());
		params.put("deptPid",user.getPid());
		return goodsInfoService.initOrgSelect(params);
	}

	@GetMapping("/initOrgSelectWx")
	@ResponseBody
	public List<OrgInfo> initOrgSelectWx(@RequestParam Map<String,Object> params) {
		UserDO user = ShiroUtils.getUser();
		params.put("orgId",user.getDeptId());
		return goodsInfoService.initOrgSelect2(params);
	}
}

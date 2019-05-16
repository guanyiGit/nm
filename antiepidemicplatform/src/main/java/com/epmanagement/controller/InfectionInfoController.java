package com.epmanagement.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.dogmanager.utils.DogResult;
import com.entities.TInfectionInfo;
import com.epmanagement.service.InfectionInfoService;
import com.sys.controller.BaseController;
import com.utils.PageUtils;
import com.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epmanagement.dao.TInfectionInfoMapper;
import com.epmanagement.vo.InfectionInfoVO;
import com.epmanagement.vo.MediaUrl;
/**
 * 牛羊病变及脏器感染处理
 * @author ZhongZhong
 * 
 */
@Controller
@RequestMapping("/biz/infectionInfo")
public class InfectionInfoController extends BaseController {
	
	
	private static Logger logger = LoggerFactory.getLogger(InfectionInfoController.class);
	@Autowired
	private InfectionInfoService infectionInfoService;
	@Autowired
	private TInfectionInfoMapper infectionMapper;
	
	/**
	 * 跳转到牛羊感染病变列表
	 * @return
	 */
	@GetMapping("/toInfectionInfo_List")
	@RequiresPermissions("/biz/infectionInfo/toInfectionInfo_List")
	String infectionInfoList(Model model){
		model.addAttribute("user", getUser());
	    return "epmanagement/infectionInfo/InfectionInfo_List";
	}
	
	/**
	 * 牛羊感染病变列表
	 * @param index 索引
	 * @param pageSize 页面大小
	 * @param orgId1 州级管理员从下拉框中选择的组织id 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	@ResponseBody
	@GetMapping("/findInfectionInfoList")
	@RequiresPermissions("/biz/infectionInfo/toInfectionInfo_List")
	public PageUtils findInfectionInfoList(Integer index,Integer pageSize,Integer orgId1,
											Date startDate,Date endDate){
		PageUtils pageUtils = null;
		try {
			pageUtils=infectionInfoService.findInfectionInfoList(index,pageSize,orgId1,startDate,endDate);
		} catch (Exception e) {
			logger.warn("查找牛羊感染病变列表失败!");
		}
		return pageUtils;
	}
	
	
	/**
	 * 跳转到修改牛羊病变页面
	 * @return
	 */
	@GetMapping("/toInfectionInfo_Update")
	@RequiresPermissions("/biz/infectionInfo/toInfectionInfo_Update")
	String toInfectionInfo_Update(Integer id,Model model){
		InfectionInfoVO detail = infectionMapper.getInfectionInfoDetail(id);
			model.addAttribute("detail", detail);
	    return "epmanagement/infectionInfo/InfectionInfo_Update";
	}
	
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("/biz/infectionInfo/toInfectionInfo_Update")
	public R update(TInfectionInfo tInfectionInfo){
		if(infectionInfoService.update(tInfectionInfo)>0) {
			return R.ok();
		}
		return R.error();
	}
	
	
	/**
	 * 跳转到新增牛羊免疫页面
	 * @return
	 */
	@GetMapping("/toInfectionInfo_Add")
	@RequiresPermissions("/biz/infectionInfo/toInfectionInfo_Add")
	String toInfectionInfo_Add(){
	    return "epmanagement/infectionInfo/InfectionInfo_Add";
	}
	
	
	/**
	 * 新增
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("/biz/infectionInfo/toInfectionInfo_Add")
	public R save(TInfectionInfo tInfectionInfo){
		int id=infectionInfoService.save(tInfectionInfo);
		if(id>0) {
			Map<String, Object>map=new HashMap<>();
				map.put("id", id);
				return R.ok(map);
		}
		return R.error();
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@RequiresPermissions("/biz/infectionInfo/delete")
	public R remove(Integer id){
		int a=infectionInfoService.delete(id);
		if(a>0) {
			return R.ok();
		}
		return R.error();
	}
	
	
	/**
	 * 查找牛羊抗体详情
	 * @param id 主键id
	 * @return
	 */
	@GetMapping("/getInfectionInfoDetail")
	@RequiresPermissions("/biz/infectionInfo/getInfectionInfoDetail")
	public String getInfectionInfoDetail(Integer id,Model model) {
		InfectionInfoVO detail = infectionMapper.getInfectionInfoDetail(id);
		model.addAttribute("detail", detail);
		
		List<MediaUrl> media = infectionMapper.getMedia(id);
		List<MediaUrl> imageList =new ArrayList<>();
		List<MediaUrl> videoList =new ArrayList<>();
		
		for(MediaUrl m:media) {
			MediaUrl mediaUrl1=new MediaUrl();
			MediaUrl mediaUrl2=new MediaUrl();
			//照片
			if(m.getIsVideo()==0) {
				mediaUrl1.setThumbnailUrl(m.getThumbnailUrl());
				mediaUrl1.setUrl(m.getUrl());
				imageList.add(mediaUrl1);
			}else {
				mediaUrl2.setUrl(m.getUrl());
				videoList.add(mediaUrl2);
			}
		}
		model.addAttribute("imageList", imageList);
		model.addAttribute("videoList", videoList);
		return "epmanagement/infectionInfo/InfectionInfo_Detail";
	}


	/**
	 * WX查找牛羊抗体详情
	 * @param id 主键id
	 * @return
	 */
	@GetMapping("/getInfectionInfoDetail2")
	@ResponseBody
	public DogResult getInfectionInfoDetail2(Integer id) {
        HashMap<Object, Object> map = new HashMap<>();
        InfectionInfoVO detail = infectionMapper.getInfectionInfoDetail(id);
        map.put("detail", detail);

		List<MediaUrl> media = infectionMapper.getMedia(id);
		List<MediaUrl> imageList =new ArrayList<>();
		List<MediaUrl> videoList =new ArrayList<>();
		
		for(MediaUrl m:media) {
			MediaUrl mediaUrl1=new MediaUrl();
			MediaUrl mediaUrl2=new MediaUrl();
			//照片
			if(m.getIsVideo()==0) {
				mediaUrl1.setThumbnailUrl(m.getThumbnailUrl());
				mediaUrl1.setUrl(m.getUrl());
				imageList.add(mediaUrl1);
			}else {
				mediaUrl2.setUrl(m.getUrl());
				videoList.add(mediaUrl2);
			}
		}
        map.put("imageList", imageList);
        map.put("videoList", videoList);
		return DogResult.ok(map);
	}
}

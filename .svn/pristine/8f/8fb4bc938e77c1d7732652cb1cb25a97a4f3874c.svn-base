package com.epmanagement.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dogmanager.utils.DogResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entities.TAntibodyDetection;
import com.epmanagement.dao.TAntibodyDetectionMapper;
import com.epmanagement.service.AntibodyDetectionService;
import com.epmanagement.vo.AntibodyDetectionVO;
import com.epmanagement.vo.DogAnatomyVO;
import com.epmanagement.vo.MediaUrl;
import com.sys.controller.BaseController;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 牛羊免疫抗体检测
 * @author ZhongZhong
 */
@Controller
@RequestMapping("/biz/antibodyDetection")
public class AntibodyDetectionController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(AntibodyDetectionController.class);
	@Autowired
	private AntibodyDetectionService antibodyDetectionService;
	
	@Autowired
	private TAntibodyDetectionMapper antiMapper;
	/**
	 * 跳转到牛羊免疫抗体列表
	 * @return
	 */
	@GetMapping("/toAntibodyDetection_List")
	@RequiresPermissions("/biz/antibodyDetection/toAntibodyDetection_List")
	String dogAnatomy(Model model){
		model.addAttribute("user", getUser());
	    return "epmanagement/antibodyDetection/AntibodyDetection_List";
	}
	
	/**
	 * 牛羊免疫抗体列表
	 * @param index 索引
	 * @param pageSize 页面大小
	 * @param orgId1 州级管理员从下拉框中选择的组织id 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	@ResponseBody
	@GetMapping("/findAntibodyDetectionList")
	@RequiresPermissions("/biz/antibodyDetection/toAntibodyDetection_List")
	public PageUtils findAntibodyDetectionList(Integer index,Integer pageSize,Integer orgId1,
											Date startDate,Date endDate){
		PageUtils pageUtils = null;
		try {
			pageUtils=antibodyDetectionService.findAntibodyDetectionList(index,pageSize,orgId1,startDate,endDate);
		} catch (Exception e) {
			logger.warn("查找犬只解剖列表失败!");
		}
		return pageUtils;
	}
	
	
	/**
	 * 跳转到修改牛羊免疫页面
	 * @return
	 */
	@GetMapping("/edit")
	@RequiresPermissions("/biz/antibodyDetection/toAntibodyDetection_Update")
	String antibodyDetection(Integer id,Model model){
		AntibodyDetectionVO detail = antiMapper.getAntibodyDetectionDetail(id);
			model.addAttribute("detail", detail);
	    return "epmanagement/antibodyDetection/AntibodyDetection_Update";
	}
	
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("/biz/antibodyDetection/toAntibodyDetection_Update")
	public R update(TAntibodyDetection antibodyDetection){
		if(antibodyDetectionService.update(antibodyDetection)>0) {
			return R.ok();
		}
		return R.error();
	}
	
	
	/**
	 * 跳转到新增牛羊免疫页面
	 * @return
	 */
	@GetMapping("/toAntibodyDetection_Add")
	@RequiresPermissions("/biz/antibodyDetection/toAntibodyDetection_Add")
	String toAntibodyDetection_Add(){
	    return "epmanagement/antibodyDetection/AntibodyDetection_Add";
	}
	
	
	/**
	 * 新增
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("/biz/antibodyDetection/toAntibodyDetection_Add")
	public R save(TAntibodyDetection antibodyDetection){
		int id=antibodyDetectionService.save(antibodyDetection);
		if(id>0){
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
	@RequiresPermissions("/biz/antibodyDetection/delete")
	public R remove(Integer id){
		if(antibodyDetectionService.delete(id)>0) {
			return R.ok();
		}
		return R.error();
		
	}
	
	
	/**
	 * 查找牛羊抗体详情
	 * @param id 主键id
	 * @return
	 */
	@GetMapping("/getAntibodyDetectionDetail")
	@RequiresPermissions("/biz/antibodyDetection/getAntibodyDetectionDetail")
	public String getAntibodyDetectionDetail(Integer id,Model model) {
		AntibodyDetectionVO detail = antiMapper.getAntibodyDetectionDetail(id);
		model.addAttribute("detail", detail);
		
		List<MediaUrl> media = antiMapper.getMedia(id);
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
		return "epmanagement/antibodyDetection/AntibodyDetection_Detail";
	}



	/**
	 * wx查找牛羊抗体详情
	 * @param id 主键id
	 * @return
	 */
	@GetMapping("/getAntibodyDetectionDetail2")
	@ResponseBody
	public DogResult getAntibodyDetectionDetail2(Integer id) {
        HashMap<Object, Object> map = new HashMap<>();
        AntibodyDetectionVO detail = antiMapper.getAntibodyDetectionDetail(id);
        map.put("detail", detail);
		List<MediaUrl> media = antiMapper.getMedia(id);
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

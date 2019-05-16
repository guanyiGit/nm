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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entities.CorpseDisposal;
import com.entities.OrgInfo;
import com.entities.TManureAntigen;
import com.epmanagement.dao.TManureAntigenMapper;
import com.epmanagement.service.ManureAntigenService;
import com.epmanagement.vo.ManureAntigenVO;
import com.epmanagement.vo.MediaUrl;
import com.sys.controller.BaseController;
import com.utils.PageUtils;
import com.utils.R;


/**
 * 
 * @description 犬粪抗原检测
 * @author ZhongZhong
 * @date 2018-09-28 09:52:35
 */
 
@Controller
@RequestMapping("/biz/manureAntigen")
public class ManureAntigenController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(ManureAntigenController.class);
	@Autowired
	private ManureAntigenService manureAntigenService;
	@Autowired
	private TManureAntigenMapper manureAntigenMapper;
	
	
	/**
	 * 查看本州和下属县级组织
	 */
	@RequestMapping("/findOrgList")
	@ResponseBody
	//@RequiresPermissions("/biz/manureAntigen/selectOrg")
	public List<OrgInfo> findOrgList(){
		List<OrgInfo> orgList=null;
		try {
			orgList=manureAntigenService.findOrgList();
		} catch (Exception e) {
		logger.warn("初始化组织失败!");
		}
		return orgList;
	}

	/**
	 * 跳转到新增犬粪抗原检测界面
	 * @return
	 */
	@GetMapping("/add")
	@RequiresPermissions("/biz/manureAntigen/add")
	String add(){
	    return "epmanagement/manureAntigen/ManureAntigen_Add";
	}

	
	/**
	 * 新增犬粪抗原并返回自增id
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("/biz/manureAntigen/add")
	public R save(TManureAntigen manureAntigen){
		Integer id = manureAntigenService.save(manureAntigen);
		if(id!=0){
			Map<String, Object>map=new HashMap<>();
				map.put("id", id);
			return R.ok(map);
		}
		return R.error();
	}
	/**
	 * 跳转到犬粪抗原列表
	 * @return
	 */
	@GetMapping("/toManureAntigen_List")
	@RequiresPermissions("/biz/manureAntigen/toManureAntigen_List")
	String manureAntigen(Model model){
		model.addAttribute("user", getUser());
	    return "epmanagement/manureAntigen/ManureAntigen_List";
	}
	
	/**
	 * 查找犬粪抗原列表
	 * @param index 索引
	 * @param pageSize 页面大小
	 * @param orgId1 州级管理员从下拉框中选择的组织id 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	@ResponseBody
	@GetMapping("/findManureAntigenList")
	@RequiresPermissions("/biz/manureAntigen/toManureAntigen_List")
	public PageUtils findManureAntigenList(Integer index,Integer pageSize,Integer orgId1,
											Date startDate,Date endDate){
		PageUtils pageUtils = null;
		try {
			pageUtils=manureAntigenService.findManureAntigenList(index,pageSize,orgId1,startDate,endDate);
		} catch (Exception e) {
			logger.warn("查找犬粪抗原列表失败!");
		}
		return pageUtils;
	}
	
	
	
	
	/**
	 * 查找犬粪抗原详情
	 * @param id 主键id
	 * @return
	 */
	@GetMapping("/getManureAntigenDetail")
	@RequiresPermissions("/biz/manureAntigen/getManureAntigenDetail")
	public String getManureAntigenDetail(Integer id,Model model) {
		ManureAntigenVO detail = manureAntigenMapper.getManureAntigenDetail(id);
			model.addAttribute("detail", detail);
			
			List<MediaUrl> media = manureAntigenMapper.getMedia(id);
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
		return "epmanagement/manureAntigen/ManureAntigen_Detail";
	}


	/**
	 * wx查找犬粪抗原详情
	 * @param id 主键id
	 * @return
	 */
	@GetMapping("/getManureAntigenDetail2")
    @ResponseBody
	public DogResult getManureAntigenDetail2(Integer id) {
        HashMap<Object, Object> map = new HashMap<>();
        ManureAntigenVO detail = manureAntigenMapper.getManureAntigenDetail(id);
        map.put("detail", detail);

		List<MediaUrl> media = manureAntigenMapper.getMedia(id);
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
	
	/**
	 * 跳转到修改犬粪抗原页面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/edit")
	@RequiresPermissions("/biz/manureAntigen/edit")
	String edit(Integer id,Model model){
		ManureAntigenVO detail = manureAntigenMapper.getManureAntigenDetail(id);
		model.addAttribute("detail", detail);
			//跳转到修改尸体页面
		return "epmanagement/manureAntigen/ManureAntigen_Update";
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("/biz/manureAntigen/edit")
	public R update(TManureAntigen manureAntigen){
		if(manureAntigenService.update(manureAntigen)>0){
			return R.ok();
			}
			return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("/biz/manureAntigen/remove")
	public R remove(Integer id){
		if(manureAntigenService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	
	
}

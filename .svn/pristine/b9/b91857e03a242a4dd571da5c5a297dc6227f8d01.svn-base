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

import com.entities.TDogAnatomy;
import com.entities.TManureAntigen;
import com.epmanagement.dao.CorpseDisposalDao;
import com.epmanagement.dao.TDogAnatomyMapper;
import com.epmanagement.service.DogAnatomyService;
import com.epmanagement.service.ManureDisposalService;
import com.epmanagement.vo.DogAnatomyVO;
import com.epmanagement.vo.ManureAntigenVO;
import com.epmanagement.vo.MediaUrl;
import com.sys.controller.BaseController;
import com.utils.PageUtils;
import com.utils.R;


/**
 * 
 * @description 犬只解剖
 * @author ZhongZhong
 * @date 2018-09-28 09:52:35
 */
 
@Controller
@RequestMapping("/biz/dogAnatomy")
public class DogAnatomyController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(DogAnatomyController.class);
	@Autowired
	private DogAnatomyService dogAnatomyService;
	@Autowired
	private TDogAnatomyMapper dogAnatomyMapper;
	
	
	
	
	
	/**
	 * 跳转到犬只解剖列表
	 * @return
	 */
	@GetMapping("/toDogAnatomy_List")
	@RequiresPermissions("/biz/dogAnatomy/toDogAnatomy_List")
	String dogAnatomy(Model model){
			model.addAttribute("user", getUser());
	    return "epmanagement/dogAnatomy/DogAnatomy_List";
	}
	
	/**
	 * 查找犬只解剖列表
	 * @param index 索引
	 * @param pageSize 页面大小
	 * @param orgId1 州级管理员从下拉框中选择的组织id 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	@ResponseBody
	@GetMapping("/findDogAnatomyList")
	@RequiresPermissions("/biz/dogAnatomy/toDogAnatomy_List")
	public PageUtils findDogAnatomyList(Integer index,Integer pageSize,Integer orgId1,
											Date startDate,Date endDate){
		PageUtils pageUtils = null;
		try {
			pageUtils=dogAnatomyService.findDogAnatomyList(index,pageSize,orgId1,startDate,endDate);
		} catch (Exception e) {
			logger.warn("查找犬只解剖列表失败!");
		}
		return pageUtils;
	}
	
	
	/**
	 * 跳转到新增犬只解剖界面
	 * @return
	 */
	@GetMapping("/add")
	@RequiresPermissions("/biz/dogAnatomy/add")
	String add(){
	    return "epmanagement/dogAnatomy/DogAnatomy_Add";
	}

	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("/biz/dogAnatomy/add")
	public R save(TDogAnatomy dogAnatomy){
		Integer id = dogAnatomyService.save(dogAnatomy);
		if(id>0){
			Map<String, Object>map=new HashMap<>();
				map.put("id", id);
			return R.ok(map);
		}
		return R.error();
	}
	
	
	/**
	 * 查找犬只解剖详情
	 * @param id 主键id
	 * @return
	 */
	@GetMapping("/getDogAnatomyDetail")
	@RequiresPermissions("/biz/dogAnatomy/getDogAnatomyDetail")
	public String getDogAnatomyDetail(Integer id,Model model) {
		DogAnatomyVO detail = dogAnatomyMapper.getDogAnatomyDetail(id);
			model.addAttribute("detail", detail);
			
			List<MediaUrl> media = dogAnatomyMapper.getMedia(id);
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
		return "epmanagement/dogAnatomy/DogAnatomy_Detail";
	}

	/**
	 * wx查找犬只解剖详情
	 * @param id 主键id
	 * @return
	 */
	@GetMapping("/getDogAnatomyDetail2")
	@ResponseBody
	public DogResult getDogAnatomyDetail2(Integer id) {
        HashMap<Object, Object> map = new HashMap<>();
        DogAnatomyVO detail = dogAnatomyMapper.getDogAnatomyDetail(id);
        map.put("detail", detail);

		List<MediaUrl> media = dogAnatomyMapper.getMedia(id);
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
	@RequiresPermissions("/biz/dogAnatomy/edit")
	String edit(Integer id,Model model){
		DogAnatomyVO detail = dogAnatomyMapper.getDogAnatomyDetail(id);
		model.addAttribute("detail", detail);
			//跳转到修改页面
		return "epmanagement/dogAnatomy/DogAnatomy_Update";
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("/biz/dogAnatomy/edit")
	public R update(TDogAnatomy dogAnatomy){
		if(dogAnatomyMapper.updateByPrimaryKeySelective(dogAnatomy)>0){
			return R.ok();
			}
			return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("/biz/dogAnatomy/remove")
	public R remove(Integer id){
		if(dogAnatomyService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	
	
	
	
	
	
	
}
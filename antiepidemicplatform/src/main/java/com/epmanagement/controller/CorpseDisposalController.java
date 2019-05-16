package com.epmanagement.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dogmanager.utils.DogResult;
import com.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entities.AreaInfo;
import com.entities.CorpseDisposal;
import com.epmanagement.dao.CorpseDisposalDao;
import com.epmanagement.service.CorpseDisposalService;
import com.epmanagement.service.ManureDisposalService;
import com.epmanagement.vo.CorpseDisposalVO;
import com.epmanagement.vo.MediaUrl;
import com.epmanagement.vo.SelectVO;
import com.sys.controller.BaseController;


/**
 * 
 * @description 尸体无害化处理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-28 09:52:35
 */
 
@Controller
@RequestMapping("/biz/corpseDisposal")
public class CorpseDisposalController extends BaseController {
	@Autowired
	private CorpseDisposalService corpseDisposalService;
	@Autowired
	private CorpseDisposalDao corpseDisposalDao;
	@Autowired
	private ManureDisposalService manureDisposalService;
	
	
	
	/**
	 * 跳转到新增尸体处理页面
	 * @return
	 */
	@GetMapping("/add")
	@RequiresPermissions("/biz/corpseDisposal/add")
	String add(){
	    return "epmanagement/corpseDisposal_Add";
	}

	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("/biz/corpseDisposal/add")
	public R save(CorpseDisposal corpseDisposal){
		Integer id = corpseDisposalService.save(corpseDisposal);
			Map<String, Object>map=new HashMap<>();
				map.put("id", id);
			return R.ok(map);
	}
	/**
	 * 跳转到修改尸体页面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/edit")
	@RequiresPermissions("/biz/corpseDisposal/edit")
	String edit(Integer id,Model model){
		Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
		Map<String,Object> map = new HashMap<>();
		map.put("langType",type);
		map.put("id",id);
		//查找尸体处理详情
		CorpseDisposalVO corpseDetail = corpseDisposalDao.getCorpseDetaili18n(map);

//			Map<String, Object> map=new HashMap<>();
//		//查找所有犬尸处理法
//				map.put("type", "corpse_handle");
//			List<SelectVO> handleList = manureDisposalService.initSelData(map);
//				for(SelectVO s:handleList) {
//					if(s.getValue().equals(corpseDetail.getProcessingMethod())) {
//						s.setIsCheck("checked");
//					}
//				}
//		//查找所有死亡原因
//				map.put("type", "casuse_of_death");
//				List<SelectVO> deathList = manureDisposalService.initSelData(map);
//					for(SelectVO s:deathList) {
//						if(s.getValue().equals(corpseDetail.getCauseOfDeath())) {
//							s.setIsCheck("checked");
//						}
//					}

			model.addAttribute("corpse", corpseDetail);
//			model.addAttribute("handleList", handleList);
//			model.addAttribute("deathList", deathList);
			//跳转到修改尸体页面
		return "epmanagement/corpseDisposal_Update";
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("/biz/corpseDisposal/edit")
	public R update(CorpseDisposal corpseDisposal){
		corpseDisposalService.update(corpseDisposal);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("/biz/corpseDisposal/remove")
	public R remove( Integer id){
		if(corpseDisposalService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:corpseDisposal:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		corpseDisposalService.batchRemove(ids);
		return R.ok();
	}
	@GetMapping("byTitle")
	public void selectByTitle(@RequestParam Map<String, Object> params){
        Map<String, String> corpse = corpseDisposalService.selectByTitle(params);
    }
	/**
	 * 跳转到犬尸列表页面
	 * @return
	 */
	@GetMapping("/toCorpseDisposal")
	@RequiresPermissions("/biz/corpseDisposal/toCorpseDisposal")
	String CorpseDisposal(){
	    return "epmanagement/corpseDisposal";
	}
	
	/**
	 * 
	 * @param index 索引
	 * @param pageSize 页面大小
	 * @param orgId 选中的部门id
	 * @param pro 选中的用户id
	 * @param searchKey 搜索条件
	 * @return
	 */
	@ResponseBody
	@GetMapping("/findCorpseDisposalList")
	@RequiresPermissions("/biz/corpseDisposal/toCorpseDisposal")
	public PageUtils findCorpseDisposalList(Integer index,Integer pageSize,String searchKey,Integer orgId,Integer pro){
		PageUtils pageUtils = null;
		try {
			pageUtils=corpseDisposalService.findCorpseDisposalList(index,pageSize,searchKey,orgId,pro);
		} catch (Exception e) {
			
		}
		return pageUtils;
	}
	
	
	
	/**
	 * 查找尸体详情
	 * @param id 主键id
	 * @return
	 */
	@GetMapping("/getCorpseDetail")
	@RequiresPermissions("/biz/corpseDisposal/getCorpseDetail")
	public String getCorpseDetail(Integer id,Model model) {
				Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
		Map<String,Object> map = new HashMap<>();
		map.put("langType",type);
		map.put("id",id);
		CorpseDisposalVO corpseDetail = corpseDisposalService.getCorpseDetaili18n(map);
			
			List<MediaUrl> media = corpseDisposalDao.getMedia(id);
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
			model.addAttribute("corpse", corpseDetail);
			model.addAttribute("imageList", imageList);
			model.addAttribute("videoList", videoList);
		return "epmanagement/corpseDisposal_Detail";
	}

	/**
	 * 查找尸体详情
	 * @param id 主键id
	 * @return
	 */
	@GetMapping("/wxGetCorpseDetail")
	@RequiresPermissions("/biz/corpseDisposal/getCorpseDetail")
	@ResponseBody
	public DogResult wxGetCorpseDetail(Integer id) {
		CorpseDisposalVO corpseDetail = corpseDisposalService.getCorpseDetail(id);
		List<MediaUrl> media = corpseDisposalDao.getMedia(id);
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
		Map<String,Object> map = new HashMap<>();
		map.put("corpse", corpseDetail);
		map.put("imageList", imageList);
		map.put("videoList", videoList);
		return DogResult.ok(map);
	}
	
}

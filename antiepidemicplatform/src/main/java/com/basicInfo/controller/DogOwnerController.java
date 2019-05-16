package com.basicInfo.controller;

import com.basicInfo.dao.TDogOwnerMapper;
import com.basicInfo.service.DogOwnerService;
import com.basicInfo.service.PastoralCommitteeService;
import com.basicInfo.service.ProtectorService;
import com.basicInfo.vo.DogOwnerVO;
import com.dogmanager.VO.DogInfoVO;
import com.entities.AreaInfo;
import com.entities.TDogOwner;
import com.entities.TPastoralCommittee;
import com.entities.TSysDict;
import com.orgmanagement.service.AreaInfoService;
import com.sys.controller.BaseController;
import com.utils.FileUpload;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.areaUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/biz/dogOwner")
public class DogOwnerController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(DogOwnerController.class);
	@Autowired
	private DogOwnerService dogOwnerService;
	@Autowired
	private TDogOwnerMapper dogOwnerMapper;
	@Autowired
	private PastoralCommitteeService pasoralCommitteeService;
	@Autowired 
	private ProtectorService protectorService;
	@Autowired 
	private AreaInfoService areaInfoService;
	/**
	 * 跳转到犬主列表
	 * @return
	 */
	@GetMapping("/dogOwner_List")
	@RequiresPermissions("/biz/dogOwner/dogOwner_List")
	String springDefendInfo() {
		return "basicInfo/dogOwner/dogOwner_List";
	}
	/**
	 * 跳转到新增犬主
	 * @return
	 */
	@GetMapping("/dogOwner_Add")
	@RequiresPermissions("/biz/dogOwner/dogOwner_Add")
	String add(){
	    return "basicInfo/dogOwner/dogOwner_Add";
	}
	//1.新增犬主
	@RequestMapping("/addDogOwner")
	@RequiresPermissions("/biz/dogOwner/dogOwner_Add")
	@ResponseBody
	public R addDogOwner(TDogOwner dogOwner) {
		Integer id=dogOwnerService.insertSelective(dogOwner);
		if(id!=0){
			Map<String, Object>map=new HashMap<>();
				map.put("id", id);
			return R.ok(map);
		}
		return R.error();
	}
	
	//2.查看犬主列表
	@ResponseBody
	@GetMapping("/findDogOwnerList")
	@RequiresPermissions("/biz/dogOwner/dogOwner_List")
	public PageUtils findDogOwnerList(Integer index,Integer pageSize,String searchKey,Integer orgId,Integer pro){
		PageUtils pageUtils=null;
		try {
			pageUtils=dogOwnerService.findDogOwnerList(index,pageSize,searchKey,orgId,pro);
		} catch (Exception e) {
			logger.warn("查看犬主列表失败!");
		}
		return pageUtils;
	}
	
	/**
	 * 犬主详情
	 * @param  id 兽医id
	 */
	@GetMapping("/findDogOwnerDetail")
	@RequiresPermissions("/biz/dogOwner/findDogOwnerDetail")
	public String findDogOwnerDetail(Integer id,Model model){
		DogOwnerVO	dogOwnerVO=dogOwnerService.findDogOwnerDetail(id);
		List<AreaInfo> lists=new ArrayList<>();
			lists=areaUtils.areaR(areaInfoService.getList(), dogOwnerVO.getAreaId(), lists);
			List<DogInfoVO> dogList = dogOwnerMapper.findDogList(id);
		model.addAttribute("dogOwnerVO", dogOwnerVO);
		model.addAttribute("dogList", dogList);
		model.addAttribute("lists", lists);
		return "basicInfo/dogOwner/dogOwner_Detail";
	}

    /**
     * 犬主详情
     * @param  id 兽医id
     */
    @GetMapping("/wxfindDogOwnerDetail")
//    @RequiresPermissions("/biz/dogOwner/findDogOwnerDetail")
    @ResponseBody
    public R wxfindDogOwnerDetail(Integer id){
        DogOwnerVO	dogOwnerVO=dogOwnerService.findDogOwnerDetail(id);
		List<AreaInfo> lists=new ArrayList<>();
		lists=areaUtils.areaR(areaInfoService.getList(), dogOwnerVO.getAreaId(), lists);
        List<DogInfoVO> dogList = dogOwnerMapper.findDogList(id);
        Map<String,Object> map = new HashMap<>();
        map.put("dogOwnerVO",dogOwnerVO);
        map.put("dogList", dogList);
		map.put("lists",lists);
        return R.ok(map);
    }


	/**
	 * 获取用户所在区域
	 * @param  areaId 区域id
	 */
	@GetMapping("/wxfindUserArea")
	@ResponseBody
	public R wxfindUserArea(Integer areaId){
		List<AreaInfo> lists=new ArrayList<>();
		lists=areaUtils.areaR(areaInfoService.getList(), areaId, lists);
		Map<String,Object> map = new HashMap<>();
		map.put("lists",lists);
		return R.ok(map);
	}

	/**
	 * 删除犬主(后期加上删除狗)
	 */
	@RequestMapping("/deleteDogOwner")
	@RequiresPermissions("/biz/dogOwner/deleteDogOwner")
	@ResponseBody
	public R deleteDogOwner(Integer id) {
		if(dogOwnerService.deleteDogOwner(id)>0){
			return R.ok();
		}
		return R.error();
	}
	
	
	/**
	 * 跳转到修改犬主界面
	 * @param  id 犬主id
	 */
	@GetMapping("/toUpadatePage")
	@RequiresPermissions("/biz/dogOwner/toUpadatePage")
	public String toUpadatePage(Integer id,Model model){
		DogOwnerVO	dogOwnerVO=dogOwnerService.findDogOwnerDetail(id);
		List<TPastoralCommittee> pastoralList = pasoralCommitteeService.findPastoralByAreaId();
			for(TPastoralCommittee t:pastoralList) {
				if(dogOwnerVO.getPastoralCommitteeId()==t.getId()) {
					t.setRemarks("checked");
				}
			}
			List<TSysDict> nationList = protectorService.selectEthnic();
			List<TSysDict> edBaList = protectorService.selectEducationBackground();
			for(TSysDict n:nationList) {
				if(n.getName().equals(dogOwnerVO.getNation())) {
					n.setRemarks("checked");
				}
			}
		for(TSysDict n:edBaList) {
			if(n.getName().equals(dogOwnerVO.getDegreeOfEducation())) {
				n.setRemarks("checked");
			}
		}
		model.addAttribute("dogOwnerVO", dogOwnerVO);
		model.addAttribute("pastoralList", pastoralList);
		model.addAttribute("nationList", nationList);
		model.addAttribute("edBaList", edBaList);
		return "basicInfo/dogOwner/dogOwner_Update";
	}
	
	/**
	 * 修改犬主
	 */
	@RequestMapping("/updateDogOwner")
	@RequiresPermissions("/biz/dogOwner/toUpadatePage")
	@ResponseBody
	public R updateForage(TDogOwner dogOwner) {
		if(dogOwnerService.updateDogOwner(dogOwner)>0){
			return R.ok();
		}
		return R.error();
	}
	
	
	
	/**
	 * 根据身份证或者电话号码进行唯一性判断
	 * @param cardNum:犬主身份证号
	 * @param phoneNum:犬主电话 号码
	 */
	@RequestMapping("/judugeExit")
	@ResponseBody
	public R judugeExit(String cardNum,String phoneNum) {
		if(dogOwnerService.judugeExit(cardNum,phoneNum)==0){
			return R.ok();
		}
		return R.error();
	}




	/**
	 * 修改时根据身份证或者电话号码进行唯一性判断
	 * @param id:主键id
	 * @param cardNum:犬主身份证号
	 * @param phoneNum:犬主电话 号码
	 */
	@RequestMapping("/judugeUpdateExit")
	@ResponseBody
	public R judugeUpdateExit(Integer id,String cardNum,String phoneNum) {
		if(dogOwnerService.judugeUpdateExit(id,cardNum,phoneNum)==0){
			return R.ok();
		}
		return R.error();
	}


	/**
	 * 修改时根据身份证或者电话号码进行唯一性判断
	 * @param id:主键id
	 * @param cardNum:犬主身份证号
	 * @param phoneNum:犬主电话 号码
	 */
	@RequestMapping("/wxJudugeUpdateExit")
	@ResponseBody
	public R wxjudugeUpdateExit(Integer id,String cardNum,String phoneNum) {
		return dogOwnerService.wxjudugeUpdateExit(id,cardNum,phoneNum);
	}
}

package com.publicitytraining.controller;


import com.dogmanager.utils.DogResult;
import com.entities.ActivityInfoDO;
import com.entities.OrgInfo;
import com.orgmanagement.service.OrgInfoService;
import com.publicitytraining.service.ActivityInfoService;
import com.publicitytraining.vo.ActivityInfoVO;
import com.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
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
 * @date 2018-11-05 15:17:03
 */
 
@Controller
@RequestMapping("/biz/activityInfo")
public class ActivityInfoController {
	@Autowired
	private ActivityInfoService activityInfoService;
	@Autowired
    private OrgInfoService orgInfoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping("/trainActivity")
//	@RequiresPermissions("system:activityInfo:activityInfo")
	String trainActivity(Model model){
	    model.addAttribute("user",ShiroUtils.getUser());
	    return "publicitytraining/trainActivity";
	}

    @GetMapping("/conductActivity")
//	@RequiresPermissions("system:activityInfo:activityInfo")
    String ActivityInfo(Model model){
	    model.addAttribute("user",ShiroUtils.getUser());
        return "publicitytraining/conductActivity";
    }
	
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("system:activityInfo:activityInfo")
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
		//查询列表数据
        Query query = new Query(params);
		List<ActivityInfoVO> activityInfoList = activityInfoService.list(query);
        //用于前端判断“修改”“删除”权限
        if(activityInfoList != null && activityInfoList.size() > 0) {
            Integer userId = ShiroUtils.getUserId().intValue();
            for (ActivityInfoVO vo:
                    activityInfoList) {
                vo.setUserId(userId);
            }
        }
		int total = activityInfoService.count(query);
		PageUtils pageUtils = new PageUtils(activityInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
//	@RequiresPermissions("system:activityInfo:add")
	String add(){
	    return "publicitytraining/conductActivityAdd";
	}

    @GetMapping("/addTrainActivity")
//	@RequiresPermissions("system:activityInfo:add")
    String addTrainActivity(){
        return "publicitytraining/trainActivityAdd";
    }

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("system:activityInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
        ActivityInfoVO activityInfo = activityInfoService.get(id);
		model.addAttribute("activityInfo", activityInfo);
	    return "publicitytraining/conductActivityEdit";
	}

    @GetMapping("/editTrainActivity/{id}")
//	@RequiresPermissions("system:activityInfo:edit")
    String editTrainActivity(@PathVariable("id") Integer id,Model model){
        ActivityInfoVO activityInfo = activityInfoService.get(id);
        model.addAttribute("activityInfo", activityInfo);
        return "publicitytraining/trainActivityEdit";
    }

	/**
     * 查看详情(宣传活动)
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/detail/{id}")
    String detail(@PathVariable("id")Integer id,Model model) {
        ActivityInfoVO activityInfoVO = activityInfoService.getActivityDetail(id);
        model.addAttribute("activityInfoVO",activityInfoVO);
        return "publicitytraining/conductActivityDetail";
    }
	/**
	 * wx查看详情(宣传活动)
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/detail2")
	@ResponseBody
	DogResult detail2(Integer id) {
		ActivityInfoVO activityInfoVO = activityInfoService.getActivityDetail(id);
		return DogResult.ok(activityInfoVO);
	}

    /**
     * 查看详情(培训活动)
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/trainDetail/{id}")
    String trainDetail(@PathVariable("id")Integer id,Model model) {
        ActivityInfoVO activityInfoVO = activityInfoService.getActivityDetail(id);
        model.addAttribute("activityInfoVO",activityInfoVO);
        return "publicitytraining/trainActivityDetail";
    }


	/**
	 * wx查看详情(培训活动)
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/trainDetail")
	@ResponseBody
	DogResult trainDetail(Integer id) {
		ActivityInfoVO activityInfoVO = activityInfoService.getActivityDetail(id);
		return DogResult.ok(activityInfoVO);
	}
	
	/**
	 * 保存宣传活动
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("system:activityInfo:add")
	public R save(ActivityInfoDO activityInfo){
	    activityInfo.setOrgId(ShiroUtils.getUser().getDeptId().intValue());
	    activityInfo.setActivityType("0");
	    activityInfo.setCreateDate(new Date());
	    activityInfo.setOperatorId(ShiroUtils.getUserId().intValue());
		if(activityInfoService.save(activityInfo)>0){
			return R.ok().put("id",activityInfo.getId());
		}
		return R.error();
	}

    /**
     * 保存培训活动
     * @param activityInfo
     * @return
     */
    @ResponseBody
    @PostMapping("/saveTrainActivity")
//	@RequiresPermissions("system:activityInfo:add")
    public R saveTrainActivity(ActivityInfoDO activityInfo){
        activityInfo.setOrgId(ShiroUtils.getUser().getDeptId().intValue());
        activityInfo.setActivityType("1");
        activityInfo.setCreateDate(new Date());
        activityInfo.setOperatorId(ShiroUtils.getUserId().intValue());
        if(activityInfoService.save(activityInfo)>0){
            return R.ok().put("id",activityInfo.getId());
        }
        return R.error();
    }
	/**edit
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("system:activityInfo:edit")
	public R update( ActivityInfoDO activityInfo){
		activityInfoService.update(activityInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("system:activityInfo:remove")
	public R remove( Integer id){
		if(activityInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("system:activityInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		activityInfoService.batchRemove(ids);
		return R.ok();
	}
	
}

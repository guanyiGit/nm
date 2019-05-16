package com.epmanagement.controller;

import com.dogmanager.dao.TDogInfoMapper;
import com.dogmanager.service.DogInfoService;
import com.dogmanager.utils.DogResult;
import com.entities.Antiepidemic;
import com.entities.AreaInfo;
import com.entities.OrgInfo;
import com.epmanagement.service.AntiepidemicService;
import com.epmanagement.vo.AntiepidemicVO;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.AreaInfoService;
import com.orgmanagement.service.OrgInfoService;
import com.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 *
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-27 14:33:11
 */

@Controller
@RequestMapping("/biz/antiepidemic")
public class AntiepidemicController {

	private Logger logger = LoggerFactory.getLogger(AntiepidemicController.class);

	@Autowired
	private AntiepidemicService antiepidemicService;
	@Autowired
	private OrgInfoService orgInfoService;
	@Autowired
	private TDogInfoMapper dogInfoMapper;
	@Autowired
	private DogInfoService dogInfoService;
	@Autowired
	private AreaInfoService areaInfoService;


	@GetMapping()
	//@RequiresPermissions("system:antiepidemic:antiepidemic")
	String Antiepidemic(){
		return "system/antiepidemic/antiepidemic";
	}

	//跳转到秋防页面
	@GetMapping("/autumnDefendInfo")
	//@RequiresPermissions("/biz/antiepidemic/autumnDefendInfo")
	String autumnDefendInfo() {
		return "epmanagement/AutumnDefendInfo";
	}

	//跳转到春防页面
	@GetMapping("/springDefendInfo")
	//@RequiresPermissions("/biz/antiepidemic/springDefendInfo")
	String springDefendInfo() {
		return "epmanagement/SpringDefendInfo";
	}

	//跳转到月月投页面
	@GetMapping("/monthDefendInfo")
	//@RequiresPermissions("/biz/antiepidemic/monthDefendInfo")
	String monthDefendInfo() {
		return "epmanagement/MonthDefendInfo";
	}

	//跳转到编辑页面
	@GetMapping("/editDefend/{type}")
	//@RequiresPermissions("/biz/antiepidemic/editDefend")
	String editDefend(@PathVariable("type") Integer type,Model model) {
		model.addAttribute("type",type);
		return "epmanagement/editDefend";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}


	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("system:antiepidemic:antiepidemic")
	public List<Map<String,Object>> list(@RequestParam Map<String, Object> params){
		List<Map<String,Object>> list = antiepidemicService.list(params);
		return list;
	}

	@ResponseBody
	@GetMapping("/pagelist")
//	@RequiresPermissions("system:antiepidemic:antiepidemic")
	public PageUtils pagelist(@RequestParam Map<String, Object> params){
		//查询列表数据
		UserDO user = ShiroUtils.getUser();
		if(user.getRoles().get(0).getType()==2){
            Integer proId = dogInfoMapper.findProId(user.getUserId().intValue());
            if(proId==null){
				logger.error("【防疫管理】通过用户查询防疫员失败;userId:",user.getUserId());
            	throw new RuntimeException();
			}
            params.put("protector",proId);
		}else{
                List<OrgInfo> orgList=orgInfoService.orgList();
                List<Integer> res=new ArrayList<>();
			if(StringUtils.isEmpty(params.get("orgId"))) {
                    res= OrgUtils.orgReverse(orgList, user.getDeptId().intValue(), res);
                }else{
					res= OrgUtils.orgReverse(orgList, Integer.parseInt(params.get("orgId").toString()), res);
                }
                params.put("lists",res);
        }
		Query query = new Query(params);
		List<AntiepidemicVO> antiepidemicList = antiepidemicService.pagelist(query);
        List<AreaInfo> areaInfoList = areaInfoService.getList();
        for (AntiepidemicVO apv:antiepidemicList){
			if(!StringUtils.isEmpty(apv.getTown())){
				apv.setTown(areaInfoService.findAreas(Integer.parseInt(apv.getTown()),areaInfoList));
			}
		}
		int total = antiepidemicService.count(query);
		PageUtils pageUtils = new PageUtils(antiepidemicList, total);
		return pageUtils;
	}

	@GetMapping("/add/{type}")
	@RequiresPermissions("/biz/antiepidemic/add")
//	@RequiresPermissions("system:antiepidemic:add")
	String add(@PathVariable("type") Integer type,Model model){
		model.addAttribute("type",type);
		return "epmanagement/addDefend";
//	    return "epmanagement/11";
	}

	@GetMapping("/edit/{type}")
		//@RequiresPermissions("system:antiepidemic:edit")
	@RequiresPermissions("/biz/antiepidemic/edit")
	String edit(Integer id,Model model,@PathVariable("type") Integer type){
		AntiepidemicVO antiepidemicVO = antiepidemicService.get(id);
		List<HashMap<String, Object>> picList = dogInfoService.findPicList(id,3);
		List<HashMap<String, Object>> VideoList = dogInfoService.findVideoList(id,3);
		model.addAttribute("antiepidemic", antiepidemicVO);
		model.addAttribute("picList", picList);
		model.addAttribute("VideoList", VideoList);
		model.addAttribute("type",type);
		return "epmanagement/SpringDefendInfoDetail";
	}

	@GetMapping("/edit2")
	//@RequiresPermissions("system:antiepidemic:edit")
	@ResponseBody
	public  DogResult edit2(Integer id){
		AntiepidemicVO antiepidemicVO = antiepidemicService.get(id);
		List<HashMap<String, Object>> picList = dogInfoService.findPicList(id,3);
		List<HashMap<String, Object>> VideoList = dogInfoService.findVideoList(id,3);
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("antiepidemic",antiepidemicVO);
        map1.put("picList",picList);
        map1.put("VideoList",VideoList);
        if(antiepidemicVO!=null){
			return DogResult.ok(map1);
		}
		return DogResult.build(400,"请求无数据");
	}


	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping(value = "/save" )
//	@RequiresPermissions("system:antiepidemic:add")
	public R save(Antiepidemic antiepidemic){
		int id = antiepidemicService.save(antiepidemic);
		if(id>0){
			HashMap<String, Object> res = new HashMap<>();
			res.put("id",antiepidemic.getId());
			return R.ok(res);
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@PostMapping("/update")
	//@RequiresPermissions("system:antiepidemic:edit")
	public R update(Antiepidemic antiepidemic){
		antiepidemicService.update(antiepidemic);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	//@RequiresPermissions("system:antiepidemic:remove")
	public R remove( Integer id){
		if(antiepidemicService.remove(id)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	//@RequiresPermissions("system:antiepidemic:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		antiepidemicService.batchRemove(ids);
		return R.ok();
	}

	//	根据溯源id或防疫药品查询
	public String aa (HttpServletRequest request, HttpServletResponse response){
		String str = request.getParameter("str");
		antiepidemicService.selectBySidOrDrugName(str);
		return   "";
	}

	@GetMapping("/getDrug")
	@ResponseBody
	public DogResult getDrug(Integer type){
		List<HashMap<String, Object>> list = antiepidemicService.findDrug(type);
		if (list.size()>0){
			return  DogResult.ok(list);
		}
		return DogResult.build(400,"请求无数据");
	}



	//    图片上传
	@ResponseBody
	@RequiresPermissions("system:antiepidemic:batchRemove")
	@PostMapping("/uploadPic")
	public R uploadPic(HttpServletRequest request,HttpServletResponse response
			,@RequestParam("files") MultipartFile[] files){

		List<UploadResult> uploadResults = FastDfsUtils.uploadFiles(files);
//			uploadResults.stream().forEach(res ->{
//
//				System.out.println("url  ===:::"+res.getUrl()+"thumbURL  ====::::"+res.getThumbnailUrl());
//			});

		return R.ok();
	}

	//	视频上传
	@ResponseBody
	@RequiresPermissions("system:antiepidemic:batchRemove")
	@PostMapping("/uploadVio")
	public R uploadVio(MultipartHttpServletRequest request, @RequestParam("videoUrl") MultipartFile[] files){
		request.getHeaderNames();
//		List<UploadResult> uploadResults = FastDfsUtils.uploadFiles(files);
//			uploadResults.stream().forEach(res ->{
//
//				System.out.println("url  ===:::"+res.getUrl()+"thumbURL  ====::::"+res.getThumbnailUrl());
//			});

		return R.ok();
	}


}

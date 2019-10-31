package com.epmanagement.controller;

import com.dogmanager.service.DogInfoService;
import com.dogmanager.utils.DogResult;
import com.entities.DeviceInfo;
import com.entities.ManureDisposal;
import com.entities.OrgInfo;
import com.epmanagement.service.ManureDisposalService;
import com.epmanagement.vo.ManureDisposalVO;
import com.epmanagement.vo.MediaUrl;
import com.epmanagement.vo.SelectVO;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.OrgInfoService;
import com.orgmanagement.service.UserInfoService;
import com.orgmanagement.vo.UserSelVO;
import com.sys.controller.BaseController;
import com.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 *
 * @description 粪便无害化处理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-27 18:21:05
 */

@Controller
@RequestMapping("/biz/manureDisposal")
public class ManureDisposalController  extends BaseController {
	@Autowired
	private ManureDisposalService manureDisposalService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private OrgInfoService orgInfoService;
    @Autowired
    private DogInfoService dogInfoService;


	@GetMapping()
	String ManureDisposal(Model model){
        UserDO user = ShiroUtils.getUser();
        model.addAttribute("user",user);
        return "epmanagement/manureDisposal";
	}

    /**
     * 修改
     */
    @ResponseBody
    @PostMapping("/update")
//	@RequiresPermissions("biz:manureDisposal:update")
    public R update( ManureDisposal manureDisposal){
        manureDisposal.setTraceId(manureDisposal.getTraceId().trim());
        manureDisposalService.update(manureDisposal);
        return R.ok();
    }

	//跳转新增粪便无害化处理
	@GetMapping("/manureDisposalAdd")
//	@RequiresPermissions("biz:manureDisposal:manureDisposalAdd")
	public String manureDisposalAdd(Model model){
	    model.addAttribute("user",ShiroUtils.getUser());
		return "epmanagement/manureDisposalAdd";
	}

	//检查该溯源ID是否存在
    @RequestMapping("/checkDeviceNo")
    @ResponseBody
    public R checkDeviceNo(String deviceNo) {
	    if(deviceNo == null || "".equals(deviceNo)) {
	        return R.error(9001,"溯源ID不能为空");
        }
        DeviceInfo device = dogInfoService.findDeviceByNo(deviceNo.trim());
        if(device != null) {
            return R.ok();
        }else {
            return R.error(9000,"该溯源ID不存在");
        }
    }


	//查询列表数据
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("biz:manureDisposal:list")
	public PageUtils list(@RequestParam Map<String, Object> params){
        UserDO user = ShiroUtils.getUser();
            int type = user.getRoles().get(0).getType();
        if(type == 2) { //防疫员
            params.put("operator",user.getUserId());
            Query query = new Query(params);
            List<Map<String,Object>> manureDisposalList = manureDisposalService.list(query);
            int total = manureDisposalService.count(query);
            PageUtils pageUtils = new PageUtils(manureDisposalList, total);
            return pageUtils;
        }
        Long orgId = user.getDeptId();
        if(params.get("orgId") != null && params.get("orgId").toString().trim() != "") {
            orgId = Long.parseLong(params.get("orgId").toString());
        }
        List<OrgInfo> orgInfos = orgInfoService.orgList();
        List<Integer> lists = OrgUtils.orgReverse(orgInfos, Integer.parseInt(orgId.toString()), new ArrayList<>());
        params.put("lists",lists);
        Query query = new Query(params);
		List<Map<String,Object>> manureDisposalList = manureDisposalService.list(query);
		int total = manureDisposalService.count(query);
		PageUtils pageUtils = new PageUtils(manureDisposalList, total);
		return pageUtils;
	}



//	@GetMapping("/add")
//	String add(){
//	    return "system/manureDisposal/add";
//	}

	@GetMapping("/edit/{id}")
//	@RequiresPermissions("biz:manureDisposal:edit")
	String edit(@PathVariable("id") Integer id,Model model){
        ManureDisposalVO manureDisposal = manureDisposalService.get(id);
        List<UserSelVO> userList = userInfoService.userSelList();
        Integer operator = manureDisposal.getOperator();
        String operatorName = "";
        for (UserSelVO user:
             userList) {
            if(user.getId().intValue() == operator.intValue()) {
//                user.setIsCheck("checked");     //将要回显的数据添加标记(不采用下拉框了)
                operatorName = user.getName();
            }
        }
//        List<MediaUrl> mediaUrlList = manureDisposalService.getMediaUrl(id);
//        List<MediaUrl> picUrlList = new ArrayList<>();
//        List<MediaUrl> videoUrlList = new ArrayList<>();
//        for (MediaUrl m :
//                mediaUrlList) {
//            if(m.getIsVideo().intValue() == 0) {
//                picUrlList.add(m);
//            }else {
//                videoUrlList.add(m);
//            }
//        }
//        model.addAttribute("picUrlList",picUrlList);
//        model.addAttribute("videoUrlList",videoUrlList);
        model.addAttribute("userList",userList);
        model.addAttribute("operatorName",operatorName);
        model.addAttribute("manureDisposal", manureDisposal);
	    return "epmanagement/manureDisposalEdit";
	}

    /**
     * 查看详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/detail/{id}")
//	@RequiresPermissions("biz:manureDisposal:detail")
    String detail(@PathVariable("id") Integer id,Model model){
        ManureDisposalVO  manureDisposal = manureDisposalService.get(id);
        List<UserSelVO> userList = userInfoService.userSelList();
        Integer operator = manureDisposal.getOperator();
        String protecterName = "";
        for (UserSelVO user:
                userList) {
            System.out.println(user.getId());
            if(user.getId().intValue() == operator.intValue()) {
                protecterName = user.getName();
                break;
            }
        }
        List<MediaUrl> mediaUrlList = manureDisposalService.getMediaUrl(id);
        List<MediaUrl> picUrlList = new ArrayList<>();
        List<MediaUrl> videoUrlList = new ArrayList<>();
        for (MediaUrl m :
                mediaUrlList) {
            if(m.getIsVideo().intValue() == 0) {
                picUrlList.add(m);
            }else {
                videoUrlList.add(m);
            }
        }
        manureDisposal.setProtecterName(protecterName);
        model.addAttribute("picUrlList",picUrlList);
        model.addAttribute("videoUrlList",videoUrlList);
//        model.addAttribute("protecterName",protecterName);
            model.addAttribute("manureDisposal", manureDisposal);
        return "epmanagement/manureDisposalDetail";
    }

    /**
     * 查看详情
     * @param id
     * @return
     */
    @GetMapping("/wxDetail/{id}")
//	@RequiresPermissions("biz:manureDisposal:detail")
    @ResponseBody
    public DogResult wxDetail(@PathVariable("id") Integer id){
        ManureDisposalVO  manureDisposal = manureDisposalService.get(id);
        List<UserSelVO> userList = userInfoService.userSelList();
        Integer operator = manureDisposal.getOperator();
        String protecterName = "";
        for (UserSelVO user: userList) {
            System.out.println(user.getId());
            if(user.getId().intValue() == operator.intValue()) {
                protecterName = user.getName();
                break;
            }
        }
        List<MediaUrl> mediaUrlList = manureDisposalService.getMediaUrl(id);
        List<MediaUrl> picUrlList = new ArrayList<>();
        List<MediaUrl> videoUrlList = new ArrayList<>();
        for (MediaUrl m : mediaUrlList) {
            if(m.getIsVideo().intValue() == 0) {
                picUrlList.add(m);
            }else {
                videoUrlList.add(m);
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("picUrlList",picUrlList);
        map.put("videoUrlList",videoUrlList);
        map.put("protecterName",protecterName);
        map.put("manureDisposal", manureDisposal);
        return DogResult.ok(map);
    }

	@ResponseBody
	@PostMapping("/save")
// @RequiresPermissions("biz:manureDisposal:save")
	public R save(ManureDisposal manureDisposal){
        manureDisposal.setTraceId(manureDisposal.getTraceId().trim());
        manureDisposal.setOperator(getUser().getUserId().intValue());
        manureDisposal.setDealTime(new Date());
		manureDisposal.setUpdateDate(new Date());
		manureDisposal.setCreateDate(new Date());
		manureDisposal.setOrgId(getUser().getDeptId().intValue());
		if(manureDisposalService.save(manureDisposal)>0){
			return R.ok().put("id",manureDisposal.getId());
		}
		return R.error();
	}


	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("biz:manureDisposal:remove")
	public R remove( Integer id){
		if(manureDisposalService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
//	@RequiresPermissions("biz:manureDisposal:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		manureDisposalService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 根据title查询
	 */
	@GetMapping("byTitle")
//	@RequiresPermissions("biz:manureDisposal:byTitle")
	public void selectByTitle(HttpServletRequest request, HttpServletResponse response){
        String title = request.getParameter("title");
        Map<String, String> manure = manureDisposalService.selectByTitle(title);
    }

	//    图片上传
    @ResponseBody
    //@RequiresPermissions("biz:manureDisposal:uploadPic")
    @PostMapping("/uploadPic")
    public R uploadPic(HttpServletRequest request,HttpServletResponse response
            ,@RequestParam("files") MultipartFile[] files){
        Map<String,Object> map = new HashMap<>();

        String id = request.getParameter("id");
        if(id == null) {
            return R.error(10001,"id不能为空");
        }
        String type = request.getParameter("type");
        if(type == null) {
            return R.error(10002,"type不能为空");
        }
        map.put("id",id);
        map.put("type",type);
        map.put("isVideo",0);

        List<UploadResult> uploadResults = FastDfsUtils.uploadFiles(files);
//        uploadResults.stream().forEach(res ->{
//            System.out.println("url  ===:::"+res.getUrl()+"thumbURL  ====::::"+res.getThumbnailUrl());
//        });
        int result = manureDisposalService.saveMediaAndRef(uploadResults,map);
        if(result > 0) {
            return R.ok();
        }
        return R.error(10003,"上传图片失败");
    }

    //视频上传
    @ResponseBody
    //@RequiresPermissions("biz:manureDisposal:uploadVideo")
    @PostMapping("/uploadVideo")
    public R uploadVideo(HttpServletRequest request,HttpServletResponse response
            ,@RequestParam("videos") MultipartFile[] videos){
        Map<String,Object> map = new HashMap<>();

        String id = request.getParameter("id");
        if(id == null) {
            return R.error("id不能为空");
        }
        String type = request.getParameter("type");
        if(type == null) {
            return R.error("type不能为空");
        }
        map.put("id",id);
        map.put("type",type);
        map.put("isVideo",1);   //1代表视频

        List<String> videoUrlList = FastDfsUtils.uploadVideos(videos);
        int result = manureDisposalService.saveVideoMediaAndRef(videoUrlList,map);
        if(result > 0) {
            return R.ok();
        }
        return R.error("上传视频失败");
    }

	/**
	 * 初始化下拉列表
	 * @param map
	 * @return
	 */
	@RequestMapping("/initSelectData")
    @ResponseBody
	public List<SelectVO> initSelectData(@RequestParam Map<String,Object> map) {
        List<SelectVO> result = manureDisposalService.initSelData(map);
        return result;
	}
}

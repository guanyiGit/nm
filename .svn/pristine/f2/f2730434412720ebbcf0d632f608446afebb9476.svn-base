package com.devicemanagement.controller;

import com.basicInfo.service.ProtectorService;
import com.devicemanagement.service.FenceService;
import com.entities.OrgInfo;
import com.entities.TFenceDef;
import com.entities.TFenceDetail;
import com.entities.TProtector;
import com.orgmanagement.domain.RoleDO;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.OrgInfoService;
import com.orgmanagement.service.UserInfoService;
import com.sys.controller.BaseController;
import com.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-30 14:41:44
 */
 
@Controller
@RequestMapping("/biz/fence")
public class FencesController extends BaseController {
	@Autowired
	private FenceService fenceService;
	@Autowired
    private OrgInfoService orgInfoService;
	@Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ProtectorService protectorService;

	@GetMapping()
//	@RequiresPermissions("system:deviceRecord:deviceRecord")
	String DeviceRecord(Model model){
        RoleDO role = getUser().getRoles().get(0);
        int roleType = role.getType();
        model.addAttribute("roleType",roleType);
        return "devicemanagement/fenceList";
	}

    @GetMapping("addFence")
//	@RequiresPermissions("system:deviceRecord:deviceRecord")
    String addFence(){
        return "devicemanagement/drawFence";
    }


	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
        params = dealParams(params);
		Query query = new Query(params);
		List<Map<String,Object>> deviceRecordList = fenceService.list(query);
		int total = fenceService.count(query);
		PageUtils pageUtils = new PageUtils(deviceRecordList, total);
		return pageUtils;
	}



    @GetMapping("/lookOver/{id}")
    public String lookOver(@PathVariable("id") Integer id , Model model){
        List<Map<String, Object>> list = fenceService.getFenceDetailByFenceId(id);
        list.stream().forEach(fence ->{
            Double lng = Double.valueOf(fence.get("lng").toString());
            Double lat = Double.valueOf(fence.get("lat").toString());
            Map<String, Double> map = GaoDeUtils.coordinateSwitch(lng, lat);
            fence.put("lng",map.get("lng"));
            fence.put("lat",map.get("lat"));
        });
        model.addAttribute("list",list);
        return "devicemanagement/drawFence";
    }

    @GetMapping("/lookOverAll")
    public String lookOverAll(@RequestParam Map<String, Object> params,Model model){
        params = dealParams(params);
        //查询列表数据
        List<TFenceDef> fences = fenceService.getAllFences(params);
        fences.stream().forEach(fence ->{
            List<TFenceDetail> list = fence.getList();
            list.stream().forEach(fd->{
                Map<String, Double> stringDoubleMap = GaoDeUtils.coordinateSwitch(fd.getLng(), fd.getLat());
                fd.setLng(stringDoubleMap.get("lng"));
                fd.setLat(stringDoubleMap.get("lat"));
            });
        });
        model.addAttribute("fences",fences);
        return "devicemanagement/drawFence";
    }

    @ResponseBody
    @PostMapping("/delete")
    public R deleteFence(Integer id){
        if(fenceService.deleteFenceById(id)>0){
            return R.ok();
        }

        return R.error();
    }
    @GetMapping("/add")
    public String add(@RequestParam String  points,Model model){

        model.addAttribute("paths",points);
        return "devicemanagement/addFence";
    }
    @ResponseBody
    @PostMapping("/save")
    public R save(@RequestParam Map<String,Object> map){
        UserDO user = getUser();
        Long userId = user.getUserId();
        Long deptId = user.getDeptId();
        Long areaId = user.getAreaId();
        map.put("userId",userId);
        map.put("deptId",deptId);
        map.put("areaId",areaId);
        if(fenceService.saveFence(map)>0){
         return  R.ok()   ;
        }
        return R.error();
    }

    public Map<String, Object> dealParams (Map<String, Object> params){
        String pm = params.get("userId").toString();
        UserDO user = getUser();
        Long deptId = user.getDeptId();
        RoleDO role = user.getRoles().get(0);
        int type = role.getType();
        if(type==1||type==6||type==10||type==5||type==9){//州县级
            deptId = Long.parseLong(pm);
            List<OrgInfo> orgInfos = orgInfoService.orgList();
            List<Integer>  orgIds = OrgUtils.orgReverse(orgInfos, Integer.parseInt(deptId.toString()), new ArrayList<>());
            params.put("orgIds",orgIds);
        }else if(type==3||type==8){//乡级
            if(pm==null||"".equals(pm)){
                List<TProtector> Protectors = protectorService.initProtectorSel2(deptId);
                List<Integer> userIds = Protectors.stream().map(protector -> {
                    return protector.getId();
                }).collect(Collectors.toList());
                params.put("userIds",userIds);
            }else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(pm));
                params.put("userIds",list);
            }
        }else if(type==2){//防疫员
            Long userId = user.getUserId();
            ArrayList<Integer> list = new ArrayList<>();
            list.add(userId.intValue());
            params.put("userIds",list);
        }
        return params;
    }

}

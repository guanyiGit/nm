package com.posiition.controller;

import com.basicInfo.service.ProtectorService;
import com.dogmanager.service.DogInfoService;
import com.dogmanager.utils.DogResult;
import com.entities.OrgInfo;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.OrgInfoService;
import com.posiition.VO.DogRecordInfo;
import com.posiition.VO.DogRefDeviceRecordMax;
import com.posiition.VO.fenceDetailVO;
import com.posiition.VO.postionVO;
import com.posiition.service.DeviceFenceService;
import com.posiition.service.PositionService;
import com.sys.controller.BaseController;
import com.utils.GlobalResult;
import com.utils.OrgUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 定位相关
 */
@Controller
@RequestMapping(value = "/biz/position")
public class PositionController extends BaseController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private ProtectorService protectorService;

    @Autowired
    private DogInfoService dogInfoService;

    @Autowired
    private DeviceFenceService deviceFenceService;

    @Autowired
    private OrgInfoService orgInfoService;

    @GetMapping()
    public String position() {
        return "devicemanagement/position";
    }

    @GetMapping("/findList")
    public String getposition() {
        int type = getUser().getRoles().get(0).getType();
        if(type==3 || type==8){
            return "devicemanagement/real-timePositioning2";
        }else if(type==2){
            return "devicemanagement/real-timePositioning";
        }else if(type==5 || type==6 || type==9 || type==10){
            return "devicemanagement/real-timePositioning3";
        }
        return "devicemanagement/real-timePositioning";
    }




    /**
     * 查询犬只定位信息
     *
     * @param dogIds 犬id集合
     * @return
     */
    @GetMapping(value = "/list")
    //@RequiresPermissions("system:position:pistionList")
    @ResponseBody
    public String pistionList(@RequestParam("dogIds") List<Integer> dogIds) {
        return new GlobalResult().objResultJSON(positionService.pistionList(dogIds));
    }

    /**
     * 防疫员查询我的犬只定位集合
     * @return
     */
    @GetMapping(value = "/getdogPosition")
    @RequiresPermissions("system:position:getdogPosition")
    @ResponseBody
    public DogResult findDeviceDataByProtectorId() {
        UserDO user = getUser();
        int protectorId = protectorService.findProtectorDetailByUserId(user.getUserId().intValue());
        List<Map<String,Object>> list = new ArrayList<>();
        List<DogRefDeviceRecordMax> DogRefDeviceRecordMaxs = positionService.findDeviceDataByProtectorId(protectorId);
        for (DogRefDeviceRecordMax item:DogRefDeviceRecordMaxs) {
            Map<String,Object> dogRefDeviceRecordMap = new HashMap<>();
            dogRefDeviceRecordMap.put("dogRefDeviceRecordMax",item);
            Map<String, Object> ownerNameAndDogNameMap = dogInfoService.getOwnerNameAndDogName(item.getTraceId());
            dogRefDeviceRecordMap.put("dogName",ownerNameAndDogNameMap.get("dogName"));
            dogRefDeviceRecordMap.put("ownerName",ownerNameAndDogNameMap.get("ownerName"));
            list.add(dogRefDeviceRecordMap);
        }
        return  DogResult.ok(list);
    }

    /**
     * 根据犬主名或犬名获取犬只定位数据
     * @param string
     * @return
     */
    @GetMapping(value = "/getDogLocation")
    @RequiresPermissions("system:position:getDogLocation")
    @ResponseBody
    public DogResult findDeviceDataByTraceId(String string) {
        UserDO user = getUser();
        int protectorId = protectorService.findProtectorDetailByUserId(user.getUserId().intValue());
        List<Map<String,Object>>  dogInfos = dogInfoService.getTraceIdByOwnerNameOrDogName(string,protectorId);
        List<Map<String,Object>> list = new ArrayList<>();
        for (Map<String,Object> dogInfo:dogInfos) {
            DogRefDeviceRecordMax deviceDataByDeviceNo = positionService.findDeviceDataByTraceId(dogInfo.get("traceId").toString());
            Map<String,Object> dogRefDeviceRecordMap = new HashMap<>();
            dogRefDeviceRecordMap.put("dogRefDeviceRecordMax",deviceDataByDeviceNo);
            dogRefDeviceRecordMap.put("dogName",dogInfo.get("dogName"));
            dogRefDeviceRecordMap.put("ownerName",dogInfo.get("ownerName"));
            list.add(dogRefDeviceRecordMap);
        }
        return DogResult.ok(list);
    }

    /**
     * 查询犬的轨迹信息
     * @param traceId
     * @param date
     * @return
     */
    @GetMapping(value = "/getContrailByTraceIdAndDate")
    @RequiresPermissions("system:position:getContrailByTraceIdAndDate")
    @ResponseBody
     public DogResult getContrailByTraceIdAndDate(String traceId, Date date){
        List<DogRefDeviceRecordMax> dogContrails = positionService.getContrailByTraceIdAndDate(traceId, date);
        return DogResult.ok(dogContrails);
    }

    //定位信息列表
    @RequestMapping("/findDogsPositions")
    @ResponseBody
    public DogResult findDogsPosition(@RequestParam HashMap<String, Object> params){
        List<postionVO> dogsPosition = positionService.findDogsPosition(params);
        if(dogsPosition.size()>0){
            return DogResult.ok(dogsPosition);
        }
        return DogResult.build(400,"请求无数据");
    }

    @RequestMapping("/findFenceList")
    @ResponseBody
    public DogResult findFenceList(@RequestParam HashMap<String, Object> params){
        List<fenceDetailVO> fenceList = deviceFenceService.findFenceList(params);
        if(fenceList.size()>0){
            return DogResult.ok(fenceList);
        }
        return DogResult.build(400,"请求无数据");
    }

    @ResponseBody
    @PostMapping("/selectDogInfosByRange")
    public List<DogRecordInfo> selectDogInfosByRange(@RequestParam Map<String,Object> map){
        UserDO user = getUser();
        Long orgId = user.getDeptId();
//        map.put("lng",100.123);
//        map.put("lat",37.456);
        List<OrgInfo> orgInfos = orgInfoService.orgList();
        List<Integer>  orgIds = OrgUtils.orgReverse(orgInfos, Integer.parseInt(orgId.toString()), new ArrayList<>());
        map.put("orgIds",orgIds);
        List<DogRecordInfo> records = positionService.selectDogInfosByRange(map);
        return records;
    }



}

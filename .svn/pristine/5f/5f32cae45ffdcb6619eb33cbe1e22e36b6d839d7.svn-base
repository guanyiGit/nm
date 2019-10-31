package com.basicInfo.controller;

import com.basicInfo.service.ProtectorService;
import com.basicInfo.vo.ProtectorVO;
import com.dogmanager.utils.DogResult;
import com.entities.TProtector;
import com.entities.TSysDict;
import com.sys.controller.BaseController;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/biz/protector")
public class ProtectorController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(ProtectorController.class);

    @Autowired
    private ProtectorService protectorService;


    /**
     * 跳转到添加防疫员页面
     *
     * @return
     */
    @RequestMapping("/protector_Add")
    @RequiresPermissions("/biz/protector/protector_Add")
    String protector_Add() {

        return "basicInfo/protector/protector_Add";
    }

    /**
     * 新增防疫员
     */
    @RequestMapping("/addProtector")
    @RequiresPermissions("/biz/protector/protector_Add")
    @ResponseBody
    public R addProtector(TProtector protector) {

        Integer id = protectorService.addProtector(protector);
        if (id != 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            return R.ok(map);
        }
        return R.error();
    }

    /**
     * 跳转到防疫员列表页面
     *
     * @return
     */
    @RequestMapping("/protector_List")
    @RequiresPermissions("/biz/protector/protector_List")
    String protectorList(Model model) {
    		model.addAttribute("user",getUser());
        return "basicInfo/protector/protector_List";
    }

    /**
     * 防疫员列表
     *
     * @param index:索引 pageSize:页面大小
     *                 orgId:当前登录人部门id
     *                 searchKey:搜索条件
     */
    @ResponseBody
    @GetMapping("/findProtectorList")
    @RequiresPermissions("/biz/protector/protector_List")
    public PageUtils findProtectorList(Integer index, Integer pageSize, Integer orgId, String searchKey) {
        PageUtils pageUtils = null;
        try {
            pageUtils = protectorService.findProtectorList(index, pageSize, orgId, searchKey);
        } catch (Exception e) {
            logger.warn("查看防疫员列表失败!");
        }
        return pageUtils;
    }


    /**
     * 防疫员详情
     *
     * @param id 兽医id
     */
    @GetMapping("/findProtectorDetail")
    @RequiresPermissions("/biz/protector/findProtectorDetail")
    public String findProtectorDetail(Integer id, Model model) {
        ProtectorVO protectorVO = protectorService.findProtectorDetail(id);
        model.addAttribute("protectorVO", protectorVO);
        return "basicInfo/protector/protector_Detail";
    }

    /**
     * 根据用户id查看防疫员详情
     *
     * @param userId 用户id
     */
    @GetMapping("/wxfindProtectorDetail")
    @ResponseBody
    public DogResult wxfindProtectorDetail(Integer userId) {
        try {
            Integer id = protectorService.findProtectorDetailByUserId(userId);
            ProtectorVO protectorVO = protectorService.findProtectorDetail(id);
            return DogResult.ok(protectorVO);
        } catch (Exception e) {
            logger.warn("查询防疫员id失败!");
            return DogResult.build(400,"查询防疫员id失败");
        }
    }

    /**
     * 删除防疫员
     */
    @RequestMapping("/deleteProtector")
    @RequiresPermissions("/biz/protector/deleteProtector")
    @ResponseBody
    public R deleteProtector(Integer id) {
        if (protectorService.deleteProtector(id) > 0) {
            return R.ok();
        }
        return R.error();
    }


    /**
     * 跳转到修改防疫员界面
     */
    @RequestMapping("/toUpdatePage")
    @RequiresPermissions("/biz/protector/toUpdatePage")
    public String toUpdatePage(Integer id, Model model) {
        ProtectorVO protectorVO = protectorService.findProtectorDetail(id);
        List<TSysDict> nationList = protectorService.selectEthnic();
        List<TSysDict> edBaList = protectorService.selectEducationBackground();
        for (TSysDict n : nationList) {
            if (n.getName().equals(protectorVO.getNation())) {
                n.setRemarks("checked");
            }
        }
        for(TSysDict n:edBaList) {
            if(n.getName().equals(protectorVO.getDegreeOfEducation())) {
                n.setRemarks("checked");
            }
        }
        model.addAttribute("protectorVO", protectorVO);
        model.addAttribute("nationList", nationList);
        model.addAttribute("edBaList", edBaList);
        return "basicInfo/protector/protector_Update";
    }

    /**
     * 修改防疫员
     */
    @RequestMapping("/updateProtector")
    @RequiresPermissions("/biz/protector/toUpdatePage")
    @ResponseBody
    public R updateProtector(TProtector protector) {
        try {
            int i=protectorService.updateProtector(protector);
            Map<String,Object>map=new HashMap<>();
            map.put("i",i);
            return R.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 查找民族
     */
    @RequestMapping("/findTotalNation")
    @ResponseBody
    public List<TSysDict> findTotalNation() {

        List<TSysDict> list = null;
        try {
            list = protectorService.findTotalNation();
        } catch (Exception e) {
            logger.warn("查找民族失败");
        }
        return list;
    }
    /**
     * 查找民族
     */
    @RequestMapping("/selectEthnic")
    @ResponseBody
    public List<TSysDict> selectEthnic() {

        List<TSysDict> list = null;
        try {
            list = protectorService.selectEthnic();
        } catch (Exception e) {
            logger.warn("查找民族失败");
        }
        return list;
    }
    /**
     * 查找学历
     */
    @RequestMapping("/selectEducationBackground")
    @ResponseBody
    public List<TSysDict> selectEducationBackground() {

        List<TSysDict> list = null;
        try {
            list = protectorService.selectEducationBackground();
        } catch (Exception e) {
            logger.warn("查找学历失败");
        }
        return list;
    }

    @ResponseBody
    @GetMapping("initProtectorSel")
    List<Map<String, Object>> initProtectorSel(@RequestParam HashMap<String, Object> params) {
        return protectorService.initProtectorSel(params);
    }


    /**
     * 根据身份证或者电话号码进行唯一性判断
     *
     * @param cardNum:防疫员身份证号
     * @param phoneNum:防疫员电话  号码
     */
    @RequestMapping("/judugeExit")
    @ResponseBody
    public R judugeExit(String cardNum, String phoneNum) {
        if (protectorService.judugeExit(cardNum, phoneNum) == 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改时根据身份证或者电话号码进行唯一性判断
     *
     * @param id:主键id
     * @param cardNum:防疫员身份证号
     * @param phoneNum:防疫员电话  号码
     */
    @RequestMapping("/judugeUpdateExit")
    @ResponseBody
    public R judugeUpdateExit(Integer id, String cardNum, String phoneNum) {
        if (protectorService.judugeUpdateExit(id, cardNum, phoneNum) == 0) {
            return R.ok();
        }
        return R.error();
    }

    @RequestMapping("/initProtectorSel2")
    @ResponseBody
    public List<TProtector> initProtectorSel2(Map<String,Object> map) {
        Long deptId = ShiroUtils.getUser().getDeptId();
        return protectorService.initProtectorSel2(deptId);
    }

    /**
     * 电子围栏初始化 防疫员下拉框
     * @param map
     * @return
     */
    @ResponseBody
    @GetMapping("/initSelect")
    public List<TProtector> initSelect() {
        Long deptId = getUser().getDeptId();
        List<TProtector> list = protectorService.initProtectorSel2(deptId);
        list.stream().forEach(protector -> {
            protector.setExp(protector.getId().toString());
        });
        TProtector protector = new TProtector();
        protector.setExp("");
        protector.setName("全部");
        list.add(0,protector);
        return list;
    }

}

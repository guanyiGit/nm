package com.orgmanagement.controller;

import com.entities.OrgInfo;
import com.entities.Tree;
import com.orgmanagement.domain.DeptDO;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.OrgInfoService;
import com.sys.config.Constant;
import com.sys.controller.BaseController;
import com.utils.R;
import com.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 11:36:09
 */
@Controller
@RequestMapping("/biz/orgInfo")
public class OrgInfoController extends BaseController {



	private String prefix = "orgmanagement";
	
	@Autowired
	private OrgInfoService sysDeptService;

	@GetMapping()
//	@RequiresPermissions("system:sysDept:sysDept")
	String dept(Model model) {
	    model.addAttribute("user",getUser());
		return prefix + "/orgManagement";
	}

//	@ApiOperation(value="获取部门列表", notes="")
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("system:sysDept:sysDept")
	public List<DeptDO> list() {
		Map<String, Object> query = new HashMap<>(16);
        UserDO user = getUser();
        query.put("orgId",user.getDeptId());
        List<DeptDO> sysDeptList = sysDeptService.list(query);
		List<DeptDO> collect = sysDeptList.stream().filter(dept -> {
			return 0 != dept.getDeptId();
		}).collect(Collectors.toList());
		return collect;
	}

	@GetMapping("/add")
//	@RequiresPermissions("system:sysDept:add")
	String add(@RequestParam("pId") Long pId,@RequestParam("type") int type, Model model) {
		model.addAttribute("pId", pId);
		model.addAttribute("type", type);
		if (pId == 0) {
			model.addAttribute("pName", "总部门");
		} else {
			model.addAttribute("pName", sysDeptService.get(pId).getName());
		}
		UserDO user = ShiroUtils.getUser();
		model.addAttribute("user",user);
		return  prefix + "/addOrg";
	}

	@GetMapping("/edit/{deptId}")
//	@RequiresPermissions("system:sysDept:edit")
	String edit(@PathVariable("deptId") Long deptId, Model model) {
		DeptDO sysDept = sysDeptService.get(deptId);
		model.addAttribute("sysDept", sysDept);
		if(Constant.DEPT_ROOT_ID.equals(sysDept.getParentId())) {
			model.addAttribute("parentDeptName", "无");
		}else {
			DeptDO parDept = sysDeptService.get(sysDept.getParentId());
			model.addAttribute("parentDeptName", parDept.getName());
		}
		UserDO user = ShiroUtils.getUser();
		model.addAttribute("user",user);
		return  prefix + "/orgEdit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("system:sysDept:add")
	public R save(DeptDO sysDept) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (sysDeptService.save(sysDept) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
//	@RequiresPermissions("system:sysDept:edit")
	public R update(DeptDO sysDept) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (sysDeptService.update(sysDept) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
//	@RequiresPermissions("system:sysDept:remove")
	public R remove(Long deptId) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", deptId);
		if(sysDeptService.count(map)>0) {
			return R.error(2, "包含下级部门,不允许修改");
		}
		if(sysDeptService.checkDeptHasUser(deptId)) {
			if (sysDeptService.remove(deptId) > 0) {
				return R.ok();
			}
		}else {
			return R.error(3, "部门包含用户,不允许修改");

		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
//	@RequiresPermissions("system:sysDept:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] deptIds) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		sysDeptService.batchRemove(deptIds);
		return R.ok();
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<DeptDO> tree() {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree = sysDeptService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/deptTree";
	}

	@GetMapping("/initOrgSelect")
	@ResponseBody
	public List<Tree<OrgInfo>> initOrgSelect (@RequestParam Map<String, Object> params) {
		UserDO user = getUser();
        int roleType = user.getRoles().get(0).getType();
        params.put("orgId",user.getDeptId());
		params.put("deptPid",user.getPid());
		params.put("roleType",roleType);
		return sysDeptService.initOrgSelect(params);
	}

	/**
	 * 不包括本级组织
	 * by linchong
	 * @param params
	 * @return
	 */
	@GetMapping("/initOrgSelect3")
	@ResponseBody
	public List<Tree<OrgInfo>> initOrgSelect3 (@RequestParam Map<String, Object> params) {
		UserDO user = getUser();
		int roleType = user.getRoles().get(0).getType();
		params.put("orgId",user.getDeptId());
		params.put("deptPid",user.getPid());
		params.put("roleType",roleType);
		return sysDeptService.initOrgSelect3(params);
	}

    /**
     * 只返回本组织的直接下级组织
     * @return
     */
    @GetMapping("/initOrgSelect4")
    @ResponseBody
	public List<OrgInfo> initOrgSelect4() {
        Long deptId = ShiroUtils.getUser().getDeptId();
        return sysDeptService.initOrgSelect4(deptId);
    }


	@GetMapping("/initOrgSelect2")
	@ResponseBody
	//@RequiresPermissions("/biz/org/initOrgSelect2")
	public List<Tree<OrgInfo>> initOrgSelect () {
		return sysDeptService.initOrgSelect2();
	}
}

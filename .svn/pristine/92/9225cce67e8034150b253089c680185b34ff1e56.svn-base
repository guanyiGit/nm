package com.orgmanagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.annotation.Log;
import com.entities.Tree;
import com.orgmanagement.domain.DeptDO;
import com.orgmanagement.domain.RoleDO;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.domain.UserVO;
import com.orgmanagement.service.OrgInfoService;
import com.orgmanagement.service.RoleInfoService;
import com.orgmanagement.service.UserInfoService;
import com.orgmanagement.vo.UserSelVO;
import com.sys.config.Constant;
import com.sys.controller.BaseController;
import com.utils.*;
import com.utils.httpClient.HttpClientUtil;
import com.utils.httpClient.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 11:05:22
 */
 
@Controller
@RequestMapping("/biz/userInfo")
public class UserInfoController extends BaseController {
	@Autowired
	private UserInfoService userInfoService;

	@GetMapping("/initHandlerSel")
    @ResponseBody
	public List<UserSelVO> initHandlerSel() {
        List<UserSelVO> userSelList = userInfoService.userSelList();
        return userSelList;
    }
	private String prefix="orgmanagement"  ;
	@Autowired
	UserInfoService userService;
	@Autowired
	RoleInfoService roleService;
	@Autowired
    private  OrgInfoService orgInfoService;

	@Value("${sms.SMS_BASE_URL}")
	private String SMS_BASE_URL;

	@Value("${sms.type}")
	private String type;

//	@Autowired
//	DictService dictService;
	//	@RequiresPermissions("sys:user:user")
	@GetMapping("")
	String user(Model model) {
		return prefix + "/userManagement";
	}

	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
        UserDO user = getUser();
        int roleType = user.getRoles().get(0).getType();
        if(roleType!=1){//超级管理员角色

//            List<DeptDO> list = orgInfoService.lists();
//            List<DeptDO> depts = orgtills.getDeptIdsById(list, user.getDeptId(), new ArrayList<>());
//            List<Long> orgIds = depts.stream().map(dept -> {
//                return dept.getDeptId();
//            }).distinct().collect(Collectors.toList());
//
//            query.put("orgIds",orgIds);
            query.put("userIdCreate",user.getUserId());
        }
        List<UserDO> sysUserList = userService.pageList(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	//	@RequiresPermissions("sys:user:add")
	@Log("添加用户")
	@GetMapping("/add")
	String add(Model model) {
		List<RoleDO> roles = roleService.list();
		model.addAttribute("roles", roles);
		return prefix + "/userAdd";
	}

	//	@RequiresPermissions("sys:user:edit")
	@Log("编辑用户")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		UserDO userDO = userService.get(id);
		model.addAttribute("user", userDO);
		List<RoleDO> roles = roleService.list(id);
		model.addAttribute("roles", roles);
		return prefix+"/userEdit";
	}

	//	@RequiresPermissions("sys:user:add")
//	@Log("保存用户")
	@PostMapping("/save")
	@ResponseBody
	R save(UserDO user) throws Exception{
		user.setUsername(user.getUsername().trim());
		user.setUserIdCreate(getUserId());
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		String password = MathUtils.getRandomNumber();
		user.setPassword(MD5Utils.encrypt(user.getUsername(), password).trim());
		if (userService.save(user) > 0) {
			Map<String, Object> params = new HashMap<>();
			params.put("phone", user.getMobile());
			params.put("type",type);
			Map<String, Object> template_param = new HashMap<>();
			template_param.put("name", user.getName());
			template_param.put("username", user.getUsername());
			template_param.put("password", password);
			params.put("template_param", JSONObject.toJSONString(template_param));
			new Thread( () -> {
				try {
					HttpResult httpResult = HttpClientUtil.executeHttpParams(SMS_BASE_URL + "/SendSMS", "post", params);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} ).start();
			return R.ok();
		}
		return R.error();
	}

	//	@RequiresPermissions("sys:user:edit")
//	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	R update(UserDO user) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.update(user) > 0) {
			return R.ok();
		}
		return R.error();
	}


	//	@RequiresPermissions("sys:user:edit")
	@Log("更新用户")
	@PostMapping("/updatePeronal")
	@ResponseBody
	R updatePeronal(UserDO user) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.updatePersonal(user) > 0) {
			return R.ok();
		}
		return R.error();
	}


	//	@RequiresPermissions("sys:user:remove")
	@Log("删除用户")
	@PostMapping("/remove")
	@ResponseBody
	R remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	//	@RequiresPermissions("sys:user:batchRemove")
	@Log("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] userIds) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exit(params);
	}

	//	@RequiresPermissions("sys:user:resetPwd")
	@Log("请求更改用户密码")
	@GetMapping("/resetPwd/{id}")
	String resetPwd(@PathVariable("id") Long userId, Model model) {

		UserDO userDO = new UserDO();
		userDO.setUserId(userId);
		model.addAttribute("user", userDO);
		return prefix + "/reset_pwd";
	}

	@Log("提交更改用户密码")
	@PostMapping("/resetPwd")
	@ResponseBody
	R resetPwd(UserVO userVO) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{
			userService.resetPwd(userVO,getUser());
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}

	//	@RequiresPermissions("sys:user:resetPwd")
	@Log("admin提交更改用户密码")
	@PostMapping("/adminResetPwd")
	@ResponseBody
	R adminResetPwd(UserVO userVO) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{
			userService.adminResetPwd(userVO);
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}
	@GetMapping("/tree")
	@ResponseBody
	public Tree<DeptDO> tree() {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree = userService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/userTree";
	}

	@GetMapping("/personal")
	String personal(Model model) {
		UserDO userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
//		model.addAttribute("hobbyList",dictService.getHobbyList(userDO));
//		model.addAttribute("sexList",dictService.getSexList());
		return prefix + "/personal";
	}
	@ResponseBody
	@PostMapping("/uploadImg")
	R uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		Map<String, Object> result = new HashMap<>();
		try {
			result = userService.updatePersonalImg(file, avatar_data, getUserId());
		} catch (Exception e) {
			return R.error("更新图像失败！");
		}
		if(result!=null && result.size()>0){
			return R.ok(result);
		}else {
			return R.error("更新图像失败！");
		}
	}

	/**
	 * 检查账号是否存在
	 */
	@ResponseBody
	@RequestMapping("/checkAccountRepeat")
	public  R checkAccountRepeat(String account){
		try {
			Map<String, Object> map=new HashMap<>() ;
			map.put("i",userService.checkAccountRepeat(account));
			return R.ok(map);
		}catch (Exception e){
			return  R.error();
		}
	}

	
}

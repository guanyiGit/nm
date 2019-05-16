package com.sys.controller;

import com.entities.ResourceInfo;
import com.entities.Tree;
import com.orgmanagement.service.ResourceInfoService;
import com.statisanalysis.serivce.IHomePageService;
import com.utils.I18nConstant;
import com.utils.MD5Utils;
import com.utils.R;
import com.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ResourceInfoService resourceInfoService;
	@Autowired
	private IHomePageService homePageService;
//	@Autowired
//	FileService fileService;
//	@GetMapping({ "/", "" })
//	String welcome() {
//		return "redirect:/blog";
//	}

//	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model,HttpServletRequest request) {
		String l = request.getParameter("l");
		if(l == null) {
			l = "zh_CN";
		}
		model.addAttribute("lang",l);
		List<Tree<ResourceInfo>> menus = resourceInfoService.listMenuTree(Integer.parseInt(getUserId().toString()));
		Map<String,Object> map = new HashMap<>();
		map.put("id",1);
        Integer total = homePageService.getUnreadedTotal(map);
        model.addAttribute("total",total);
        model.addAttribute("menus", menus);
		model.addAttribute("user", getUser());


//		FileDO fileDO = fileService.get(getUser().getPicId());
//		if(fileDO!=null&&fileDO.getUrl()!=null){
//			if(fileService.isExist(fileDO.getUrl())){
//				model.addAttribute("picUrl",fileDO.getUrl());
//			}else {
//				model.addAttribute("picUrl","/img/photo_s.jpg");
//			}
//		}else {
//		}
//		model.addAttribute("picUrl","/img/photo_s.jpg");
//		model.addAttribute("username", getUser().getName());
		return "index";
	}

	@GetMapping("/login")
	String login(HttpServletRequest request,HttpServletResponse httpServletResponse,Model model) {
		httpServletResponse.setHeader("statusCode", "401");
		String l = request.getParameter("l");
		if(l == null) {
			//如果没有指定语言去cookie中查询语言
			Cookie[] cookies = request.getCookies();
			if(cookies != null && cookies.length > 0) {
				if(cookies != null && cookies.length > 0) {
					for (int i = 0; i < cookies.length; i++) {
						if("langType".equals(cookies[i].getName()) && (!"null".equals(cookies[i].getValue()))) {
							l = cookies[i].getValue();
						}
					}
				}
			}
		}
		if(l == null) {
			//cookie都没有就设置默认为中文
			l = "zh_CN";
		}

		model.addAttribute("lang",l);
		return "login";
	}

//	@Log("登录")
	@PostMapping("/wx/login")
	@ResponseBody
	R wxLogin(String username, String password, HttpServletResponse httpServletResponse) {
		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			String wxToken = subject.getSession().getId().toString();
			httpServletResponse.addHeader("token",wxToken);
			Map<String,Object> map = new HashMap<>();
			map.put("userInfo",getUser());
			return R.ok(map);
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}

	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password) {

		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			//设置默认语言信息为中文
			Session session = ShiroUtils.getSubjct().getSession();
			Object type = session.getAttribute("type");
			if(type ==null) {
				session.setAttribute("type", I18nConstant.I18N_ZH_CN);
			}
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}


	@GetMapping("/logout")
	String logout(Model model) {
		String l = "zh_CN";
		Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
		if(type != null) {
			if("2".equals(type.toString())) {
				l = "zh_BO";
			}else if ("3".equals(type.toString())) {
				l = "zh_MN";
			}
		}
		ShiroUtils.logout();
		model.addAttribute("lang",l);
		return "login";
	}

	@GetMapping("/main")
	String main() {
		return "epmanagement/manureDisposal";
//		return "main";
	}

}

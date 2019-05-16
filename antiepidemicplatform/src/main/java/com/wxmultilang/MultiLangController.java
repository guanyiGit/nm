package com.wxmultilang;

import com.utils.R;
import com.utils.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author linchong
 * @create 2019-04-22 14:13
 */
@Controller
@RequestMapping("/biz/mutliLang")
public class MultiLangController {

    @Autowired
    private ZhCNLangProp zhCNLangProp;
    @Autowired
    private ZhBOLangProp zhBOLangProp;
    @Autowired
    private ZhMNLangProp zhMNLangProp;

    private static final String LANG_KEY = "LANG";

    @RequestMapping("/getAllLangInfo")
    @ResponseBody
    public Map getAllLangInfo(HttpServletRequest request) {
        String lang = request.getParameter(LANG_KEY);
        LinkedHashMap<String, Object> langMap = new LinkedHashMap<>();
        if(StringUtils.isNotEmpty(lang)) {
            if("zh_MN".equals(lang)) {
                langMap.put("ZH_MN",zhMNLangProp);
            }else if("zh_BO".equals(lang)) {
                langMap.put("ZH_BO",zhBOLangProp);
            }else {
                langMap.put("ZH_CN",zhCNLangProp);
            }
        }else {
            langMap.put("ZH_CN", zhCNLangProp);
            langMap.put("ZH_BO", zhBOLangProp);
            langMap.put("ZH_MN", zhMNLangProp);
        }

        return R.ok().put(LANG_KEY, langMap);
    }

    @RequestMapping("/changLangInfo")
    @ResponseBody
    public R changLangInfo(HttpServletRequest request) {
        Session session = ShiroUtils.getSubjct().getSession();
        if(session == null) {
            return R.error("请先登录");
        }
        Locale locale = Locale.getDefault();
        String lang = request.getHeader("LANG");
        if(StringUtils.isNotEmpty(lang)) {
            String[] split = lang.split("_");
            locale = new Locale(split[0],split[1]);
            session.setAttribute("localLang", locale);
            if("ZH_BO".equals(lang)) {
                session.setAttribute("type",2);
            }else if("ZH_MN".equals(lang)) {
                session.setAttribute("type",3);
            }else {
                session.setAttribute("type",1);
            }
        }else {
            //其他情况默认为中文
            session.setAttribute("type",1);
            session.setAttribute("localLang", locale);
        }
        return R.ok();
    }

}

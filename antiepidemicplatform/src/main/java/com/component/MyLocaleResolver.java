package com.component;

import com.utils.I18nConstant;
import com.utils.ShiroUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * @author linchong
 * @create 2019-03-20 16:34
 */
public class MyLocaleResolver implements LocaleResolver {



    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {

        boolean flag = ShiroUtils.getSubjct().isAuthenticated();
        HttpSession session = httpServletRequest.getSession();
        Locale locale = Locale.getDefault();
        //如果点击了切换语言就切换
        String l = httpServletRequest.getParameter("l");
        //链接没有附带语言信息
        if(!StringUtils.isEmpty(l)) {
            return parseLocale(l, session,flag);
        }
        //从session获取语言信息
        if(flag) {      //flag==true => session还未销毁
            Object localLang = session.getAttribute("localLang");
            if(localLang != null){
                return (Locale)localLang;
            }
        }
        //从Cookie中获取语言信息
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                if("langType".equals(cookies[i].getName()) && (!"null".equals(cookies[i].getValue()))) {
                    //找到存储语言信息的cookie
                    String value = cookies[i].getValue();
                    return parseLocale(value,session,flag);
                }
            }
        }


        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }

    /**
     * 将语言标识转换成Locale对象
     * @param l
     * @param session
     * @return
     */
    public Locale parseLocale(String l,HttpSession session,boolean flag) {
        Locale locale = null;
        String[] split = l.split("_");
        locale = new Locale(split[0],split[1]);
        //如果退出了登录不用保存到session
        if(flag == false) {
            return locale;
        }
        //记录该语言信息在会话内有效
        session.setAttribute("localLang",locale);
        //定义type标识不同的语言
        if("BO".equals(split[1])) {
            //设置为藏文标识2
            session.setAttribute("type", I18nConstant.I18N_ZH_BO);
        }else if("MN".equals(split[1])) {
            //设置为蒙文标识3
            session.setAttribute("type",I18nConstant.I18N_ZH_MN);
        }else {
            //设置为中文标识1
            session.setAttribute("type",I18nConstant.I18N_ZH_CN);
        }
        return locale;
    }
}

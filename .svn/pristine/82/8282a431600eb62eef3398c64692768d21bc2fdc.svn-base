package com.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author linchong
 * @create 2019-04-11 16:25
 */
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //通过cookie将语言类型保存到浏览器
        String lang = httpServletRequest.getParameter("l");
        if(lang != null) {
            //将语言信息保存到cookie，设置过期时间为一周
            Cookie cookie = new Cookie("langType",lang);
            cookie.setMaxAge(30 * 24 * 60 * 60);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
        }
        ///~

        //小程序多语言
//        String wxLang = httpServletRequest.getHeader("LANG");
//        Session session = ShiroUtils.getSubjct().getSession();
//        if(StringUtils.isNotEmpty(wxLang)) {
//            session.setAttribute("wxLang",wxLang);
//        }else {
//            //从Session取语言信息
//            Object langObj = session.getAttribute("wxLang");
//            if(langObj != null) {
//                wxLang = langObj.toString();
//            }else {
//                //默认情况设置为中文
//                session.setAttribute("wxLang","zh_CN");
//                wxLang = "zh_CN";
//            }
//        }
//        if("zh_BO".equals(wxLang)) {
//            httpServletResponse.setHeader("langInfo", JSONObject.toJSONString(zhBOLangProp));
//        }else if("zh_MN".equals(wxLang)) {
//            httpServletResponse.setHeader("langInfo", JSONObject.toJSONString(zhMNLangProp));
//        }else {
//            //中文或未知文都是中文
//            httpServletResponse.setHeader("langInfo", JSONObject.toJSONString(zhCNLangProp));
//        }
//        System.out.println(zhCNLangProp);
//        System.out.println(zhBOLangProp);
        ///~
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}

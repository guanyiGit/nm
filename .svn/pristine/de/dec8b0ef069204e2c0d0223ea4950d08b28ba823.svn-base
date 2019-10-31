package com.sys.controller;

import com.orgmanagement.domain.UserDO;
import com.utils.ShiroUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


@Controller
    public class BaseController {
    public UserDO getUser() {
        return ShiroUtils.getUser();
    }

    public Long getUserId() {
        return getUser().getUserId();
    }

    public String getUsername() {
        return getUser().getUsername();
    }

        @InitBinder
    	public void initBinder(ServletRequestDataBinder binder){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    	}
    }

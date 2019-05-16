package com.soholy.dogmanager.controller.mail;

import com.soholy.dogmanager.pojo.Result;
import com.soholy.dogmanager.utils.mail.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class MailController {
	
	@Autowired
	private MailUtils mailUtils;
	
	/**
	 * 
	* @Description: 发送邮件
	* @param recipients  多个收件人格式<收件人姓名/收件人邮箱,收件人姓名/收件人邮箱 >
	* @param subject	
	* @param context	
	* @param pics	
	* @param fileDataSource  多个附件用“，”隔开
	* @return（展示方法参数和返回值）
	 */
	@RequestMapping(value="/SendMail",method= RequestMethod.POST)
	public Result SendMail(Map<String, Object> param){
		String recipients = (String) param.get("recipients");
		String subject = (String) param.get("subject");
		String context = (String) param.get("context");
		String pics = (String) param.get("pics");
		String fileDataSource = (String) param.get("fileDataSource");
		Result result = mailUtils.sendMail(recipients, subject, context, pics, fileDataSource);
		return result;
	}
	
	
}

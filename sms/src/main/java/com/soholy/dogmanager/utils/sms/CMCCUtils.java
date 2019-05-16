package com.soholy.dogmanager.utils.sms;

import com.alibaba.fastjson.JSONObject;
import com.soholy.dogmanager.pojo.Result;
import com.soholy.dogmanager.utils.httpclient.HttpClientUtil;
import com.soholy.dogmanager.utils.httpclient.HttpResult;
import org.apache.http.ParseException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * 
 * @ClassName: ModelSMSUtils
 * @Description: 移动短信接口
 * @author py
 * @date 2018年8月3日
 *
 */
public class CMCCUtils {

	private static Logger logger = Logger.getLogger(CMCCUtils.class);

	/** 用户名 */
	private String uid;
	/** 云信平台加密后的密码 */
	private String pwd;
	/** 平台url */
	private String url;
	/** 短信模板id */
	private String templateId;

	public Result sendSms(String phone, String username, String password) throws UnsupportedEncodingException {
		// 创建参数map
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("ac", "send");
		params.put("uid", this.getUid());
		params.put("pwd", pwd);
		params.put("mobile", phone);
		params.put("content", "{\"name\":\"" + username + "\",\"password\":\"" + password + "\"}");
		params.put("template", this.getTemplateId());
		HttpResult result = null;
		try {
			result = HttpClientUtil.executeHttpParams(this.url, "post", params);
			String content = result.getContent();
			JSONObject JsonContent = JSONObject.parseObject(content);
			String stat = (String) JsonContent.get("res_code");
			String response = getResponse(stat);
			return Result.build(Integer.parseInt(stat), response);

		} catch (ParseException e) {
			e.printStackTrace();
			return Result.build(400, "服务器异常,请稍后再试！！");
		} catch (IOException e) {
			e.printStackTrace();
			return Result.build(400, "服务器异常,请稍后再试！！");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return Result.build(400, "服务器异常,请稍后再试！！");
		}
	}

	/**
	 * 将返回状态编码转化为描述结果
	 * 
	 * @param logger
	 *            打印信息
	 * @param result
	 *            状态编码
	 * @return 描述结果
	 */
	public String getResponse(String result) {
		if (result.equals("100")) {
			logger.info("发送成功");
			return "发送成功";
		}
		if (result.equals("101")) {
			logger.info("验证失败");
			return "验证失败";
		}
		if (result.equals("102")) {
			logger.info("短信不足");
			return "短信不足";
		}
		if (result.equals("103")) {
			logger.info("操作失败");
			return "操作失败";
		}
		if (result.equals("104")) {
			logger.info("非法字符");
			return "非法字符";
		}
		if (result.equals("105")) {
			logger.info("内容过多");
			return "内容过多";
		}
		if (result.equals("106")) {
			logger.info("号码过多");
			return "号码过多";
		}

		if (result.equals("108")) {
			logger.info("号码内容空");
			return "号码内容空";
		}
		if (result.equals("109")) {
			logger.info("账号冻结");
			return "账号冻结";
		}

		if (result.equals("112")) {
			logger.info("号码不正确");
			return "号码不正确";
		}
		if (result.equals("117")) {
			logger.info("绑定IP不正确");
			return "绑定IP不正确";
		}
		if (result.equals("161")) {
			logger.info("未添加短信模板");
			return "未添加短信模板";
		}
		if (result.equals("162")) {
			logger.info("模板格式不正确");
			return "模板格式不正确";
		}
		if (result.equals("163")) {
			logger.info("模板ID不正确");
			return "模板ID不正确";
		}
		if (result.equals("164")) {
			logger.info("全文模板不匹配");
			return "全文模板不匹配";
		}
		return result;
	}

	/**
	 * 查询指定帐户剩余短信数量
	 * 
	 * @param uid
	 *            用户名
	 * @param password
	 *            用户密码
	 * @return
	 */
	public Result queryRemainingCount(String uid, String password) {

		// 创建参数map
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("ac", "number");
		params.put("uid", this.getUid());
		params.put("pwd", pwd);
		HttpResult result = null;
		try {
			result = HttpClientUtil.executeHttpParams(this.url, "post", params);
			String content = result.getContent();
			JSONObject JsonContent = JSONObject.parseObject(content);
			String stat = JsonContent.getString("stat");
			if (Integer.parseInt(stat) != 100) {
				// 获取失败
				return Result.build(Integer.parseInt(stat), content);
			} else {
				return Result.ok(content);
			}

		} catch (ParseException e) {
			e.printStackTrace();
			return Result.build(400, "服务器异常,请稍后再试！！");
		} catch (IOException e) {
			e.printStackTrace();
			return Result.build(400, "服务器异常,请稍后再试！！");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return Result.build(400, "服务器异常,请稍后再试！！");
		}
	}

	public static String getMd5(String value) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update((value).getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte b[] = md5.digest();
		int i;
		StringBuffer buf = new StringBuffer("");

		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0) {
				i += 256;
			}
			if (i < 16) {
				buf.append("0");
			}
			buf.append(Integer.toHexString(i));
		}
		String result = buf.toString();
		return result;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public CMCCUtils(String uid, String pwd, String url, String templateId) {
		super();
		this.uid = uid;
		this.pwd = pwd;
		this.url = url;
		this.templateId = templateId;
	}

	public CMCCUtils() {
		super();
	}
}

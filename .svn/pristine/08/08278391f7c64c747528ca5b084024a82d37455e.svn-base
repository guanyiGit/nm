package com.soholy.dogmanager.utils.sms;

import com.alibaba.fastjson.JSONObject;
import com.soholy.dogmanager.pojo.Result;
import com.soholy.dogmanager.utils.httpclient.HttpClientUtil;
import com.soholy.dogmanager.utils.httpclient.HttpResult;
import com.soholy.dogmanager.utils.httpclient.HttpsUtils;
import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: CTCCUtils
 * @Description: 中国电信短信接口
 * @author py
 * @date 2018年8月6日
 *
 */
public class CTCCUtils {

	private static final Logger logger = LoggerFactory.getLogger(CTCCUtils.class);

	// 应用ID------登录平台在应用设置可以找到
	private String APP_ID;
	// 应用secret-----登录平台在应用设置可以找到
	private String APP_SECRET;
	// 模板ID
	private String TEMPLATE_ID;

	public CTCCUtils() {
		super();
	}

	public CTCCUtils(String aPP_ID, String aPP_SECRET, String tEMPLATE_ID) {
		super();
		APP_ID = aPP_ID;
		APP_SECRET = aPP_SECRET;
		TEMPLATE_ID = tEMPLATE_ID;
	}

	/**
	 * @Description: 发送短信 @Title: sendSms @param tel 收信人手机号码 @param
	 *               template_param 模板参数 @return Result @throws
	 */
	public Result sendSms(String tel, String template_param) {
		String smsUrl = "http://api.189.cn/v2/emp/templateSms/sendSms";
		// 创建参数map
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("app_id", this.APP_ID);
		// 获取token
		String accessToken = getAccessToken();
		params.put("access_token", accessToken);
		params.put("acceptor_tel", tel);
		params.put("template_id", this.TEMPLATE_ID);
		params.put("template_param", template_param);
		// 时间戳，格式yyyy-MM-dd HH:mm:ss
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params.put("timestamp", format.format(new Date()));
		try {
			HttpResult result = HttpClientUtil.executeHttpParams(smsUrl, "post", params);
			String content = result.getContent();
			JSONObject JsonContent = JSONObject.parseObject(content);
			// 获取电信接口返回码
			String res_code = JsonContent.getString("res_code");
			System.out.println(res_code);
			if (Integer.parseInt(res_code) == 0) {
				return Result.ok(content);
			} else {
				// 后续添加
				return Result.build(Integer.parseInt(res_code), content);
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

	public Result querysmsstatus(String identifier) {
		String querySmsUrl = "http://api.189.cn/v2/EMP/nsagSms/appnotify/querysmsstatus";
		// 创建参数map
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("app_id", this.APP_ID);
		// 获取token
		String accessToken = getAccessToken();
		params.put("access_token", accessToken);
		params.put("identifier", identifier);
		// 时间戳，格式yyyy-MM-dd HH:mm:ss
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params.put("timestamp", format.format(new Date()));
		try {
			HttpResult result = HttpClientUtil.executeHttpParams(querySmsUrl, "post", params);
			// 获取接口返回内容并转换成json对象
			JSONObject JsonContent = JSONObject.parseObject(result.getContent());
			// 获取电信接口返回码
			String res_code = JsonContent.getString("res_code");
			if (Integer.parseInt(res_code) == 0) {
				return Result.ok(result.getContent());
			} else {
				// 后续添加
				return Result.build(Integer.parseInt(res_code), result.getContent());
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

	// 查询模板短信套餐余额查询
	public Result queryleft() {
		String queryleft = "http://api.189.cn/v2/emp/templateSms/pleft";
		// 创建参数map
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("app_id", this.APP_ID);
		// 获取token
		String accessToken = getAccessToken();
		
		params.put("access_token", accessToken);
		// 时间戳，格式yyyy-MM-dd HH:mm:ss
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params.put("timestamp", format.format(new Date()));
		try {
			HttpResult result = HttpClientUtil.executeHttpParams(queryleft, "post", params);
			// 获取接口返回内容并转换成json对象
			JSONObject JsonContent = JSONObject.parseObject(result.getContent());
			// 获取电信接口返回码
			String res_code = JsonContent.getString("res_code");
			if (Integer.parseInt(res_code) == 0) {
				return Result.ok(result.getContent());
			} else {
				// 后续添加
				return Result.build(Integer.parseInt(res_code), result.getContent());
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

	// 获取访问令牌 CC模式
	public String getAccessToken() {
		// 获取token接口地址
		String accessToken_url = "https://oauth.api.189.cn/emp/oauth2/v3/access_token";
		// 创建参数map
		Map<String, Object> params = new HashMap<String, Object>();
		// cc认证
		params.put("grant_type", "client_credentials");
		params.put("app_id", this.APP_ID);
		params.put("app_secret", this.APP_SECRET);
		try {
			JSONObject doPost = HttpsUtils.doPost(accessToken_url, params);
			String code = doPost.getString("res_code");
			if (Integer.parseInt(code) != 0) {
				throw new Exception();
			}
			return doPost.getString("access_token");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "服务器异常,请稍后再试！！";
		}
	}

	public String getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(String aPP_ID) {
		APP_ID = aPP_ID;
	}

	public String getAPP_SECRET() {
		return APP_SECRET;
	}

	public void setAPP_SECRET(String aPP_SECRET) {
		APP_SECRET = aPP_SECRET;
	}

	public String getTEMPLATE_ID() {
		return TEMPLATE_ID;
	}

	public void setTEMPLATE_ID(String tEMPLATE_ID) {
		TEMPLATE_ID = tEMPLATE_ID;
	}
}

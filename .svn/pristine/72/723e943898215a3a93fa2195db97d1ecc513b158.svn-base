package com.soholy.dogmanager.utils.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AliSMSUtils {

	public SendSmsResponse sendSms(String phone, String username, String password) throws ClientException {
		try {
			// 可自助调整超时时间
			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
			System.setProperty("sun.net.client.defaultReadTimeout", "10000");
			// 初始化acsClient,暂不支持region化
			DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
			DefaultProfile.addEndpoint(endpointName, regionId, product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);
			// 组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();
			// 必填:待发送手机号
			request.setPhoneNumbers(phone);
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName(sign);
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode(templateCode);
			// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${username},您的密码为${password}"时,此处的值为
			request.setTemplateParam("{\"name\":\"" + username + "\",\"password\":\"" + password + "\"}");
			// hint 此处可能会抛出异常，注意catch
			SendSmsResponse acsResponse = acsClient.getAcsResponse(request);
			return acsResponse;
		} catch (Exception e) {
			return null;
		}
	}

	public QuerySendDetailsResponse querySendDetails(String bizId, String phone) throws ClientException {
		try {
			// 可自助调整超时时间
			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
			System.setProperty("sun.net.client.defaultReadTimeout", "10000");

			// 初始化acsClient,暂不支持region化
			IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
			DefaultProfile.addEndpoint(endpointName, regionId, product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);
			// 组装请求对象
			QuerySendDetailsRequest request = new QuerySendDetailsRequest();
			// 必填-号码
			request.setPhoneNumber(phone);
			// 可选-流水号
			request.setBizId(bizId);
			// 必填-发送日期 支持30天内记录查询，格式yyyyMMdd
			SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
			request.setSendDate(ft.format(new Date()));
			// 必填-页大小
			request.setPageSize(10L);
			// 必填-当前页码从1开始计数
			request.setCurrentPage(1L);

			// hint 此处可能会抛出异常，注意catch
			QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
			return querySendDetailsResponse;
		} catch (Exception e) {
			return null;
		}
	}

	// 产品名称:云通信短信API产品,开发者无需替换
	static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	static final String domain = "dysmsapi.aliyuncs.com";

	static final String regionId = "cn-hangzhou";
	static final String endpointName = "cn-hangzhou";

	// 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	private String accessKeyId;

	private String accessKeySecret;
	// 短信签名-可在短信控制台中找到
	private String sign;
	// 短信模板-可在短信控制台中找到
	private String templateCode;

	public String getDomain() {
		return domain;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public AliSMSUtils(String accessKeyId, String accessKeySecret, String sign, String templateCode) {
		super();
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.sign = sign;
		this.templateCode = templateCode;
	}

	public AliSMSUtils() {
		super();
	}

}

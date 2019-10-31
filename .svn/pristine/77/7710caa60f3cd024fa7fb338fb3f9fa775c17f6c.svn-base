package com.soholy.dogmanager.utils.randCode;

import com.alibaba.fastjson.JSONObject;
import com.soholy.dogmanager.pojo.Result;
import com.soholy.dogmanager.utils.httpclient.HttpClientUtil;
import com.soholy.dogmanager.utils.httpclient.HttpResult;
import com.soholy.dogmanager.utils.httpclient.HttpsUtils;
import com.soholy.dogmanager.utils.sms.CTCCUtils;
import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CTCCRandcode {

	private static final Logger logger = LoggerFactory.getLogger(CTCCUtils.class);

	// 应用ID------登录平台在应用设置可以找到
	private String APP_ID;
	// 应用secret-----登录平台在应用设置可以找到
	private String APP_SECRET;
	
	private Integer expTime; 
	
	// 电信随机验证码下发
	public  Result TelecomCodeSend(String phone, String noticeUrl) {
		String send_url = "http://api.189.cn/v2/dm/randcode/send";
		// 创建获取sign Map
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		paramsMap.put("access_token", this.getAccessToken());
		paramsMap.put("app_id", this.APP_ID);
		paramsMap.put("phone", phone);
		// 时间戳，格式yyyy-MM-dd HH:mm:ss
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		paramsMap.put("timestamp", format.format(new Date()));
		paramsMap.put("token", getToken());
		paramsMap.put("url", noticeUrl);
		String sign = getSign(paramsMap);
		paramsMap.put("sign", sign);
		try {
			HttpResult result = HttpClientUtil.executeHttpParams(send_url, "post", paramsMap);
			// 获取电信接口返回值
			return Result.ok(result.getContent());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return Result.build(400, "服务器异常，请稍后再试！！！");
	}

	/**
	 * @Description:自定义短信验证码下发
	 * @param phone
	 * @param randCode
	 * @param expTime
	 * @return（展示方法参数和返回值）
	 */
	public Result customCodeSend(String phone, String randCode) {
		String send_url = "http://api.189.cn/v2/dm/randcode/sendSms";
		// 创建获取sign Map
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		paramsMap.put("access_token", this.getAccessToken());
		paramsMap.put("app_id", this.APP_ID);
		paramsMap.put("exp_time", expTime+"");
		paramsMap.put("phone", phone);
		paramsMap.put("randcode", randCode);
		// 时间戳，格式yyyy-MM-dd HH:mm:ss
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		paramsMap.put("timestamp", format.format(new Date()));
		paramsMap.put("token", getToken());
		String sign = getSign(paramsMap);
		paramsMap.put("sign", sign);
		try {
			HttpResult result = HttpClientUtil.executeHttpParams(send_url, "post", paramsMap);
			// 获取电信接口返回值
			return Result.ok(result.getContent());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Result.build(400, "服务器异常，请稍后再试！！！");
	}

	// 获取信任码token
	public String getToken() {
		String token_url = "http://api.189.cn/v2/dm/randcode/token";
		// 创建获取sign Map
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		paramsMap.put("access_token", this.getAccessToken());
		paramsMap.put("app_id", this.APP_ID);
		// 时间戳，格式yyyy-MM-dd HH:mm:ss
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		paramsMap.put("timestamp", format.format(new Date()));
		String sign = getSign(paramsMap);
		paramsMap.put("sign", sign);
		try {
			HttpResult result = HttpClientUtil.executeHttpParams(token_url, "get", paramsMap);
			// 获取电信接口返回值
			JSONObject JsonContent = JSONObject.parseObject(result.getContent());
			if (Integer.parseInt(JsonContent.getString("res_code")) == 0) {
				return JsonContent.getString("token");
			} else {
				return JsonContent.getString("res_message");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取Sign
	public String getSign(TreeMap<String, String> paramsMap) {
		try {
			String sign = "";
			for (Iterator iterator = paramsMap.keySet().iterator(); iterator.hasNext();) {
				String pName = (String) iterator.next();
				sign = (new StringBuilder(String.valueOf(sign))).append("&").append(pName).append("=")
						.append((String) paramsMap.get(pName)).toString();
			}
			byte[] hmacSHA1Encrypt = hmacSHA1Encrypt(sign.substring(1), this.APP_SECRET);
			sign = encryptBASE64(hmacSHA1Encrypt);
			return sign;
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return null;
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

	public CTCCRandcode(String aPP_ID, String aPP_SECRET) {
		super();
		APP_ID = aPP_ID;
		APP_SECRET = aPP_SECRET;
	}

	public Integer getExpTime() {
		return expTime;
	}

	public void setExpTime(Integer expTime) {
		this.expTime = expTime;
	}

	public CTCCRandcode() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static byte[] hmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
		String MAC_NAME = "HmacSHA1";
		String ENCODING = "UTF-8";
		byte data[] = encryptKey.getBytes(ENCODING);
		javax.crypto.SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
		Mac mac = Mac.getInstance(MAC_NAME);
		mac.init(secretKey);
		byte text[] = encryptText.getBytes(ENCODING);
		return mac.doFinal(text);
	}

	private static String encryptBASE64(byte key[]) throws Exception {
		return new String(base64Encode(key));
	}

	private static String base64Encode(byte data[]) {
		StringBuffer sb = new StringBuffer();
		int len = data.length;
		for (int i = 0; i < len;) {
			int b1 = data[i++] & 255;
			if (i == len) {
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[(b1 & 3) << 4]);
				sb.append("==");
				break;
			}
			int b2 = data[i++] & 255;
			if (i == len) {
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[(b1 & 3) << 4 | (b2 & 240) >>> 4]);
				sb.append(base64EncodeChars[(b2 & 15) << 2]);
				sb.append("=");
				break;
			}
			int b3 = data[i++] & 255;
			sb.append(base64EncodeChars[b1 >>> 2]);
			sb.append(base64EncodeChars[(b1 & 3) << 4 | (b2 & 240) >>> 4]);
			sb.append(base64EncodeChars[(b2 & 15) << 2 | (b3 & 192) >>> 6]);
			sb.append(base64EncodeChars[b3 & 63]);
		}

		return sb.toString();
	}

	private static char base64EncodeChars[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9', '+', '/' };

}

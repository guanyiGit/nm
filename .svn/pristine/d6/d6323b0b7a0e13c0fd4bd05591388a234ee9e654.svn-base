package com.soholy.dogmanager.controller.sms;

import com.soholy.dogmanager.pojo.Result;
import com.soholy.dogmanager.service.jedis.impl.JedisClientPool;
import com.soholy.dogmanager.utils.common.MathUtils;
import com.soholy.dogmanager.utils.randCode.CTCCRandcode;
import com.soholy.dogmanager.utils.sms.CTCCUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/sms")
public class SMSController {

	private static Logger LOGGER = LoggerFactory.getLogger(SMSController.class);

	@Resource(name = "passWordSMS")
	private CTCCUtils passWordSMS; // 发送密码

	@Resource(name = "forbidSMS")
	private CTCCUtils forbidSMS; // 犬只围栏黑名单短信

	@Resource(name = "forbidSMS")
	private CTCCUtils batterySMS; // 发送电量不足

	@Resource(name = "blacklistSMS")
	private CTCCUtils blacklistSMS; // 犬主黑名单模板

	@Resource(name = "whitelistSMS")
	private CTCCUtils whitelistSMS; // 犬只围栏白名单模板
	
	@Resource(name = "violationSMS")
	private CTCCUtils violationSMS; // 犬只违规短信模板
	
	@Resource(name = "ctccRandcode")
	private CTCCRandcode ctccRandcode;
	
	@Autowired
	private JedisClientPool jedisClientPool;

	@RequestMapping(value = "/SendRandCode")
	@ResponseBody
	public Result SendRandCode(String clientIpAddress,String phone) {
		// 组合key
		String key = clientIpAddress + phone+"SendRandCode";
		if (jedisClientPool.exists(key)) {
			return Result.build(401, "已发送验证码，请不要重复发送");
		} else {
			// 获取验证码
			String randCode = MathUtils.getRandomNumber();
			// 发送短信
			Result result = ctccRandcode.customCodeSend(phone, randCode);
			if (result.getStatus() == 200) {
				// 将用户ip+phone以及验证码存入redis
				jedisClientPool.set(key, randCode);
				// 设置key过期时间
				jedisClientPool.expire(key,  60);
				return result;
			}
			return Result.ok();
		}
	}
	

	
	@RequestMapping(value = "/CheckRandCode", method = RequestMethod.POST)
	@ResponseBody
	public Result CheckRandCode(String clientIpAddress,String phone,String randCode) {
		// 组合key
		String key = clientIpAddress + phone+"SendRandCode";
		
		if (jedisClientPool.exists(key)) {
			// 获取redis中的二维码
			String randcode = jedisClientPool.get(key);
			// 如果上传的验证码和redis中的一样，则验证通过
			if (randCode.equals(randcode)) {
				return Result.ok();
			} else {
				return Result.build(400, "验证码不正确！！！");
			}
		}
		return Result.build(400, "验证码不正确！！！");
	}

	/**
	 * @Description:发送密码模板短信
	 * @param request
	 * @param phone
	 *            电话
	 * @param param
	 *            模板短信参数 @return（展示方法参数和返回值）
	 */
	@RequestMapping(value = "/PassWordSMS", method = RequestMethod.POST)
	@ResponseBody
	public Result PassWordSMS(String phone,String template_param) {
		// 组合key
		String key = phone+"PassWordSMS";
		
		if (jedisClientPool.exists(key)) {
			return Result.ok("已发送验证码，请不要重复发送");
		} else {
			// 发送短信
			Result result = passWordSMS.sendSms(phone, template_param);
			if (result.getStatus() == 200) {
				// 将用户ip+phone以及验证码存入redis
				jedisClientPool.set(key, key);
				// 设置key过期时间
				jedisClientPool.expire(key, 20 * 60);
				return result;
			}
			return Result.ok();
		}
	};

	/**
	 * @Description:发送禁养通知模板
	 * @param request
	 * @param phone
	 * @param param
	 * @return（展示方法参数和返回值）
	 */
	@RequestMapping(value = "/ForbidSMS", method = RequestMethod.POST)
	@ResponseBody
	public Result ForbidSMS(String phone,String template_param) {
		
		// 组合key
		String key =  phone+"ForbidSMS";
		
		if (jedisClientPool.exists(key)) {
			return Result.ok("已发送验证码，请不要重复发送");
		} else {
			
			// 发送短信
			Result result = forbidSMS.sendSms(phone, template_param);
			if (result.getStatus() == 200) {
				// 将用户ip+phone以及验证码存入redis
				jedisClientPool.set(key, key);
				// 设置key过期时间
				jedisClientPool.expire(key, 20 * 60);
				return result;
			}
			return Result.ok();
		}
	};

	/**
	 * @Description:电量不足短信提醒
	 * @param request
	 * @param phone
	 * @param param
	 * @return（展示方法参数和返回值）
	 */
	@RequestMapping(value = "/BatterySMS", method = RequestMethod.POST)
	@ResponseBody
	public Result BatterySMS(String phone,String template_param) {
		
		// 组合key
		String key = phone+"BatterySMS";
		// 获取模板参数
		if (jedisClientPool.exists(key)) {
			return Result.ok("已发送验证码，请不要重复发送");
		} else {
			
			// 发送短信
			Result result = batterySMS.sendSms(phone, template_param);
			if (result.getStatus() == 200) {
				// 将用户ip+phone以及验证码存入redis
				jedisClientPool.set(key, key);
				// 设置key过期时间
				jedisClientPool.expire(key, 20 * 60);
				return result;
			}
			return Result.build(400, "已发送验证码，请不要重复发送");
		}
	};

	/**
	 * @Description:黑名单短信提醒
	 * @param request
	 * @param phone
	 * @param param
	 * @return（展示方法参数和返回值）
	 */
	@RequestMapping(value = "/BlacklistSMS", method = RequestMethod.POST)
	@ResponseBody
	public Result BlacklistSMS(String phone,String template_param) {
		
		// 组合key
		String key = phone+"BlacklistSMS";
		
		if (jedisClientPool.exists(key)) {
			return Result.ok("已发送验证码，请不要重复发送");
		} else {
			// 获取验证码
			String randCode = MathUtils.getRandomNumber();
			// 发送短信
			Result result = blacklistSMS.sendSms(phone, template_param);
			// 将用户ip+phone以及验证码存入redis
			jedisClientPool.set(key, randCode);
			// 设置key过期时间
			jedisClientPool.expire(key, 20 * 60);
			return result;
		}
	};

	@RequestMapping(value = "/WhitelistSMS", method = RequestMethod.POST)
	@ResponseBody
	public Result WhitelistSMS(String phone,String template_param) {
		// 组合key
		String key =phone+"WhitelistSMS";
		if (jedisClientPool.exists(key)) {
			return Result.ok("已发送验证码，请不要重复发送");
		} else {
			// 获取验证码
			String randCode = MathUtils.getRandomNumber();
			// 发送短信
			Result result = whitelistSMS.sendSms(phone, template_param);
			if (result.getStatus() == 200) {
				// 将用户ip+phone以及验证码存入redis
				jedisClientPool.set(key, randCode);
				// 设置key过期时间
				jedisClientPool.expire(key, 20 * 60);
				return result;
			}
			return Result.ok();
		}
	};
	
	@RequestMapping(value = "/ViolationSMS", method = RequestMethod.POST)
	@ResponseBody
	public Result ViolationSMS(String phone,String template_param) {
		// 组合key
		String key = phone+"ViolationSMS";
		
		if (jedisClientPool.exists(key)) {
			return Result.ok("已发送验证码，请不要重复发送");
		} else {
			// 获取验证码
			String randCode = MathUtils.getRandomNumber();
			// 发送短信
			Result result = violationSMS.sendSms(phone, template_param);
			if (result.getStatus() == 200) {
				// 将用户ip+phone以及验证码存入redis
				jedisClientPool.set(key, randCode);
				// 设置key过期时间
				jedisClientPool.expire(key, 20 * 60);
				return result;
			}
			return Result.ok();
		}
	};
	
}

package com.soholy.dogmanager.utils.common;

import javax.servlet.http.HttpServletRequest;

public class GetClientIpAddress {
	
	/**
	* @Description:获取客户端访问ip
	* @param request
	* @return（展示方法参数和返回值）
	 */
	public static String getClientIpAddress(HttpServletRequest request) {
		String clientIp = request.getHeader("x-forwarded-for");
		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("Proxy-Client-IP");
		}
		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("WL-Proxy-Client-IP");
		}
		if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getRemoteAddr();
		}
		return clientIp;
	}
	
}

package com.utils;

import java.util.Random;

public class MathUtils {
	
	/**
	* 获取随机码
	* @Title: getRandomNumber  
	* @param @return    
	* @return String     
	* @throws
	 */
	public static String getRandomNumber(){
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result+=random.nextInt(10);
		}
		return result;
	}
	
	/**
	* @Description:取两位小数
	* @param data
	* @return（展示方法参数和返回值）
	 */
	public static Double getRound(Double data){
		String format = String.format("%.2f", data);
		return Double.valueOf(format);
	}
	
	
}

package com.soholy.dogmanager.utils.common;

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
	
	
}

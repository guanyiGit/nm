/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: KeyUtil
 * Author:   Administrator
 * Date:     2018/11/23 10:18
 * Description: 生成唯一的字符串
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 〈一句话功能简述〉<br> 
 * 〈生成唯一的字符串〉
 *
 * @author Administrator
 * @create 2018/11/23
 * @since 1.0.0
 */
public class KeyUtil {
    public static  String getUniqueKey(){
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        synchronized(KeyUtil.class){
            for(int i=0;i<10;i++ ) {
                sb.append(random.nextInt(10));
            }
        }
        String[] strNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString().split("-");
        String YY=strNow[0].substring(strNow[0].length()-2);
        return YY+""+strNow[1]+""+strNow[2]+sb.toString();
    }

    public static void main(String[] args) throws  Exception{

        System.out.println(KeyUtil.getUniqueKey());
    }

}

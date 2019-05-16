package com.sys.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateConverter  implements Converter<String,Date> {

    public Date convert(String source) {
        try {
            //1. 定义日期格式
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //2. 解析日期
            Date date = format.parse(source);

            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


}

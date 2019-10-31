package com.soholy.converters;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class GlobleDateConverter implements Converter<String, Date> {

    // 添加不同的日期格式
    private static List<String> formats = new ArrayList<String>();

    static {
        formats.add("yyyy");
        formats.add("yyyy-MM");
        formats.add("yyyy-MM-dd");
        formats.add("yyyy-MM-dd HH:mm");
        formats.add("yyyy-MM-dd HH:mm:ss");
        formats.add("yyyy/MM");
        formats.add("yyyy/MM/dd");
        formats.add("yyyy/MM/dd HH:mm");
        formats.add("yyyy/MM/dd HH:mm:ss");
        formats.add("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    public static void main(String[] args) throws ParseException {
        String str = "2018-10-23T05:00:24.091Z";
        SimpleDateFormat sdf = new SimpleDateFormat(formats.get(9));
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = sdf.parse(str);
        date = new SimpleDateFormat(formats.get(9)).parse(str);
        sdf.format(date);
        sdf = new SimpleDateFormat(formats.get(4));
        System.out.println(date);
    }

    @Override
    public Date convert(String source) {

        try {
            if (source.matches("^\\d{4}$")) {// 2017
                return parseDate(source, formats.get(0), null);
            } else if (source.matches("^\\d{4}-\\d{1,2}$")) {// 2017-09
                return parseDate(source, formats.get(1), null);
            } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {// 2017-09-10
                return parseDate(source, formats.get(2), null);
            } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {// 2017-09-10 21:15
                return parseDate(source, formats.get(3), null);
            } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {// 2017-09-10 12:12:12
                return parseDate(source, formats.get(4), null);
            } else if (source.matches("^\\d{4}/\\d{1,2}$")) {// 2017/09
                return parseDate(source, formats.get(5), null);
            } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2}$")) {// 2017/09/10
                return parseDate(source, formats.get(6), null);
            } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {// 2017/09/10 21:15
                return parseDate(source, formats.get(7), null);
            } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {// 2017/09/10
                return parseDate(source, formats.get(8), null);
            } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}Z$")) {// 2017/09/10
                return parseDate(source, formats.get(9), TimeZone.getTimeZone("GMT"));
            }
//            else if (source != null) {
//                throw new RuntimeException("在springmvc自定义全局日期转换器,没有相对应的日期格式与传入的日期相匹配!!!");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述：格式化日期
     *
     * @param dateStr  String 字符型日期
     * @param format   String 格式
     * @param timeZone 时区，默认 ‘GMT+8’
     * @return Date 日期
     */
    public Date parseDate(String dateStr, String format, TimeZone timeZone) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        if (timeZone != null)
            dateFormat.setTimeZone(timeZone);
        dateFormat.setLenient(false);// 指定日期/时间解析为不严格
        try {
            date = (Date) dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return date;
    }

}

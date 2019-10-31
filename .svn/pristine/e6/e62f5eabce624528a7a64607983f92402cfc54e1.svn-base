package com.statisanalysis.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: linchong
 * @Date: 2018/9/30 12:45
 * @Version 1.0
 */
public class DateUtils {

//    public static void main(String[] args) throws ParseException {
//        daysOfTwo_1();
//    }

    public static long daysOfTwo_1(String beginDate,String endDate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //跨年不会出现问题
        //如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0
        Date fDate=sdf.parse(beginDate);
        Date oDate=sdf.parse(endDate);
        long days=(oDate.getTime()-fDate.getTime())/(1000*3600*24);
//        System.out.print(days);
        return days;
    }

    /**
     * @param dateStr
     * @param addYear
     * @param addMonth
     * @param addDate
     * @return
     * @throws Exception
     */
    public static String getLastMonth(String dateStr, int addYear, int addMonth, int addDate) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date sourceDate = sdf.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sourceDate);
            cal.add(Calendar.YEAR, addYear);
            cal.add(Calendar.MONTH, addMonth);
            cal.add(Calendar.DATE, addDate);
            SimpleDateFormat returnSdf = new SimpleDateFormat("yyyy-MM");
            String dateTmp = returnSdf.format(cal.getTime());
            Date returnDate = returnSdf.parse(dateTmp);
            return dateTmp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 获取起始日期之间的所有月份
     * @param minDate
     * @param maxDate
     * @return
     * @throws Exception
     */
    public static List<String> getMonthBetween(String minDate, String maxDate) throws Exception {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    /**
     * 根据yyyy-MM格式的日期字符串获得对应的数值
     * 数值计算公式：year*100+month
     * @param dateStr
     * @return
     */
    public static int getNumOfDate(String dateStr) {
        String[] strings = dateStr.split("-");
        int result = Integer.parseInt(strings[0])*100+Integer.parseInt(strings[1]);
        return result;
    }

//    public static void main(String[] args) throws Exception {
//        int numOfDate = getNumOfDate("2018-30");
//        System.out.println("numOfDate:"+numOfDate);
//    }

    //获取startTime,endTime,nextMonth
    public static List<String> getTimes(Map<String,Object> params) throws Exception {
        List<String> times = new ArrayList<>();
        String endTime = "";
        String startTime = "";
        String nextMonth = "";
        String startEndTime = params.get("startEndTime").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        if(startEndTime == null || "".equals(startEndTime)) {
            //设置查询近一年的数据
            endTime = sdf.format(new Date());       //当前时间的月份
            startTime = DateUtils.getLastMonth(endTime,0,-11,0);     //一年前的月份
        }else {
            String[] split = startEndTime.split(" - ");
            startTime = split[0];
            endTime = split[1];
        }
        nextMonth = DateUtils.getLastMonth(endTime, 0, 1, 0);
        times.add(startTime);
        times.add(endTime);
        times.add(nextMonth);
        return times;
    }

}

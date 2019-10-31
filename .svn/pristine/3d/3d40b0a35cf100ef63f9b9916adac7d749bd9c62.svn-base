package atshunhengli.com.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 获取当前日期的前一天
     * @param date 当前日期
     * @return
     */
    public static Date getAfterDate(Date date) {
        Date afterDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, +1);
        afterDate = calendar.getTime();
        return afterDate;
    }

    /**
     * 获取当前日期的前一天
     * 
     * @param date 当前日期
     * @return
     */
    public static Date getBeforeDate(Date date) {
        Date beforeDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        beforeDate = calendar.getTime();
        return beforeDate;
    }

    /**
     * 获取当前日期的前一个星期
     * 
     * @param date
     * @return
     */
    public static Date getBeforeWeek(Date date) {
        Date beforeWeek = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_WEEK, -7);
        beforeWeek = calendar.getTime();
        return beforeWeek;
    }

    /**
     * 获取当前日期前一个月的日期
     * 
     * @param date
     * @return
     */
    public static Date getBeforeMonth(Date date) {
        Date beforeMonth = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        beforeMonth = calendar.getTime();
        return beforeMonth;
    }

    /**
     * 获取某月前一个月第一天
     * 
     * @param date
     * @return
     */
    public static Date getMonthfirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        // 获取当前时间的年月
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        // 设置年份
        calendar.set(Calendar.YEAR, year);
        // 设置月份
        calendar.set(Calendar.MONTH, month - 1);
        // 获取某月最小一天
        int MonthOffirstDate = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份最小的天数
        calendar.set(Calendar.DAY_OF_MONTH, MonthOffirstDate);
        Date MonthfirstDay = calendar.getTime();
        return MonthfirstDay;
    }

    /**
     * 获取某月前一个月最后一天
     * 
     * @param date
     * @return
     */
    public static Date getMonthlastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        // 获取当前时间的年月
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        // 设置年份
        calendar.set(Calendar.YEAR, year);
        // 设置月份
        calendar.set(Calendar.MONTH, month - 1);
        // 获取某月最小一天
        int MonthOflastDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份最小的天数
        calendar.set(Calendar.DAY_OF_MONTH, MonthOflastDate);
        Date MonthlastDate = calendar.getTime();
        return MonthlastDate;
    }
}

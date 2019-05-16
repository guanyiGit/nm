package com.soholy.cb.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.springframework.core.convert.converter.Converter;

public class GlobleDateConverter extends Object implements Converter<String, Date> {
  private static List<String> formats = new ArrayList();
  
  static  {
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
    SimpleDateFormat sdf = new SimpleDateFormat((String)formats.get(9));
    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    Date date = sdf.parse(str);
    date = (new SimpleDateFormat((String)formats.get(9))).parse(str);
    sdf.format(date);
    sdf = new SimpleDateFormat((String)formats.get(4));
    System.out.println(date);
  }
  
  public Date convert(String source) {
    try {
      if (source.matches("^\\d{4}$"))
        return parseDate(source, (String)formats.get(0), null); 
      if (source.matches("^\\d{4}-\\d{1,2}$"))
        return parseDate(source, (String)formats.get(1), null); 
      if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$"))
        return parseDate(source, (String)formats.get(2), null); 
      if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$"))
        return parseDate(source, (String)formats.get(3), null); 
      if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$"))
        return parseDate(source, (String)formats.get(4), null); 
      if (source.matches("^\\d{4}/\\d{1,2}$"))
        return parseDate(source, (String)formats.get(5), null); 
      if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2}$"))
        return parseDate(source, (String)formats.get(6), null); 
      if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}$"))
        return parseDate(source, (String)formats.get(7), null); 
      if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$"))
        return parseDate(source, (String)formats.get(8), null); 
      if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}Z$"))
        return parseDate(source, (String)formats.get(9), TimeZone.getTimeZone("GMT")); 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return null;
  }
  
  public Date parseDate(String dateStr, String format, TimeZone timeZone) {
    Date date = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    if (timeZone != null)
      dateFormat.setTimeZone(timeZone); 
    dateFormat.setLenient(false);
    try {
      date = dateFormat.parse(dateStr);
    } catch (ParseException e) {
      e.printStackTrace();
    } catch (Exception e1) {
      e1.printStackTrace();
    } 
    return date;
  }
}

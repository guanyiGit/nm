package atshunhengli.com.entity.callback;

import java.util.Date;

public class FenceUtils {

	// 地球半径
	private static double EARTH_RADIUS = 6371.393;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
	
	public static void main(String[] args) {
		System.out.println(FenceUtils.GetDistance(106.486654,29.220001,106.486654,29.490295));
	}
	
	/**
	 * 计算两个经纬度之间的距离(单位：米)
	 * 
	 * @param lat1  纬度1
	 * @param lng1  经度1
	 * @param lat2  纬度2
	 * @param lng2  经度2	
	 * @return
	 */
	public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 1000);
		return s;
	}

	/**
	 * @Description: 计算两个时间点之间的时间差距 
	 * @Title: dateDiff 
	 * @param  startTime 开始时间 
	 * @param  endTime 结束时间
	 * @param  format 时间转换器 
	 * @param  str 返回时间格式 day:天 hour:小时 min:分钟 其他:秒 
	 * @param  Double
	 * @throws
	 */
	public static Double dateDiff(Date startTime, Date endTime){
		return dateDiff(startTime, endTime, null);
	}
	
	
	public static Double dateDiff(Date startTime, Date endTime, String str) {
		// 按照传入的格式生成一个simpledateformate对象
		Double nd = (double) (1000 * 24 * 60 * 60);// 一天的毫秒数
		Double nh = (double) (1000 * 60 * 60);// 一小时的毫秒数
		Double nm = (double) (1000 * 60);// 一分钟的毫秒数
		Double ns = (double) 1000;// 一秒钟的毫秒数
		Double diff;
		Double day = 0.0;
		Double hour = 0.0;
		Double min = 0.0;
		Double sec = 0.0;
		// 获得两个时间的毫秒时间差异
		try {
			if(str == null ||str.equals("")){
				str = "";
			}
			
			diff = (double) (endTime.getTime() - startTime.getTime());
			day = diff / nd;// 计算差多少天
			hour = diff / nh;// 计算差多少小时
			min = diff / nm;// 计算差多少分钟
			sec = diff / ns;// 计算差多少秒
				
			if (str.equalsIgnoreCase("day")) {
				return (double) Math.round(day);
			} else if (str.equalsIgnoreCase("hour")) {
				return (double) Math.round(hour);
			} else if (str.equalsIgnoreCase("min")) {
				return (double) Math.round(min);
			}else if(str.equalsIgnoreCase("sec")){
				return (double) Math.round(sec);
			}
			else{
				return diff;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

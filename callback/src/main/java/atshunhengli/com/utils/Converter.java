package atshunhengli.com.utils;


import atshunhengli.com.entity.callback.Point;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: WGS_84ToGCJ_02
 * @Description: 真实地址转高德地址
 * @author py
 * @date 2018年8月29日
 *
 */
public class Converter {


	private final static double a = 6378245.0; // 长半轴
	private final static double pi = 3.14159265358979324; // π
	private final static double ee = 0.00669342162296594323; // e²

//	// 真实地址转高德地址
//	public List<Point> WGS_84GCJ_02(List<Point> points) throws Exception {
//
//		Map<String, String> map = new HashMap<>();
//		List<Point> pointList = new ArrayList<>();
//		String url = "https://restapi.amap.com/v3/assistant/coordinate/convert";
//		String key = this.key;
//		String locations = "";
//		for (Point point : points) {
//			locations += point.getLongitude() + "," + point.getLatitude() + "|";
//		}
//		locations = locations.substring(0, locations.length() - 1);
//		String coordsys = "gps";
//		map.put("key", key);
//		map.put("locations", locations);
//		map.put("coordsys", coordsys);
//		JSONObject doget = HttpsUtils.doGet(url, map);
//		String string = doget.getString("locations");
//		String[] pointsString = string.split(";");
//		for (String pointString : pointsString) {
//			String[] split = pointString.split(",");
//			Point point = new Point();
//			point.setLongitude(Double.valueOf(split[0]));
//			point.setLatitude(Double.valueOf(split[1]));
//			pointList.add(point);
//		}
//		return pointList;
//	}
	
	
	
	// WGS-84 to GCJ-02
	public static List<Point> WGS_84GCJ_02(List<Point> points) {
		List<Point> pointList = new ArrayList<Point>();
		for (Point point : points) {
			Point apoint = new Point();
			// 计算地图偏差
			Point dev = calDev(point.getLatitude(), point.getLongitude());
			apoint.setLatitude(point.getLatitude() + dev.getLatitude());
			apoint.setLongitude(point.getLongitude() + dev.getLongitude());
			pointList.add(apoint);
		}
		return pointList;
	}

	// GCJ-02 to WGS-84
	public static List<Point> GCJ_02ToWGS_84(List<Point> points) {
		List<Point> pointList = new ArrayList<>();
		for (Point point : points) {
			Point apoint = new Point();
			// 计算地图偏差
			Point dev = calDev(point.getLatitude(), point.getLongitude());
			apoint.setLatitude(point.getLatitude() - dev.getLatitude());
			apoint.setLongitude(point.getLongitude() - dev.getLongitude());
			pointList.add(apoint);
		}
		return pointList;
	}

	// 计算偏差
	private static Point calDev(Double wgLat, Double wgLon) {
		if (isOutOfChina(wgLat, wgLon)) {
			return new Point((double) 0, (double) 0);
		}
		double dLat = calLat(wgLon - 105.0, wgLat - 35.0);
		double dLon = calLon(wgLon - 105.0, wgLat - 35.0);
		double radLat = wgLat / 180.0 * pi;
		double magic = Math.sin(radLat);
		magic = 1 - ee * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
		dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
		return new Point(dLat, dLon);
	}

	// 判断坐标是否在国外
	private static boolean isOutOfChina(Double lat, Double lon) {
		if (lon < 72.004 || lon > 137.8347)
			return true;
		if (lat < 0.8293 || lat > 55.8271)
			return true;
		return false;
	}

	// 计算纬度
	private static Double calLat(Double x, Double y) {
		Double resultLat = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
		resultLat += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		resultLat += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
		resultLat += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
		return resultLat;
	}

	// 计算经度
	private static Double calLon(Double x, Double y) {
		Double resultLon = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
		resultLon += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		resultLon += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
		resultLon += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
		return resultLon;
	}
}

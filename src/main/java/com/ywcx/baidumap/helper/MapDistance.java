package com.ywcx.baidumap.helper;

import java.util.HashMap;
import java.util.Map;

import com.ywcx.baidumap.bean.Point;

public class MapDistance {

	private static double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double getDistance(Point p1, Point p2) {
		double radLat1 = Math.toRadians(p1.getLatitude());
		double radLat2 = Math.toRadians(p2.getLatitude());
		double a = radLat1 - radLat2;
		double b = Math.toRadians(p1.getLongitude()) - Math.toRadians(p2.getLongitude());
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		//保留两位小数乘除100, 四位则是10000
		s = (double) Math.round(s * 100) / 100;
		return s;
	}

	/**
	 * 获取当前用户一定距离以内的经纬度值 单位米 return minLat 最小经度 minLng 最小纬度 maxLat 最大经度 maxLng 最大纬度
	 * minLat
	 */
	public static Map getAround(String latStr, String lngStr, String raidus) {
		Map map = new HashMap();

		Double latitude = Double.parseDouble(latStr);// 传值给经度
		Double longitude = Double.parseDouble(lngStr);// 传值给纬度

		Double degree = (24901 * 1609) / 360.0; // 获取每度
		double raidusMile = Double.parseDouble(raidus);

		Double mpdLng = Double.parseDouble((degree * Math.cos(latitude * (Math.PI / 180)) + "").replace("-", ""));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng * raidusMile;
		// 获取最小经度
		Double minLat = longitude - radiusLng;
		// 获取最大经度
		Double maxLat = longitude + radiusLng;

		Double dpmLat = 1 / degree;
		Double radiusLat = dpmLat * raidusMile;
		// 获取最小纬度
		Double minLng = latitude - radiusLat;
		// 获取最大纬度
		Double maxLng = latitude + radiusLat;

		map.put("minLat", minLat + "");
		map.put("maxLat", maxLat + "");
		map.put("minLng", minLng + "");
		map.put("maxLng", maxLng + "");

		return map;
	}

	public static HashMap<String,Point> findNeighPosition(Point p) {
		// 先计算查询点的经纬度范围
		HashMap<String,Point> map= new HashMap<String,Point>();
		double latitude=p.getLatitude();
		double longitude=p.getLongitude();
		
		double r = EARTH_RADIUS;// 地球半径千米
		double dis = 3.53;// 3.53^2+3.53^2=5^2
		double dlng = 2 * Math.asin(Math.sin(dis / (2 * r)) / Math.cos(latitude * Math.PI / 180));
		dlng = dlng * 180 / Math.PI;// 角度转为弧度
		double dlat = dis / r;
		dlat = dlat * 180 / Math.PI;
		double minlat = latitude - dlat;
		double maxlat = latitude + dlat;
		double minlng = longitude - dlng;
		double maxlng = longitude + dlng;
		
		Point pointMax=new Point(maxlat, maxlng);
		Point pointMin=new Point(minlat, minlng);
		map.put("MAX", pointMax);
		map.put("MIN", pointMin);
		
		return map;

	}

}
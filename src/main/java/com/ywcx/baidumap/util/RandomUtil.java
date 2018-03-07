package com.ywcx.baidumap.util;

import java.math.BigDecimal;
import java.util.Random;

import com.ywcx.baidumap.bean.Point;

public class RandomUtil {
	public static Point randomLonLat(double minLon, double minLat,double maxLon, double maxLat) {  
	    BigDecimal db = new BigDecimal(Math.random() * (maxLon - minLon) + minLon);  
	    double longitude = db.setScale(15, BigDecimal.ROUND_HALF_UP).doubleValue();//小数后6位  
	    db = new BigDecimal(Math.random() * (maxLat - minLat) + minLat);  
	    double latitude = db.setScale(15, BigDecimal.ROUND_HALF_UP).doubleValue();  
	    return new Point(latitude, longitude);  
	} 
	
	public static Point randomLonLat(Point maxPoint, Point minPoint) {  
	    return randomLonLat(minPoint.getLongitude(),minPoint.getLatitude(),maxPoint.getLongitude(),maxPoint.getLatitude());  
	} 
}

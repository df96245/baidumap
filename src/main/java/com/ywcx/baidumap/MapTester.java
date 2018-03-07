package com.ywcx.baidumap;

import java.util.HashMap;
import java.util.TreeSet;

import com.ywcx.baidumap.bean.Point;
import com.ywcx.baidumap.helper.MapDistance;
import com.ywcx.baidumap.util.RandomUtil;

public class MapTester {
	public static void main(String[] args) {
		Point pointMid=new Point(20.013426508350145, 110.2932342978026);
		HashMap<String,Point> map=MapDistance.findNeighPosition(pointMid);
		long begin = System.currentTimeMillis();
		Point pointMax=map.get("MAX");
		Point pointMin=map.get("MIN");
		TreeSet<Double> treeSet= new TreeSet<Double>();
		for (int i = 0; i < 1000000; i++) {
			Point randomPoint=RandomUtil.randomLonLat(pointMax, pointMin);
			double distance=MapDistance.getDistance(pointMid,randomPoint);
			treeSet.add(distance);
		}
		
		for (Double d : treeSet) {
			System.out.println(d);
		}
		
		System.out.println(MapDistance.getDistance(pointMid,pointMax));
		System.out.println(MapDistance.getDistance(pointMid,pointMin));
		
		System.out.println("耗时：" + (System.currentTimeMillis() - begin) + "毫秒"); // 耗时：461毫秒

	}
}

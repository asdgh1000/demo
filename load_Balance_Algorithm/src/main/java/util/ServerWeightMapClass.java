package util;

import java.util.LinkedHashMap;

/**
 * Created by Jo on 12/9/16.
 */
public class ServerWeightMapClass {
	public static LinkedHashMap getMap(){
		LinkedHashMap serverWeightMap = new LinkedHashMap();
		serverWeightMap.put("192.168.0.21",1);
		serverWeightMap.put("192.168.0.22",2);
		serverWeightMap.put("192.168.0.23",1);
		serverWeightMap.put("192.168.0.24",3);
		serverWeightMap.put("192.168.0.25",4);
		serverWeightMap.put("192.168.0.26",5);
		return serverWeightMap;
	}
}

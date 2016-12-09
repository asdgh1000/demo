package roundRobin;

import util.ServerWeightMapClass;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jo on 12/9/16.
 */
public class RoundRobin {
	static Integer pos = 0;
	public static String testRoundRobin(){
		//通过新建方法内的局部变量serverMap,先将域变量复制到线程本地,以避免被多个线程修改.
		LinkedHashMap<String,Integer> serverWeightMap = ServerWeightMapClass.getMap();
		Map<String,Integer> serverMap = new LinkedHashMap<String, Integer>();
		serverMap.putAll(serverWeightMap);
		Set<String> keySet = serverMap.keySet();
		ArrayList keyList = new ArrayList();
		keyList.addAll(keySet);
		String server = null;
		//同一时刻只有一个线程能修改pos
		synchronized (pos){
			if(pos >= keySet.size()){
				pos = 0;
			}
			server = (String)keyList.get(pos);
			pos++;
		}
		return server;
	}
}

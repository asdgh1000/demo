package Hash;

import util.ServerWeightMapClass;

import java.util.*;

/**
 * Created by Jo on 12/9/16.
 */
public class HashMethod {
	Integer pos = 0;
	public static String testConsumerHash(String consumerIp){
		//通过新建方法内的局部变量serverMap,先将域变量复制到线程本地,以避免被多个线程修改.
		LinkedHashMap<String,Integer> serverWeightMap = ServerWeightMapClass.getMap();
		Map<String,Integer> serverMap = new LinkedHashMap<String, Integer>();
		serverMap.putAll(serverWeightMap);
		Set<String> keySet = serverMap.keySet();
		ArrayList keyList = new ArrayList();
		keyList.addAll(keySet);

		int hashCode = consumerIp.hashCode();
		int serverListSize = keyList.size();
		int serverPos = hashCode % serverListSize;

		return (String)keyList.get(serverPos);
	}
}

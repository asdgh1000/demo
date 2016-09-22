package user.aop;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by Jo on 8/24/16.
 */
public class MethodCounter implements Serializable{
	private HashMap<String,Integer> map = new HashMap<String, Integer>();
	private int allCount;
	protected void count(Method method){

	}
	protected void count(String methodName){
		Integer i = map.get(methodName);
		i = (i!=null) ? new Integer(i.intValue()+1):new Integer(1);
		map.put(methodName,i);
		++allCount;
	}
	public int getCalls(String methodName){
		Integer i = map.get(methodName);
		return (i != null ? i.intValue() : 0);
	}

	public int getAllCounts(){
		return allCount;
	}

	@Override public boolean equals(Object o) {
		return (o != null && o.getClass() == this.getClass());
	}

	@Override public int hashCode(){
		return this.getClass().hashCode();
	}

}

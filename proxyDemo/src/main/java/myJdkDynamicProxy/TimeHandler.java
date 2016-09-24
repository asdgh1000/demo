package myJdkDynamicProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by Jo on 9/24/16.
 */
public class TimeHandler implements InvocationHandler {
	public TimeHandler(Object target){
		this.target = target;
	}
	private Object target;

	public void invoke(Object o, Method method) {
		long start = System.currentTimeMillis();
		System.out.println("start-----");
		try {
			method.invoke(target);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("end-----");
		System.out.println("waste time :" + (end - start));
	}
}

package jdkDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Jo on 9/21/16.
 */
public class TimeHandler implements InvocationHandler {
	public TimeHandler(Object target){
		this.target = target;
	}
	private Object target;

	/**
	 *
	 * @param proxy 生成的代理对象
	 * @param method 被代理对象的方法
	 * @param args 被代理对象方法的参数
	 * @return
	 * @throws Throwable 方法的返回值
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("start-----");
		//调用传递过来的对象的方法
		method.invoke(target);
		long end = System.currentTimeMillis();
		System.out.println("end-----");
		System.out.println("waste time :" + (end - start));
		return null;
	}
}

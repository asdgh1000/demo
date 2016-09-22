package jdkDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Jo on 9/21/16.
 */
public class TransactionHandler implements InvocationHandler {
	public TransactionHandler(Object target){
		this.target = target;
	}
	private Object target;
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("start Transaction-----");
		//调用传递过来的对象的方法
		method.invoke(target);
		System.out.println("end Transaction-------");
		return null;
	}
}

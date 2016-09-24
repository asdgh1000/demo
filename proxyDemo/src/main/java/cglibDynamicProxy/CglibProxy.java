package cglibDynamicProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Jo on 9/22/16.
 */
public class CglibProxy implements MethodInterceptor {
	private Enhancer enhancer = new Enhancer();
	public Object getProxy(Class clazz){
		//设置创建子类的类
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}
	/**
	 * CGlib 使用继承方式进行代理
	 * 拦截所有目标类方法的调用
	 * @param o 目标类的实例
	 * @param method 目标类的方法
	 * @param args 方法的参数
	 * @param methodProxy 代理类的实例
	 * @return
	 * @throws Throwable
	 */
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("start-----");
		/**
		 * 代理类调用父类的方法
		 * o:目标类对象
		 * args:方法参数
		 */
		methodProxy.invokeSuper(o,args);
		long end = System.currentTimeMillis();
		System.out.println("end-----");
		System.out.println("waste time :" + (end - start));
		return null;
	}
}

package jdkDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理测试类:为防止静态代理的类爆炸,因此引入动态代理(若需要多种不同的代理,即使使用组合的方式仍然需要创建很多代理类)
 * Created by Jo on 9/21/16.
 */
public class Test {
	public static void main(String[] args){

		Move move = new MoveImpl();
		InvocationHandler timeHandler = new TimeHandler(move);

		/**
		 * 类加载器
		 * 代理类接口
		 * 代理Handler
		 */
		Class mv = move.getClass();
		Move moveProxy = (Move)Proxy.newProxyInstance(mv.getClassLoader(), mv.getInterfaces(), timeHandler);
		//进行两次代理
		InvocationHandler transactionHandler = new TransactionHandler(moveProxy);
		Move moveProxy1 = (Move)Proxy.newProxyInstance(mv.getClassLoader(),mv.getInterfaces(),transactionHandler);
		moveProxy1.drive();
	}
}

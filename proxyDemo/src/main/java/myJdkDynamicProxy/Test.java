package myJdkDynamicProxy;



/**
 * 动态代理测试类:为防止静态代理的类爆炸,因此引入动态代理(若需要多种不同的代理,即使使用组合的方式仍然需要创建很多代理类)
 * Created by Jo on 9/21/16.
 */
public class Test {
	public static void main(String[] args){
		Move move = new MoveImpl();

		/**
		 * loader:类加载器
		 * interface:代理类接口
		 * handler:代理Handler
		 *
		 * 动态代理实现思路:
		 * 实现功能:通过proxy的newProxyInstance返回代理对象
		 * 1.声明一段源码:动态声称代理类
		 * 2.对源码进行编译(JDK Compiler API) ,产生新的类 (代理类)
		 * 3.将类load到内存中产生代理对象
		 * return 代理对象
		 */
		InvocationHandler timeHandler = new TimeHandler(move);
		try {
			Move moveProxy = (Move)Proxy.newProxyInstance(Move.class,timeHandler);
			moveProxy.drive();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

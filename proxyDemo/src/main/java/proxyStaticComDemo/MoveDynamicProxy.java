package proxyStaticComDemo;

/**
 * 利用聚合实现静态代理  聚合比继承更适合实现代理
 * Created by Jo on 9/21/16.
 */
public class MoveDynamicProxy implements Move {
	public MoveDynamicProxy(Move move){
		super();
		this.move = move;
	}

	private Move move;

	public void drive() {
		long start = System.currentTimeMillis();
		System.out.println("start-----");
		move.drive();
		long end = System.currentTimeMillis();
		System.out.println("end------");
		System.out.println("waste time :" + (end - start));
	}
}

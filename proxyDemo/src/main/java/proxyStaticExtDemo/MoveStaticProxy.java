package proxyStaticExtDemo;

/**
 * 利用继承实现静态代理,继承方式实际上并不适合做静态代理模式,因为若要多次进行代理则需要多次进行类继承
 * Created by Jo on 9/21/16.
 */
public class MoveStaticProxy extends MoveImpl {
	@Override
	public void drive() {
		long start = System.currentTimeMillis();
		System.out.println("start-----");
		super.drive(); //被代理方法
		long end = System.currentTimeMillis();
		System.out.println("end------");
		System.out.println("waste time :" + (end - start));
	}

}

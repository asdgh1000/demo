package proxyStaticExtDemo;

/**
 * 静态代理测试类
 * Created by Jo on 9/21/16.
 */
public class Test {
	public static void main(String[] args){
		Move move = new MoveStaticProxy();
		move.drive();
	}
}

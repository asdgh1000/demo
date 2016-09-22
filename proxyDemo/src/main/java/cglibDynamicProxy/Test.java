package cglibDynamicProxy;

/**
 * Created by Jo on 9/22/16.
 */
public class Test {
	public static void main(String[] args){
		CglibProxy proxy = new CglibProxy();
		Move move = (Move)proxy.getProxy(MoveImpl.class);
		move.drive();
	}
}

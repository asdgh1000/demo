package proxyStaticComDemo;

/**
 * Created by Jo on 9/21/16.
 */
public class MoveDynamicProxy2 implements Move{
	public MoveDynamicProxy2(Move move){
//		super();
		this.move = move;
	}
	private Move move;
	public void drive() {
		System.out.println("another proxy start--");
		move.drive();
		System.out.println("another proxy end--");
	}
}

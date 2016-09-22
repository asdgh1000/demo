package proxyStaticComDemo;

/**
 * 利用聚合代理的测试类
 * Created by Jo on 9/21/16.
 */
public class Test {
	public static void main(String[] args){
		Move move = new MoveImpl();
		//模拟进行两次代理   相比于静态代理有优势,若继承式的若要更换代理顺序则需要更改继承顺序,且不能去掉继承中的某一环,极不灵活.
		Move moveProxy = new MoveDynamicProxy(move);
		Move moveProxy2 = new MoveDynamicProxy2(moveProxy);
		moveProxy2.drive();
	}
}

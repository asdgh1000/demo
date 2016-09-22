package proxyStaticComDemo;

/**
 * 未使用代理的原始类
 * Created by Jo on 9/21/16.
 */
public class MoveImpl implements Move {
	public void drive() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
	}

}

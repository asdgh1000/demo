package user.sync;

/**
 * Created by Jo on 9/6/16.
 */
public class ThreadLocalTest {
	private ThreadLocal myThreadLocal1 = new ThreadLocal<String>();
	public void test(){
		myThreadLocal1.set("Hello ThreadLocal");
		String threadLocalValues = (String)myThreadLocal1.get();
		System.out.println(threadLocalValues);
	}
	public static void main(String[] args){
		ThreadLocalTest threadLocalTest = new ThreadLocalTest();
		threadLocalTest.test();
	}

}

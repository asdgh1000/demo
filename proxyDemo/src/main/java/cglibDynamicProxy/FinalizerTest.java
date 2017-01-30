package cglibDynamicProxy;

/**
 * Created by Jo on 1/27/17.
 */
public class FinalizerTest {
	public static FinalizerTest object;
	public void isAlive() {
		System.out.println("I'm alive");
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("method finalize is running");
		object = this;
	}

	public static void main(String[] args) throws Exception {
		object = new FinalizerTest();

		// 第一次执行，finalize方法会自救
		object = null;
		System.gc();

		Thread.sleep(500);
		if (object != null) {
			object.isAlive();
		} else {
			System.out.println("I'm dead");
		}

		// 第二次执行，finalize方法已经执行过
		object = null;
		System.gc();

		Thread.sleep(500);
		if (object != null) {
			object.isAlive();
		} else {
			System.out.println("I'm dead");
		}
	}
}
/**
 * Created by Jo on 1/16/17.
 */
public class SycronizedTest {
	static int count = 0;
	public static void main(String[] args) throws InterruptedException {
		SycronizedTest sycronizedTest = new SycronizedTest();
		sycronizedTest.test();
	}
	public void test(){
		
		final Object lock = new Object();
		Runnable runnable = new Runnable() {
			public  void run() {
				synchronized(this) {
					for (int i = 0; i < 5; i++) {
						count++;
						System.out.println(Thread.currentThread() + ":" + count);
					}
				}
			}
		};


		Thread thread1 = new Thread(runnable);
		thread1.start();
	}

}

package Atomicity;

/**
 * Created by Jo on 10/8/16.
 */
public class UnsafeCount implements Runnable{
	long count = 0l;
	public void run() {
		//休眠100ms使得几乎每次都会产生线程争抢情况
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//这里count++不是原子操作,而是可以被分为:读 改 写 的三个步骤,因此可能会因为不同线程访问同一个共享变量count而产生一致性问题.
//		System.out.println(++count);
		//简单解决方案:给count++操作加同步锁
		synchronized(this){
		//此时只有一个线程能获取count的操作权限
			System.out.println(++count);
		}
	}
	public static void main(String[] args) {
		UnsafeCount unsafeCount = new UnsafeCount();
		for(int i=0;i<1000;i++){
			Thread thread = new Thread(unsafeCount);
			thread.start();
		}
	}

}

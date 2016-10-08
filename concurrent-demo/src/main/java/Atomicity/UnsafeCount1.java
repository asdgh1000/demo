package Atomicity;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Jo on 10/8/16.
 */
public class UnsafeCount1 implements Runnable{
	//使用current包提供的atomic可以便捷的将变量变为安全的原子变量
//	AtomicLong count = new AtomicLong(0l);
	long count = 0;
	public void run() {
		//休眠100ms使得几乎每次都会产生线程争抢情况
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//这里count++不是原子操作,而是可以被分为:读 改 写 的三个步骤,因此可能会因为不同线程访问同一个共享变量count而产生一致性问题.
		System.out.println(++count);
		//这边内部实现与sychronized并不相同,可以看到sychronized代码段执行的多线程任务输出结果是有序的,而用atomic修饰的则是无序的.
		// 这部分后面再讨论.
//		System.out.println(count.incrementAndGet());
	}
	public static void main(String[] args) {
		UnsafeCount1 unsafeCount = new UnsafeCount1();
		for(int i=0;i<1000;i++){
			Thread thread = new Thread(unsafeCount);
			thread.start();
		}
	}

}


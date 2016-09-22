package user.sync;

/**
 * Created by Jo on 9/6/16.
 */
public class ThreadLocalExample {
	public static class MyRunnable implements Runnable {
		private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
		public void run(){
			threadLocal.set( (int) (Math.random() * 100D) );
			try{
				Thread.sleep(2000);
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println(threadLocal.get());
		}
	}
	public static void main(String[] args){
		MyRunnable myRunnableInstance = new MyRunnable();
		Thread thread1 = new Thread(myRunnableInstance);
		Thread thread2 = new Thread(myRunnableInstance);
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

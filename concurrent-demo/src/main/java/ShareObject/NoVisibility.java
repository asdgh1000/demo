package ShareObject;

/**
 * Created by Jo on 10/8/16.
 */
public class NoVisibility {
	//存在共享变量,但是同时没有做好线程之间的同步
	private static boolean ready;
	private static int number;
	private static class ReaderThread extends Thread{
		public void run(){
			while(!ready){
				Thread.yield();
			}
			System.out.println(number);
		}
	}
	public static void main(String[] args){
		for(int i = 0;i<100;i++) {
			//由于重排序的缘故,执行顺序可能并不是按照预期执行
			new ReaderThread().start();
			number = 100;
			ready = true;
		}
	}
}

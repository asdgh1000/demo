package user.sync;

/**
 * Created by Jo on 9/6/16.
 */
public class Example {
	public static void main(String[] args){
		Counter counter = new Counter();
//		Counter counter1 = new Counter();
		CounterThread counterThread1 = new CounterThread(counter);
		CounterThread counterThread2 = new CounterThread(counter);
		counterThread1.run();
		counterThread2.run();
	}
}

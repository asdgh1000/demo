package user.sync;

/**
 * Created by Jo on 9/6/16.
 */
public class CounterThread extends Thread {
	protected Counter counter = null;
	public CounterThread(Counter counter){
		this.counter = counter;
	}
	@Override
	public void run(){
		for(int i=0;i<10;i++){
			counter.add(i);
			System.out.println(counter.count);
		}
	}
}

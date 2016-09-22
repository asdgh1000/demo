package user.sync;

/**
 * Created by Jo on 9/6/16.
 */

public class Counter {
	long count = 0;
	public synchronized void add(long value){
		this.count = count+value;
	}
}
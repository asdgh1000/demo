package zk.create;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jo on 12/12/16.
 */
public class ThreadTest {
	public static void main(String[] args){
		long startTime = System.currentTimeMillis();
		int count =1000;
		final List<Integer> l = new ArrayList<Integer>();
		final Random random = new Random();
		for(int i =0;i<count;i++){
			Thread thread = new Thread(){
				@Override
				public void run(){
					l.add(random.nextInt());
				}
			};
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(System.currentTimeMillis() - startTime);
		System.out.println(l.size());
	}
}

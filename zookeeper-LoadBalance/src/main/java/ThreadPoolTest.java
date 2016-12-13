import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jo on 12/12/16.
 */
public class ThreadPoolTest {
	public static void main(String[] args){
		int count = 1000;
		long startTime = System.currentTimeMillis();
		final List<Integer> l = new ArrayList<Integer>();
		ThreadPoolExecutor tp = new ThreadPoolExecutor(1,1,60, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(count));
		final Random random = new Random();
		for(int i=0;i<count;i++){
			tp.execute(new Runnable() {
				public void run() {
					l.add(random.nextInt());
				}
			});
		}
		tp.shutdown();
		try {
			tp.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis()-startTime);
		System.out.println(l.size());
	}
}

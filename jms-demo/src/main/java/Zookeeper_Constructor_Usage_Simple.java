import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Jo on 2/22/17.
 */
public class Zookeeper_Constructor_Usage_Simple implements Watcher {
	private static CountDownLatch countDownLatch = new CountDownLatch(1);
	public static void main(String[] args)throws Exception{
		Executor executor = Executors.newFixedThreadPool(10);
		ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",5000,new Zookeeper_Constructor_Usage_Simple());
		System.out.println(zooKeeper.getState());
		try{
			countDownLatch.await();
		}catch (InterruptedException e){
			System.out.println("zookeeper session established");
			e.printStackTrace();
		}
	}
	public void process(WatchedEvent event) {
		System.out.println("Receive watched event :"+event);
		if(Event.KeeperState.SyncConnected == event.getState()){
			countDownLatch.countDown();
		}
	}
}

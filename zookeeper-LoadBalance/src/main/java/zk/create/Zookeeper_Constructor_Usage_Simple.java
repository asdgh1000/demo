package zk.create;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Jo on 2/22/17.
 */
public class Zookeeper_Constructor_Usage_Simple implements Watcher {
	private static CountDownLatch countDownLatch = new CountDownLatch(1);
	public static void main(String[] args)throws Exception{
		ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",5000,new Zookeeper_Constructor_Usage_Simple());
		System.out.println(zooKeeper.getState());
		try{
			countDownLatch.await();
			String path1 = zooKeeper.create("/zk-test-ephemeral-","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			System.out.println("Success create znode:"+path1);
			String path2 = zooKeeper.create("/zk-test-ephemeral-","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
			System.out.println("Success create znode:"+path2);
		}catch (InterruptedException e){
			System.out.println("zookeeper session established");
			e.printStackTrace();
		}
	}
	public void process(WatchedEvent event) {
//		System.out.println("Receive watched event :"+event);
		if(Event.KeeperState.SyncConnected == event.getState()){
			countDownLatch.countDown();
		}
	}
}

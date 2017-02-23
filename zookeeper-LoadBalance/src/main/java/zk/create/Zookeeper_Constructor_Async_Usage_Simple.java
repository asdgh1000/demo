package zk.create;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Jo on 2/22/17.
 */
public class Zookeeper_Constructor_Async_Usage_Simple implements Watcher {
	private static CountDownLatch countDownLatch = new CountDownLatch(1);
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",5000,new Zookeeper_Constructor_Async_Usage_Simple());
		System.out.println(zooKeeper.getState());
		countDownLatch.await();
		new Zookeeper_Constructor_Async_Usage_Simple().create(zooKeeper);
	}
	public void create(ZooKeeper zooKeeper) throws InterruptedException {
		zooKeeper.create("/zk-test-ephemeral-","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,new IStringCallBack(),"test");
		zooKeeper.create("/zk-test-ephemeral-","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL,new IStringCallBack(),"test");

		Thread.sleep(Integer.MAX_VALUE);
	}
	public void process(WatchedEvent event) {
		if(Event.KeeperState.SyncConnected == event.getState()){
			countDownLatch.countDown();
		}
	}
	class IStringCallBack implements AsyncCallback.StringCallback{

		public void processResult(int rc, String path, Object ctx, String name) {
			System.out.println("Create path result ["+rc+","+ctx+",real path name:"+name);
		}
	}
}

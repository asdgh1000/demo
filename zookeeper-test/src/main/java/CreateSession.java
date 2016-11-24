import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by Jo on 11/22/16.
 */
public class CreateSession implements Watcher{
	private static ZooKeeper zookeeper;
	public static void main(String[] args) throws IOException,InterruptedException{
		zookeeper = new ZooKeeper("127.0.0.1:2181",5000,new CreateSession());
		System.out.println(zookeeper.getState());
		Thread.sleep(Integer.MAX_VALUE);
	}

	public void process(WatchedEvent watchedEvent) {
		System.out.println(watchedEvent);
	}
}

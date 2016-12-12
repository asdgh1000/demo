package zk.create;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by Jo on 12/10/16.
 */
public class CreateZookeeper {
	String url = "127.0.0.1:2181";
	public void connect() {
		try {
			ZooKeeper zooKeeper = new ZooKeeper(url, 10000, null);
			zooKeeper.create("/root", "root data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zooKeeper.setData("/root", "hello".getBytes(), -1);
			zooKeeper.create("/root/child","root child".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		CreateZookeeper createZookeeper = new CreateZookeeper();
		createZookeeper.connect();
	}

}

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkInterruptedException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jo on 11/24/16.
 */
public class WorkServer {

	private volatile boolean running = true;

	private ZkClient zkClient;

	private static final String MASTER_PATH = "/master";

	private IZkDataListener dataListner;

	private RunningData serverData;

	private RunningData masterData;

	//延时调度器
	private ScheduledExecutorService delayExecutor = Executors.newScheduledThreadPool(1);
	//延迟时间
	private int delayTime = 5;

	public WorkServer(RunningData runningData){
		this.serverData = runningData;
		this.dataListner = new IZkDataListener(){

			public void handleDataChange(String s, Object o) throws Exception {

			}

			public void handleDataDeleted(String s) throws Exception {
//				askMaster();
				if(masterData != null && masterData.getName().equals(serverData.getName())){
					askMaster();
				}
				else{
					delayExecutor.schedule(new Runnable() {
						public void run() {
							try {
								askMaster();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					},delayTime, TimeUnit.SECONDS);
				}
			}
		};
	}

	public void start() throws Exception{
		if(running){
			throw new Exception("Server has start up");
		}
		running = true;
		zkClient.subscribeDataChanges(MASTER_PATH, dataListner);
		askMaster();
	}

	public void stop() throws Exception{
		if(! running){
			throw new Exception("Server has stoped ");
		}
		running = false;
		zkClient.unsubscribeDataChanges(MASTER_PATH, dataListner);
		releaseMaster();
	}

	public void askMaster() throws Exception{
		if(!running){
			return;
		}
		try {
			zkClient.create(MASTER_PATH, serverData, CreateMode.EPHEMERAL);
			masterData = serverData;
			delayExecutor.schedule(new Runnable() {
				public void run() {
					try {
						if(checkMaster()) {
							releaseMaster();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			},delayTime,TimeUnit.SECONDS);
		}catch (ZkNodeExistsException e){
			RunningData runningData = zkClient.readData(MASTER_PATH,true);
			if(runningData == null){
				askMaster();
			}
			else{
				masterData = runningData;
			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	public boolean checkMaster() throws Exception{
		try{
			RunningData masterData = zkClient.readData(MASTER_PATH);
			if(masterData.getName().equals(serverData.getName())){
				return true;
			}
		}catch (ZkNodeExistsException e){
			return false;
		}catch (ZkInterruptedException e){
			return checkMaster();
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public void releaseMaster() throws Exception{
		if(checkMaster()) {
			try {
				zkClient.delete(MASTER_PATH);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}

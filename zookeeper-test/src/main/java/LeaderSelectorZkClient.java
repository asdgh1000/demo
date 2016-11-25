import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jo on 11/24/16.
 */
public class LeaderSelectorZkClient
{
	//启动的服务个数
	private static final int        CLIENT_QTY = 10;
	//zookeeper服务器的地址
	private static final String     ZOOKEEPER_SERVER = "127.0.0.1:2181";


	public static void main(String[] args) throws Exception
	{
		//保存所有zkClient的列表
		List<ZkClient>  clients = new ArrayList<ZkClient>();
		//保存所有服务的列表
		List<WorkServer>  workServers = new ArrayList<WorkServer>();

		try
		{
			for ( int i = 0; i < CLIENT_QTY; ++i )
			{
				//创建zkClient
				ZkClient client = new ZkClient(ZOOKEEPER_SERVER, 5000, 5000, new SerializableSerializer());
				clients.add(client);
				//创建serverData
				RunningData runningData = new RunningData();
				runningData.setCid(Long.valueOf(i));
				runningData.setName("Client #" + i);
				//创建服务
				WorkServer  workServer = new WorkServer(runningData);
				workServer.setZkClient(client);

				workServers.add(workServer);
				workServer.start();
			}

			System.out.println("敲回车键退出！\n");
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		}
		finally
		{
			System.out.println("Shutting down...");

			for ( WorkServer workServer : workServers )
			{
				try {
					workServer.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			for ( ZkClient client : clients )
			{
				try {
					client.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

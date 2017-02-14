package com.netease.rpc.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jo on 2/10/17.
 */
public class MultiThreadEchoServer {
	private static ExecutorService executorService = Executors.newCachedThreadPool();
	static class HandleMsg implements Runnable{
		Socket socketClient;
		public HandleMsg(Socket socketClient) {
			this.socketClient = socketClient;
		}

		@Override public void run() {
			BufferedReader is = null;
			PrintWriter os = null;
			try{
				is = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				os = new PrintWriter(socketClient.getOutputStream());
				String inputLine = null;
				long b = System.currentTimeMillis();
				while((inputLine = is.readLine())!=null){
					os.print(inputLine);
				}
				long e = System.currentTimeMillis();
				System.out.println("spend:"+(e-b)+"ms");

			}catch(IOException e){
				e.printStackTrace();
			}finally {
				try{
					if(is!=null)
						is.close();
					if(os!=null)
						os.close();
					socketClient.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args){
		ServerSocket echoServer = null;
		Socket clientSocket = null;
		try{
			echoServer = new ServerSocket(8000);
		}catch (IOException e){
			e.printStackTrace();
		}
		while(true){
			try{
				clientSocket = echoServer.accept();
				System.out.println(clientSocket.getRemoteSocketAddress() + "connect!");
				executorService.execute(new HandleMsg(clientSocket));
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
}

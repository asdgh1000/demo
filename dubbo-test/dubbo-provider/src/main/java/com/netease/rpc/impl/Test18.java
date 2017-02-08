package com.netease.rpc.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Jo on 2/7/17.
 */
public class Test18 {
	public static class MyTask implements Runnable{

		public void run() {
			System.out.println(System.currentTimeMillis()+":Thread ID:"+Thread.currentThread().getId());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		public static void main(String[] args){
			MyTask myTask = new MyTask();
			Executor executor = Executors.newFixedThreadPool(5);
			for(int i=0;i<20;i++){
				executor.execute(myTask);
			}
		}
	}
}

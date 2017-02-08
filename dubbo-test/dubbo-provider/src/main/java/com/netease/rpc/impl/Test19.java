package com.netease.rpc.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jo on 2/7/17.
 */
public class Test19 {
	public static void main(String[] args){
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
		ses.scheduleAtFixedRate(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
					System.out.println(System.currentTimeMillis()/1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},0,2, TimeUnit.SECONDS);
	}
}

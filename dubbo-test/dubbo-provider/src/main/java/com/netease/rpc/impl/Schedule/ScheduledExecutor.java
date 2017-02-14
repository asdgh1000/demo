package com.netease.rpc.impl.Schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jo on 2/12/17.
 */
public class ScheduledExecutor implements Runnable{
	private String jobName = "";

	public ScheduledExecutor(String jobName) {
		super();
		this.jobName = jobName;
	}

	@Override public void run() {
		System.out.println("execute "+jobName);
	}
	public static void main(String[] args){
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
		long initialDelay1 = 1;
		long period1 = 1;
		scheduledExecutorService.scheduleAtFixedRate(
				new ScheduledExecutor("job1"),initialDelay1,period1, TimeUnit.SECONDS
		);
		long initialDelay2 = 1;
		long period2 = 1;
		scheduledExecutorService.scheduleAtFixedRate(
				new ScheduledExecutor("job2"),initialDelay2,period2,TimeUnit.SECONDS
		);
	}
}

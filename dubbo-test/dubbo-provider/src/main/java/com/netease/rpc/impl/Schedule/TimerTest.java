package com.netease.rpc.impl.Schedule;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jo on 2/12/17.
 */
public class TimerTest extends TimerTask {
	private String jobName = "";
	public TimerTest(String jobName){
		super();
		this.jobName = jobName;
	}
	@Override
	public void run() {
		System.out.println("execute "+jobName);
	}
	public static void main(String[] args){
		Timer timer = new Timer("a");

		long delay1 = 1*1000;
		long period1 = 1000;
		timer.schedule(new TimerTest("job1"),delay1,period1);
		long delay2 = 5*1000;
		long period2 = 2000;
		timer.schedule(new TimerTest("job2"),delay2,period2 );
	}
}

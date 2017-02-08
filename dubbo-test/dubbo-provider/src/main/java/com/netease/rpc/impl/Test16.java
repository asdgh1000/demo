package com.netease.rpc.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Jo on 2/7/17.
 */
public class Test16 implements Runnable{
	final Semaphore semaphore = new Semaphore(5);
	public void run() {
		try {
			semaphore.acquire();
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getId()+" done");
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		Executor executor = Executors.newFixedThreadPool(20);
		final Test16 demo = new Test16();
		for(int i=0;i<20;i++){
			executor.execute(demo);
		}
	}
}

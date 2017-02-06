package com.netease.rpc.impl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Jo on 2/3/17.
 */
public class Test1 {
	public static void main(String[] args) throws InterruptedException {
		Executor executor = Executors.newFixedThreadPool(5);
		Test1.time(executor, 4, new Runnable() {
			public void run() {
				System.out.println("test");
			}
		});
	}
	public static long time(Executor executor,int concurency,final Runnable action) throws InterruptedException {
		final CountDownLatch ready = new CountDownLatch(concurency);
		final CountDownLatch start = new CountDownLatch(1);
		final CountDownLatch done = new CountDownLatch(concurency);
		for(int i =0; i< concurency;i++){
			executor.execute(new Runnable() {
				public void run() {
					ready.countDown();
					try {
						start.await();
						action.run();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}finally{
						done.countDown();
					}
				}
			});
		}
		ready.await();
		long startNanos = System.nanoTime();
		System.out.println(startNanos);
		start.countDown();
		done.await();
		long time = System.nanoTime() - startNanos;
		System.out.println(time);
		return time;
	}
}

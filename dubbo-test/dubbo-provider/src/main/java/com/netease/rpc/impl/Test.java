package com.netease.rpc.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Jo on 2/1/17.
 */
public class Test {

	public static Executor executor = Executors.newFixedThreadPool(5);
	public static Object lock = new Object();
	public static void main(String[] args){
		Task task1 = new Task();
		Task task2 = new Task();
		executor.execute(task1);
		executor.execute(task2);
	}
	static class Task implements Runnable {
		public void run() {
			synchronized (lock) {
				try{
					lock.wait();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		public void cal() {
			System.out.println(Thread.currentThread());
			int i = 0;
			while (true) {
				i++;
			}
		}
	}
}

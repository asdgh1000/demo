package com.netease.rpc.impl;

/**
 * Created by Jo on 2/6/17.
 */
public class Test8 {
	static volatile int i = 0;
	public static class PlusTask implements Runnable{
		public void run(){
			for(int k=0;k<10000;k++){
				i++;
			}
		}
	}

	public static void main(String[] args)throws InterruptedException {
		Thread[] threads = new Thread[10];
		for(int i=0;i<10;i++){
			threads[i] = new Thread(new PlusTask());
			threads[i].start();
		}
		for(int i=0;i<10;i++){
			threads[i].join();
		}
		System.out.println(i);
	}
}

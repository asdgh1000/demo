package com.netease.rpc.impl;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Jo on 2/7/17.
 */
public class Test11 {
	static CopyOnWriteArrayList<Integer> arrayList = new CopyOnWriteArrayList<Integer>();
	public static class AddThread implements Runnable{

		public void run() {
			for(int i=0;i<100000;i++){
				arrayList.add(i);
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new AddThread());
		Thread t2 = new Thread(new AddThread());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(arrayList.size());
	}
}

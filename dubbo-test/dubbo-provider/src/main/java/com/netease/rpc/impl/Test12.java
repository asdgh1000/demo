package com.netease.rpc.impl;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jo on 2/7/17.
 */
public class Test12 implements Runnable {
	public static ReentrantLock lock = new ReentrantLock();
	public static int i = 0;

	public void run() {
		for(int j=0;j<10000000;j++){
			lock.lock();
			lock.lock();
			try{
				i++;
			}finally {
				lock.unlock();
				lock.unlock();
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Test12 test1 = new Test12();
		Test12 test2 = new Test12();
		Thread t1 = new Thread(test1);
		Thread t2 = new Thread(test2);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);

	}
}

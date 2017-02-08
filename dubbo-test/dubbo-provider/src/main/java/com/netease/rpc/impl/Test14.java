package com.netease.rpc.impl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jo on 2/7/17.
 */
public class Test14 implements Runnable {
	public static ReentrantLock lock = new ReentrantLock();
	public static Condition condition = lock.newCondition();
	public void run() {
		try {
			lock.lock();
			condition.await();
			System.out.println("Thread is going on");
		}catch (InterruptedException e){
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Test14 test14 = new Test14();
		Thread thread = new Thread(test14);
		thread.start();
		Thread.sleep(2000);
		lock.lock();
		condition.signal();
		lock.unlock();
	}
}

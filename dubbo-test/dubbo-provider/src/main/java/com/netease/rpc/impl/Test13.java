package com.netease.rpc.impl;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jo on 2/7/17.
 */
public class Test13 implements Runnable {
	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	int lock;
	public Test13(int lock){
		this.lock = lock;
	}
	public void run() {
		try {
		if(lock == 1){
				lock1.lockInterruptibly();
				try{
					Thread.sleep(500);
				}catch (InterruptedException e){
					e.printStackTrace();
				}
				lock2.lockInterruptibly();
			}
			else{
				lock2.lockInterruptibly();
			try{
				Thread.sleep(500);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			if(lock1.isHeldByCurrentThread()){
				lock1.unlock();
			}
			if(lock2.isHeldByCurrentThread()){
				lock2.unlock();
			}
			System.out.println(Thread.currentThread().getId()+"线程退出");
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Test13 test1 = new Test13(1);
		Test13 test2 = new Test13(2);
		Thread thread1 = new Thread(test1);
		Thread thread2 = new Thread(test2);
		thread1.start();
		thread2.start();
		Thread.sleep(1000);
		thread2.interrupt();
	}
}

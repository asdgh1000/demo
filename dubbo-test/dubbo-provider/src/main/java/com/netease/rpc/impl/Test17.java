package com.netease.rpc.impl;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Jo on 2/7/17.
 */
public class Test17 {
	private static Lock lock = new ReentrantLock();
	private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
	private static Lock readLock = reentrantReadWriteLock.readLock();
	private static Lock writeLock = reentrantReadWriteLock.writeLock();
	private int value;

	public Object handleRead(Lock lock) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(1000);
			return value;
		} finally {
			lock.unlock();
		}
	}

	public void handleWrite(Lock lock,int index) throws InterruptedException {
		try{
			lock.lock();
			Thread.sleep(1000);
			value=index;
		}finally {
			lock.unlock();
		}
	}
	public static void main(String[] args) {
		final Test17 demo = new Test17();
		Runnable readRunnable = new Runnable() {
			public void run() {
				try {
					demo.handleRead(readLock);
//					demo.handleRead(lock);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		Runnable writeRunnable = new Runnable() {
			public void run() {

				try {
					demo.handleWrite(writeLock, new Random().nextInt());
//					demo.handleWrite(lock,new Random().nextInt());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		for(int i=0;i<20;i++){
			new Thread(readRunnable).start();
		}
		for(int i=0;i<2;i++){
			new Thread(writeRunnable).start();
		}
	}
}

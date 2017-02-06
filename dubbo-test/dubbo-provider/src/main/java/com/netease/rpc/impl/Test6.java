package com.netease.rpc.impl;

/**
 * Created by Jo on 2/6/17.
 */
public class Test6 {
	final static Object lock = new Object();
	public static class T1 extends Thread{
		@Override
		public void run(){
			synchronized (lock){
				System.out.println(System.currentTimeMillis()+":T1 start!");
				try{
					System.out.println(System.currentTimeMillis()+":T1 wait for lock");
					lock.wait();
				}catch (InterruptedException e){
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis()+":T1 end");
			}
		}
	}

	public static class T2 extends Thread{
		@Override
		public void run(){
			synchronized (lock){
				System.out.println(System.currentTimeMillis()+":T2 start! notify one thread");
				lock.notify();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis()+":T2 end");
			}
		}
	}
	public static void main(String[] args){
		Thread t1 = new T1();
		Thread t2 = new T2();
		t1.start();
		t2.start();
	}
}

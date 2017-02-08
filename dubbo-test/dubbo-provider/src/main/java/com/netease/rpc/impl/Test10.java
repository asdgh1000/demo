package com.netease.rpc.impl;

/**
 * Created by Jo on 2/6/17.
 */
public class Test10 implements Runnable{
//	static Test10 instance = new Test10();
	static int i=0;
	public static synchronized void increase(){
		i++;
	}

	public void run() {
		for(int j=0;j<10000000;j++){
			increase();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Test10());
		Thread t2 = new Thread(new Test10());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}

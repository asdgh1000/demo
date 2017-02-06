package com.netease.rpc.impl;

/**
 * Created by Jo on 2/6/17.
 */
public class Test5 {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(){

			@Override
			public void run(){
				while(true){
					if(currentThread().isInterrupted()) {
						System.out.println("Interrupted");
						break;
					}
					Thread.yield();
				}
			}
		};
		t1.start();
		t1.wait();
		Thread.sleep(2000);
		t1.interrupt();
	}
}

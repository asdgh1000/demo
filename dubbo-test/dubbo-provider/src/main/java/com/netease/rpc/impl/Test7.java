package com.netease.rpc.impl;

/**
 * Created by Jo on 2/6/17.
 */
public class Test7 {
	public volatile  static int i=0;
	public static class AddThread extends Thread{
		@Override
		public void run(){
			for(i=0;i<10000000;i++);
		}
	}
	public static void main(String[] args)throws InterruptedException {
		AddThread at = new AddThread();
		at.start();
		at.join();
		System.out.println(i);
	}
}

package com.netease.rpc.impl;

/**
 * Created by Jo on 2/6/17.
 */
public class Test9 implements Runnable{

	public static void main(String[] args){
		ThreadGroup tg = new ThreadGroup("PrintGroup");
		Thread t1 = new Thread(tg,new Test9(),"T1");
		Thread t2 = new Thread(tg,new Test9(),"T2");
		t1.start();
		t2.start();
		System.out.println(tg.activeCount());
		tg.list();
	}

	public void run() {
		String groupAndName = Thread.currentThread().getThreadGroup().getName()+"-"+Thread.currentThread().getName();
		while (true){
			System.out.println("I am "+groupAndName);
			try{
				Thread.sleep(3000);
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}

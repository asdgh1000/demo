package com.netease.rpc.impl;

/**
 * Created by Jo on 2/9/17.
 */
public class Test22 extends Thread{
	protected Object tool;
	static Object fork1 = new Object();
	static Object fork2 = new Object();

	public Test22(Object obj){
		this.tool = obj;
		if(tool == fork1){
			this.setName("哲学家A");
		}
		if(tool == fork2){
			this.setName("哲学家B");
		}
	}
	@Override
	public void run(){
		if(tool == fork1){
			synchronized (fork1){
				try{
					Thread.sleep(500);
				}catch (Exception e){
					e.printStackTrace();
				}
				synchronized (fork2){
					System.out.println("哲学家A开始吃饭了");
				}
			}
		}
		if(tool == fork2){
			synchronized (fork2){
				try{
					Thread.sleep(500);
				}catch(Exception e){
					e.printStackTrace();
				}
				synchronized (fork1){
					System.out.println("哲学家B开始吃饭了");
				}
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Test22 a = new Test22(fork1);
		Test22 b = new Test22(fork2);
		a.start();
		b.start();
		Thread.sleep(1000);
	}


}

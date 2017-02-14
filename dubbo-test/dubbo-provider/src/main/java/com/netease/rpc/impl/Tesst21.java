package com.netease.rpc.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jo on 2/9/17.
 */

public class Tesst21 {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static Object  lock = new Object();

	public static class ParseDate implements Runnable{
		int i = 0;

		ThreadLocal threadLocal = new ThreadLocal();
//		public static ReentrantLock lock = new ReentrantLock();
		public ParseDate(int i) {
			this.i = i;
		}

		@Override public void run() {
			try {
//				lock.lock();
				synchronized (lock) {
					Date t = sdf.parse("2015-03-29 19:29:" + i % 60);
					System.out.println(i + ":" + t);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}finally {
//				lock.unlock();
			}
		}
	}
	public static void main(String[] args){
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i=0;i<1000;i++){
			es.execute(new ParseDate(i));
		}
	}
}

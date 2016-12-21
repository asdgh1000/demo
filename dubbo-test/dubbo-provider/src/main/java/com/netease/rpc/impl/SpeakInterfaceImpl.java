package com.netease.rpc.impl;

import java.util.HashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Jo on 11/29/16.
 */
public class SpeakInterfaceImpl {
	public static void main(String[] args) {
		HashMap hashMap = new HashMap();
		ReentrantReadWriteLock
//		for(int i=0;i<20;i++){
//			hashMap.put(i,i+"test");
//			System.out.print(hashMap.values());
//		}
		hashMap.put(1,"1");
		hashMap.put(2,"2");
		System.out.println(14 & 19);
	}



}

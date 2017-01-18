package com.netease.spi.impl;

import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Jo on 1/18/17.
 */
public class HashMapTest {
	public static void main(String[] args){
		HashMapTest hashMapTest = new HashMapTest();
		hashMapTest.test();
	}
	public void test() {
		ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
		concurrentHashMap.put("1","2");
	final Map<String, String> map = new Hashtable<String, String>(2);
		for(int i = 0;i<10000;i++)
		{
		new Thread(new Runnable() {
			@Override
			public void run() {
				map.put(UUID.randomUUID().toString(), "");
			}
		}).start();
	}
}
}

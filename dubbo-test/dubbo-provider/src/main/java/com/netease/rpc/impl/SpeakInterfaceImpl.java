package com.netease.rpc.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Jo on 11/29/16.
 *
 */
public class SpeakInterfaceImpl {

	public static void speak() {
		HashMap map = new HashMap();
		for(int i = 0;i< 100;i++){
			map.put(i,"test"+i);
		}
		Iterator<Map.Entry> it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = it.next();
			if((Integer)entry.getKey()>20){
				it.remove();
			}
		}
		Iterator<Map.Entry> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry entry = iterator.next();
			System.out.println(entry.getKey());
		}
	}
	public static void main(String[] args){
		SpeakInterfaceImpl.speak();
	}
}

package com.netease.rpc.impl;

import java.util.HashMap;

/**
 * Created by Jo on 11/29/16.
 */
public class SpeakInterfaceImpl {
	public static void main(String[] args) {
		HashMap hashMap = new HashMap();
		hashMap.put(1,"test1");
		hashMap.put(2,"test2");
		hashMap.put(3,"test3");
		hashMap.put(null,"test4");
		hashMap.put(4,null);
		hashMap.put(5,null);

		System.out.println(hashMap.values());

	}

}

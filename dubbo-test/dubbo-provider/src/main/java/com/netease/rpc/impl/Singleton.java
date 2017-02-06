package com.netease.rpc.impl;

/**
 * Created by Jo on 2/3/17.
 */
public class Singleton {
	private static final Singleton singleton = new Singleton();
	private Singleton(){
	}
	public static Singleton getSingleton(){

		return singleton;
	}
}

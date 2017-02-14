package com.netease.rpc.impl;

/**
 * Created by Jo on 2/9/17.
 */
public class Test24 {
	private Test24(){
		System.out.println("create singleton");
	}
	private static Test24 singleton= null;
	public synchronized  static Test24 getInstance(){
		if(singleton == null){
			singleton = new Test24();
		}
		return singleton;
	}

}

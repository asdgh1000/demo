package com.netease.rpc.impl;

/**
 * Created by Jo on 2/9/17.
 */
public class Test25 {

	private Test25(){
		System.out.println("create singleton instance");
	}
	private static class SingletonHolder{

		private static Test25 test25 = new Test25();
	}
	public static Test25 getInstance(){
		return SingletonHolder.test25;
	}
}

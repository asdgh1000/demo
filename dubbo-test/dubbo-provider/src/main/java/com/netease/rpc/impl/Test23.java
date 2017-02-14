package com.netease.rpc.impl;

/**
 * Created by Jo on 2/9/17.
 */
public class Test23 {
	public static int STATUS =1;
	private Test23(){
		System.out.println("singleton is create");
	}
	private static Test23 test23 = new Test23();
	public static Test23 getInstance(){
		return test23;
	}
	public static void main(String[] args){
		int a = Test23.STATUS;
	}
}

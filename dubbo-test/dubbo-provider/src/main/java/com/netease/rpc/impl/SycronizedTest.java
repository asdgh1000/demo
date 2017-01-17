package com.netease.rpc.impl;

/**
 * Created by Jo on 1/16/17.
 */
public class SycronizedTest {
	public void test() throws InterruptedException {
		SycronizedTest sycronizedTest = new SycronizedTest();
		sycronizedTest.wait();
	}
	public static void main(String[] args){
		SycronizedTest sycronizedTest = new SycronizedTest();
		try {
			sycronizedTest.test();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

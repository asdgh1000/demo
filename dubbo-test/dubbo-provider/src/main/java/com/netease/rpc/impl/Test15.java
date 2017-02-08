package com.netease.rpc.impl;

/**
 * Created by Jo on 2/7/17.
 */
public class Test15 {
	public void test(){
		int a=0;
		while(++a<=8){
			int c=0;
			while(++c<=13) {
				if (a + c == 13 && c - a == 6) {
					System.out.println("a:" + a + ",c:" + c);
				} else {
					c++;
				}
			}
		}
	}
	public static void main(String[] args){
		Test15 test15 = new Test15();
		test15.test();
	}
}

package com.netease.rpc.impl;

/**
 * Created by Jo on 11/29/16.
 *
 */
public class SpeakInterfaceImpl {
	public static void main(String[] args){
		System.out.println(SpeakInterfaceImpl.class.getClassLoader());
		System.out.println(SpeakInterfaceImpl.class.getClassLoader().getParent());
		System.out.println(SpeakInterfaceImpl.class.getClassLoader().getParent().getParent());
		System.out.println(ClassLoader.getSystemClassLoader());
	}

}

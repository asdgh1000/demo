package com.netease.spi;

import java.util.ServiceLoader;

/**
 * Created by Jo on 1/17/17.
 */
public class SpiTest {
	public static void main(String[] args){
		ServiceLoader<HelloInterface> loaders = ServiceLoader.load(HelloInterface.class);
		for(HelloInterface helloInterface : loaders){
			helloInterface.sayHello();
		}
	}
}

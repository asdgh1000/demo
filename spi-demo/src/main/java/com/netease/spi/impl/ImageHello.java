package com.netease.spi.impl;

import com.netease.spi.HelloInterface;

/**
 * Created by Jo on 1/17/17.
 */
public class ImageHello implements HelloInterface {

	@Override
	public void sayHello() {
		System.out.println("this is for test");
	}
}

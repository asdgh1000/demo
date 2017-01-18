package com.netease.spi.impl;

import com.netease.spi.HelloInterface;

/**
 * Created by Jo on 1/18/17.
 */
public class TextHello implements HelloInterface {
	@Override public void sayHello() {
		System.out.println("text hello");
	}
}

package com.ivanzhang.spi.impl;

import com.ivanzhang.spi.HelloInterface;

/**
 * Created by Jo on 1/9/17.
 */
public class TextHello implements HelloInterface {
	public void sayHello() {
		System.out.println("Text Hello.");
	}


}

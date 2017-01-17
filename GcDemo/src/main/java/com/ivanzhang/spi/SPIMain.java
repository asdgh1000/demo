package com.ivanzhang.spi;

import java.util.ServiceLoader;

/**
 * Created by Jo on 1/9/17.
 */

public class SPIMain {
	public static void main(String[] args) {

		ServiceLoader<HelloInterface> loaders =
				ServiceLoader.load(HelloInterface.class);

		for (HelloInterface in : loaders) {
			in.sayHello();
		}
	}
}

package com.netease.rpc.impl;

import com.netease.rpc.TestService;

/**
 * Created by Jo on 1/12/17.
 */
public class TestServiceImpl implements TestService {
	public String speak() {

		System.out.println("hello world");
		return "hello world";
	}
}

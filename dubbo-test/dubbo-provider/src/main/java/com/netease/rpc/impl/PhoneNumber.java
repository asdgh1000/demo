package com.netease.rpc.impl;

import java.io.Serializable;

/**
 * Created by Jo on 2/3/17.
 */
public class PhoneNumber implements Serializable{


	private String name;

	private String phoneNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override public String toString() {
		Thread.yield();
//		ThreadPoolExecutor executor = new Executor();
//		Executor executor = Executors.newFixedThreadPool(10);
		final StringBuffer sb = new StringBuffer("PhoneNumber{");
		sb.append("name='").append(name).append('\'');
		sb.append(", phoneNumber='").append(phoneNumber).append('\'');
		sb.append('}');
		return sb.toString();
	}
}

package com.netease.rpc.impl;

import java.util.Date;

/**
 * Created by Jo on 1/16/17.
 */
public class Parameter {

	private static ThreadLocal<Parameter> _parameter= new ThreadLocal<Parameter>();
	private static ThreadLocal _parameter1= new ThreadLocal();

	public static Parameter init() {
		Runnable runnable = new Runnable() {
			public void run() {

			}
		};
		Thread a = new Thread(runnable);
		a.setDaemon(true);
		a.start();
		_parameter1.set(new Date());
		_parameter.set(new Parameter());
		return  _parameter.get();
	}
	public static Parameter get() {
		return _parameter.get();
	}
	public static void main(String[] args){
		init();
	}
}

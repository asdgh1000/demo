package com.netease.seckill.exception;

/**
 * Created by Jo on 10/18/16.
 * seckill exception
 */
public class SeckillException extends RuntimeException{

	public SeckillException(String message) {
		super(message);
	}

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}
}

package com.netease.seckill.exception;

/**
 * Created by Jo on 10/18/16.
 * repeat seckill exception(runnable exception)
 */
public class RepeatKillException extends SeckillException{
	public RepeatKillException(String message) {
		super(message);
	}

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}
}

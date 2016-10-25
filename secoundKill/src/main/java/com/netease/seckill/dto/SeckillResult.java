package com.netease.seckill.dto;

import java.io.Serializable;

/**
 * Created by Jo on 10/19/16.
 */
//packing json result
public class SeckillResult<T> {
	private boolean success;

	private T data;

	private String error;

	public SeckillResult(boolean success, String error) {
		this.success = success;
		this.error = error;
	}

	public SeckillResult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}

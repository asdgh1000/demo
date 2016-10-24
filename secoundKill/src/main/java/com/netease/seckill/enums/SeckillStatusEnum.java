package com.netease.seckill.enums;

/**
 * Created by Jo on 10/18/16.
 */
public enum SeckillStatusEnum {
	SUCCESS(1,"successkill"),
	END(0,"endkill"),
	REPEAT_KILL(-1,"repeatKill"),
	INNER_ERROR(-2,"inner error"),
	DATA_REWRITE(-3,"data rewrite");

	private int status;
	private String statusInfo;

	public static SeckillStatusEnum statusOf(int index){
		for(SeckillStatusEnum status : values()){
			if(status.getStatus() == index){
				return status;
			}
		}
		return null;
	}

	SeckillStatusEnum(int status, String statusInfo) {
		this.status = status;
		this.statusInfo = statusInfo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}
}

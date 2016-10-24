package com.netease.seckill.dto;

import com.netease.seckill.enums.SeckillStatusEnum;
import com.netease.seckill.entity.SuccessKill;

/**
 * Created by Jo on 10/18/16.
 * packing result after seckill
 */
public class SeckillExcution {

	private long seckillId;

	private int status;

	private String statusInfo;

	private SuccessKill successKill;

	public SeckillExcution(long seckillId, SeckillStatusEnum seckillStatusEnum, SuccessKill successKill) {
		this.seckillId = seckillId;
		this.status = seckillStatusEnum.getStatus();
		this.statusInfo = seckillStatusEnum.getStatusInfo();
		this.successKill = successKill;
	}
	public SeckillExcution(long seckillId, SeckillStatusEnum seckillStatusEnum) {
		this.seckillId = seckillId;
		this.status = seckillStatusEnum.getStatus();
		this.statusInfo = seckillStatusEnum.getStatusInfo();
	}

		public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(String statusInfo) {
		this.statusInfo = statusInfo;
	}

	public SuccessKill getSuccessKill() {
		return successKill;
	}

	public void setSuccessKill(SuccessKill successKill) {
		this.successKill = successKill;
	}

	@Override public String toString() {
		final StringBuffer sb = new StringBuffer("SeckillExcution{");
		sb.append("seckillId=").append(seckillId);
		sb.append(", status=").append(status);
		sb.append(", statusInfo='").append(statusInfo).append('\'');
		sb.append(", successKill=").append(successKill);
		sb.append('}');
		return sb.toString();
	}
}

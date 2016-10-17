package com.netease.seckill.entity;

import java.util.Date;

/**
 * Created by Jo on 10/17/16.
 */
public class SuccessKill {
	private long seckillId;

	private long userPhone;

	private short status;

	private Date createTime;

	//many to one
	private Seckill seckill;

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	@Override public String toString() {
		final StringBuffer sb = new StringBuffer("SuccessKill{");
		sb.append("seckillId=").append(seckillId);
		sb.append(", userPhone=").append(userPhone);
		sb.append(", status=").append(status);
		sb.append(", createTime=").append(createTime);
		sb.append(", seckill=").append(seckill);
		sb.append('}');
		return sb.toString();
	}
}

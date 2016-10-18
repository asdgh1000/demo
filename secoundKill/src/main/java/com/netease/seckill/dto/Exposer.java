package com.netease.seckill.dto;

/**
 * Created by Jo on 10/18/16.
 * expose seckill url DTO
 */
public class Exposer {

	//start seckill or not
	private boolean exposed;

	//encryption method
	private String md5;

	//id
	private long seckillId;

	//system current time
	private long now;
	//start time
	private long startTime;
	//end time
	private long endTime;

	public Exposer(boolean exposed, String md5, long seckillId) {
		this.exposed = exposed;
		this.md5 = md5;
		this.seckillId = seckillId;
	}

	public Exposer(boolean exposed, long seckillId,long now, long startTime, long endTime) {
		this.exposed = exposed;
		this.seckillId = seckillId;
		this.now = now;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Exposer(boolean exposed, long seckillId) {
		this.exposed = exposed;
		this.seckillId = seckillId;
	}

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	@Override public String toString() {
		final StringBuffer sb = new StringBuffer("Exposer{");
		sb.append("exposed=").append(exposed);
		sb.append(", md5='").append(md5).append('\'');
		sb.append(", seckillId=").append(seckillId);
		sb.append(", now=").append(now);
		sb.append(", startTime=").append(startTime);
		sb.append(", endTime=").append(endTime);
		sb.append('}');
		return sb.toString();
	}
}

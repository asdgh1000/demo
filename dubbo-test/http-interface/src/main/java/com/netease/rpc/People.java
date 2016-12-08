package com.netease.rpc;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Jo on 11/29/16.
 */
public class People implements Serializable{

	private Integer age;

	private String sex;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override public String toString() {
		AtomicInteger a= new AtomicInteger();
		a.compareAndSet()
		final StringBuffer sb = new StringBuffer("People{");
		sb.append("age=").append(age);
		sb.append(", sex='").append(sex).append('\'');
		sb.append('}');
		return sb.toString();
	}
}

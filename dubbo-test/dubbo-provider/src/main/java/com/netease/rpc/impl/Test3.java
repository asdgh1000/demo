package com.netease.rpc.impl;

/**
 * Created by Jo on 2/4/17.
 */
public class Test3 {
	private int age;
	private String name;
	private String className;
	public static class Builder{
		private int age;
		private String name;
		private String classaName;
		public Builder(String name){
			this.name = name;
		}
		public Builder age(int age){
			this.age = age;
			return this;
		}
		public Builder className(String classaName){
			this.classaName = classaName;
			return this;
		}
		public Test3 build(){
			return new Test3(this);
		}

	}
	public Test3(Builder builder){
		age = builder.age;
		name = builder.name;
		className = builder.classaName;
	}

	@Override public String toString() {
		final StringBuffer sb = new StringBuffer("Test3{");
		sb.append("age=").append(age);
		sb.append(", name='").append(name).append('\'');
		sb.append(", className='").append(className).append('\'');
		sb.append('}');
		return sb.toString();
	}

	public static void main(String[] args){
		Test3 test = new Builder("litianwei").age(24).className("san nian er ban").build();
		System.out.println(test);
	}
}

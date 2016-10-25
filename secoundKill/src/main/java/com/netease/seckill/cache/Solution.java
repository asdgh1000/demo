package com.netease.seckill.cache;

/**
 * Created by Jo on 10/25/16.
 */
public class Solution {
	public static void main(String args[]) {

		int sum = 0;
		int number=1;
		int sum2=0;
		for (number= 1; number <= 19; number++) {//number表示有几个不重复的数相加，从1一直循环到19
			sum2=pan(number);
			sum+=sum2;
			System.out.println("数字1到19中 "+number+" 个不重复的数相加等于20 ---有方法 " + sum2 + "种");
		}
		System.out.println("1到19任意个不重复的数相加等于20  共有方法 " + sum + " 种");
	}


	private static int pan(int number){
		int start=1;//start表示每次循环的开始数值
		int sum=0;//记录所有满足要求的方法数
		int n=1;//记录当前已是第几个重复的数
		int midresult=0;//midresult记录每次循环所得的中间结果，以便下次循环参考
		for (start= 1; start<19;start++) {//在不重复数字总个数为number情况下，初始数字从1到19变化
			midresult =start;
			sum+= duan(start,midresult,number,n);
		}
		return sum;
	}
	private static int duan(int start,int midresult,int number,int n){
		int sum=0;
		for (int j= start+1; j<=19;j++) {
			if(n+1<number&&midresult+j<20)
				sum+= duan(j,midresult+j,number,n+1);//运用了递归算法
			else
			if(n+1==number&&j+midresult==20)sum++;
		}
		return sum;
	}
}
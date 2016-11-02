package com.netease.seckill.cache;

/**
 * Created by Jo on 10/29/16.
 */
public class Solution {
	public int arrangeCoins(int n) {
		if(n==0){
			return 0;
		}
		if(n==1 || n ==2 ){
			return 1;
		}
		int count = 0;
		int a = 0;
		while(count <= n){
			count+=a;
			a++;
		}
		return a-1;
	}


	public static void main(String[] args){
		Solution solution = new Solution();
		System.out.println(solution.arrangeCoins(2147483647));
	}
}
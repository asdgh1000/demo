package com.netease.seckill.cache;

/**
 * Created by Jo on 11/6/16.
 */
public class Solution {
	public String reverseVowels(String s) {
		String x = "aeiouAEIOU";

		char[] a = s.toCharArray();
		int i = 0;
		int j = s.length()-1;

		while(i <j){
			while(!x.contains(a[i]+"") &&i<s.length()-1){
				i++;
			}
			while(!x.contains(a[j]+"")&&j>0){
				j--;
			}
			if(i <j) {
				char tmp = 'z';
				tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
				i++;
				j--;
			}
		}
		String z="";
		for(char f : a){
			z+=f;
		}
		return z;
	}
	public static void main(String[] args){
		Solution solution = new Solution();
		System.out.println(solution.reverseVowels("OE"));
	}
}
package com.netease.seckill.cache;

import java.util.HashMap;

/**
 * Created by Jo on 10/26/16.
 */
public class Solution {
	public int hash(int h) {
			h ^= (h >>> 20) ^ (h >>> 12);
			return h ^ (h >>> 7) ^ (h >>> 4);
	}
	public static void main(String[] args){
//		HashMap map = new HashMap();
//		map.put()
		Solution solution = new Solution();
		System.out.println(solution.hash('g'));
	}
}

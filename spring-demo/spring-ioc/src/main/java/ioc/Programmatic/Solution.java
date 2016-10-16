package ioc.Programmatic;

import com.sun.tools.hat.internal.util.ArraySorter;

import java.util.*;

/**
 * Created by Jo on 10/9/16.
 */
public class Solution {
	public int[] intersection(int[] nums1, int[] nums2) {
		int[] result = new int[nums1.length];
		if(nums2.length == 0){
			return null;
		}
		HashSet set = new HashSet();
		HashSet<Integer> set2 = new HashSet();
		for(int i=0;i<nums1.length;i++){
			set.add(nums1[i]);
		}
		for(int i=0;i<nums2.length;i++){
			if(set.contains(nums2[i])){
				set2.add(nums2[i]);
			}
		}
		int i = 0;
		for(Integer a : set2){
			result[i++] = a;
		}
		return result;
	}
}